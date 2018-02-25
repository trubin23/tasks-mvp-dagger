package ru.trubin23.tasks_mvp_dagger.addedittask;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;
import ru.trubin23.tasks_mvp_dagger.di.FragmentScoped;

/**
 * Created by Andrey on 25.02.2018.
 */

@Module
public abstract class AddEditTaskModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddEditTaskFragment addEditTaskFragment();

    @ActivityScoped
    @Binds
    abstract AddEditTaskContract.Presenter taskPresenter(AddEditTaskPresenter presenter);
}
