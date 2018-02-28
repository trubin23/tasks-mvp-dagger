package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.Nullable;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;
import ru.trubin23.tasks_mvp_dagger.di.FragmentScoped;

/**
 * Created by Andrey on 26.02.2018.
 */

@Module
public abstract class TaskDetailModule {

    @Provides
    @ActivityScoped
    @Nullable
    static String provideTaskId(TaskDetailActivity activity){
        return activity.getIntent().getStringExtra(TaskDetailActivity.TASK_ID);
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract TaskDetailFragment taskDetailFragment9();

    @ActivityScoped
    @Binds
    abstract TaskDetailContract.Presenter taskPresenter(TaskDetailPresenter presenter);
}
