package ru.trubin23.tasks_mvp_dagger.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ru.trubin23.tasks_mvp_dagger.addedittask.AddEditTaskActivity;
import ru.trubin23.tasks_mvp_dagger.addedittask.AddEditTaskModule;
import ru.trubin23.tasks_mvp_dagger.taskdetail.TaskDetailActivity;
import ru.trubin23.tasks_mvp_dagger.taskdetail.TaskDetailModule;
import ru.trubin23.tasks_mvp_dagger.tasks.TasksActivity;
import ru.trubin23.tasks_mvp_dagger.tasks.TasksModule;

/**
 * Created by Andrey on 23.02.2018.
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = TasksModule.class)
    abstract TasksActivity tasksActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddEditTaskModule.class)
    abstract AddEditTaskActivity addEditTaskActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = TaskDetailModule.class)
    abstract TaskDetailActivity taskDetailActivity();
}
