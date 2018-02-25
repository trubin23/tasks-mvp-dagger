package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.support.annotation.Nullable;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;
import ru.trubin23.tasks_mvp_dagger.di.FragmentScoped;

/**
 * Created by Andrey on 25.02.2018.
 */

@Module
public abstract class AddEditTaskModule {

    @Provides
    @ActivityScoped
    @Nullable
    static String provideTaskId(AddEditTaskActivity activity){
        return activity.getIntent().getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddEditTaskFragment addEditTaskFragment();

    @ActivityScoped
    @Binds
    abstract AddEditTaskContract.Presenter taskPresenter(AddEditTaskPresenter presenter);
}
