package ru.trubin23.tasks_mvp_dagger.taskdetail;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailFragment extends DaggerFragment implements TaskDetailContract.View {

    @Inject
    public TaskDetailFragment() {
    }
}
