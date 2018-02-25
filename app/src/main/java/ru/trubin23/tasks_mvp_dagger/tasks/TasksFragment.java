package ru.trubin23.tasks_mvp_dagger.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;
import ru.trubin23.tasks_mvp_dagger.tasks.tasklist.TaskItemListener;
import ru.trubin23.tasks_mvp_dagger.tasks.tasklist.TasksAdapter;

/**
 * Created by Andrey on 19.02.2018.
 */

@ActivityScoped
public class TasksFragment extends DaggerFragment implements TasksContract.View {

    private TasksAdapter mTasksAdapter;

    @Inject
    TasksContract.Presenter mPresenter;

    @Inject
    public TasksFragment() {
        TaskItemListener taskItemListener = this::showTaskDetail;
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
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void showTaskDetail(@NonNull String taskId) {

    }

    private void showAddTask(){

    }
}
