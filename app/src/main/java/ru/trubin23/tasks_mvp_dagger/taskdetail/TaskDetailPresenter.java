package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    @Inject
    public TaskDetailPresenter() {
    }

    @Override
    public void takeView(@NonNull TaskDetailContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
