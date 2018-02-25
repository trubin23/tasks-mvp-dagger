package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 19.02.2018.
 */

@ActivityScoped
public class TasksPresenter implements TasksContract.Presenter {

    private final TasksRepository mTasksRepository;

    private TasksContract.View mTasksView;

    @Inject
    TasksPresenter(@NonNull TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
    }

    @Override
    public void takeView(@NonNull TasksContract.View view) {
        mTasksView = view;
    }

    @Override
    public void dropView() {
        mTasksView = null;
    }
}
