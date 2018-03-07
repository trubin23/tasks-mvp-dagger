package ru.trubin23.tasks_mvp_dagger.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.addedittask.AddEditTaskActivity;
import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.taskdetail.TaskDetailActivity;
import ru.trubin23.tasks_mvp_dagger.tasks.tasklist.DividerItemVertical;
import ru.trubin23.tasks_mvp_dagger.tasks.tasklist.TaskItemListener;
import ru.trubin23.tasks_mvp_dagger.tasks.tasklist.TasksAdapter;

/**
 * Created by Andrey on 19.02.2018.
 */

public class TasksFragment extends DaggerFragment implements TasksContract.View {

    private TasksAdapter mTasksAdapter;

    @Inject
    TasksContract.Presenter mPresenter;

    @Inject
    public TasksFragment() {
        TaskItemListener taskItemListener = new TaskItemListener() {
            @Override
            public void onTaskClick(@NonNull String taskId) {
                showTaskDetail(taskId);
            }

            @Override
            public void onChangeCompletedTask(@NonNull String taskId, boolean completed) {
                mPresenter.changeCompletedTask(taskId, completed);
            }
        };
        mTasksAdapter = new TasksAdapter(taskItemListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);
        ButterKnife.bind(this, root);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mTasksAdapter);

        DividerItemVertical dividerItemVertical =
                new DividerItemVertical(recyclerView.getContext());
        recyclerView.addItemDecoration(dividerItemVertical);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_add_task);
        fab.setOnClickListener(v -> showAddTask());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tasks_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_filter:
                showFilteringPopUpMenu();
                return true;
            case R.id.menu_refresh:
                mPresenter.loadTasks(true);
                return true;
            case R.id.menu_clear:
                mPresenter.clearCompletedTasks();
                return true;
        }
        return false;
    }

    private void showFilteringPopUpMenu() {

    }

    private void showTaskDetail(@NonNull String taskId) {
        Intent intent = new Intent(getContext(), TaskDetailActivity.class);
        intent.putExtra(TaskDetailActivity.TASK_ID, taskId);
        startActivity(intent);
    }

    private void showAddTask() {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void showTasks(@NonNull List<Task> tasks) {
        getActivity().runOnUiThread(() -> mTasksAdapter.setTasks(tasks));
    }

    @Override
    public void showLoadingTasksError() {
        showMessage(getString(R.string.loading_tasks_error));
    }

    private void showMessage(String message) {
        View view = getView();
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
        }
    }
}
