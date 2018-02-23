package ru.trubin23.tasks_mvp_dagger.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ru.trubin23.tasks_mvp_dagger.TasksApplication;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepositoryModule;

/**
 * Created by Andrey on 22.02.2018.
 */

@Singleton
@Component(modules = {TasksRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<TasksApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
