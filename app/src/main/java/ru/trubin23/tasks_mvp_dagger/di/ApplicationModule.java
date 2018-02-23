package ru.trubin23.tasks_mvp_dagger.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Andrey on 23.02.2018.
 */

@Module
public abstract class ApplicationModule {

    @Binds
    abstract Context bindContext(Application application);
}
