package ru.trubin23.tasks_mvp_dagger.tasks;

import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 19.02.2018.
 */

@ActivityScoped
public class TasksPresenter implements TasksContract.Presenter {

    @Override
    public void takeView(TasksContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
