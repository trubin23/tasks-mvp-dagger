package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.addedittask.AddEditTaskActivity;
import ru.trubin23.tasks_mvp_dagger.addedittask.AddEditTaskFragment;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailFragment extends DaggerFragment
        implements TaskDetailContract.View {

    private static final int REQUEST_EDIT_TASK = 1;

    @BindView(R.id.task_detail_title)
    TextView mTaskTitle;

    @BindView(R.id.task_detail_description)
    TextView mTaskDescription;

    @BindView(R.id.task_detail_date)
    TextView mTaskDateOfCreate;

    @BindView(R.id.task_detail_completed)
    CheckBox mTaskCompleted;

    @Inject
    TaskDetailContract.Presenter mPresenter;

    @Inject
    public TaskDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        ButterKnife.bind(this, root);

        setHasOptionsMenu(true);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task);
        fab.setOnClickListener(v -> mPresenter.editTask());

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
        inflater.inflate(R.menu.taskdetail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                mPresenter.deleteTask();
                return true;
        }
        return false;
    }

    @Override
    public void showMissingTask() {
        mTaskTitle.setText("");
        mTaskDescription.setText(getString(R.string.no_data));
        mTaskDateOfCreate.setText("");
        mTaskCompleted.setChecked(false);
        mTaskCompleted.setEnabled(false);
    }

    @Override
    public void setTitle(@NonNull String title) {
        mTaskTitle.setText(title);
    }

    @Override
    public void setDescription(@NonNull String description) {
        mTaskDescription.setText(description);
    }

    @Override
    public void setDateOfCreation(@NonNull String dateOfCreation) {
        mTaskDateOfCreate.setText(dateOfCreation);
    }

    @Override
    public void setComplete(boolean completed) {
        mTaskCompleted.setChecked(completed);
    }

    @Override
    public void showEditTask(@NonNull String taskId) {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskActivity.EDIT_TASK_ID, taskId);
        startActivityForResult(intent, REQUEST_EDIT_TASK);
    }

    @Override
    public void showTaskDeleted(@NonNull String taskId) {
        getActivity().finish();
    }
}
