package ru.trubin23.tasks_mvp_dagger.tasks;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;
import ru.trubin23.tasks_mvp_dagger.di.FragmentScoped;

/**
 * Created by Andrey on 24.02.2018.
 */

@Module
public abstract class TasksModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TasksFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract TasksContract.Presenter tasksPresenter(TasksPresenter presenter);
}
