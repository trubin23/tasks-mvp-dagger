package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TasksRepository mTasksRepository;

    private String mTaskId;

    private TaskDetailContract.View mTaskDetailView;

    @Inject
    public TaskDetailPresenter() {
    }

    @Override
    public void takeView(@NonNull TaskDetailContract.View view) {
        mTaskDetailView = view;
    }

    @Override
    public void dropView() {
        mTaskDetailView = null;
    }

    @Override
    public void editTask() {

    }
}
