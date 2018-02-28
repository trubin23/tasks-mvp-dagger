package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private String mTaskId;

    private TasksRepository mTasksRepository;

    private TaskDetailContract.View mTaskDetailView;

    @Inject
    public TaskDetailPresenter(@Nullable String taskId,
                               @NonNull TasksRepository tasksRepository) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
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
