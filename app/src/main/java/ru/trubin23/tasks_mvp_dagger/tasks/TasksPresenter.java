package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 19.02.2018.
 */

@ActivityScoped
public class TasksPresenter implements TasksContract.Presenter {

    TasksContract.View mTasksView;

    @Override
    public void takeView(@NonNull TasksContract.View view) {
        mTasksView = view;
    }

    @Override
    public void dropView() {
        mTasksView = null;
    }
}
