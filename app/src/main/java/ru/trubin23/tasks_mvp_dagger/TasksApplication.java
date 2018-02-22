package ru.trubin23.tasks_mvp_dagger;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import ru.trubin23.tasks_mvp_dagger.di.DaggerAppComponent;

/**
 * Created by Andrey on 22.02.2018.
 */

public class TasksApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

}
