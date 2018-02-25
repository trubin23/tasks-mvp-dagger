package ru.trubin23.tasks_mvp_dagger.addedittask;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 25.02.2018.
 */

//@ActivityScoped
public class AddEditTaskFragment extends DaggerFragment
        implements AddEditTaskContract.View {

    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

    //@Inject
    AddEditTaskContract.Presenter mPresenter;

    @Inject
    public AddEditTaskFragment() {
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
}
