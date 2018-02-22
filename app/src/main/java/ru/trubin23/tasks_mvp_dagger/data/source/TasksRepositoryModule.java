package ru.trubin23.tasks_mvp_dagger.data.source;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.trubin23.tasks_mvp_dagger.data.source.local.TasksDao;
import ru.trubin23.tasks_mvp_dagger.data.source.local.TasksDatabase;
import ru.trubin23.tasks_mvp_dagger.data.source.local.TasksLocalDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.local.TasksLocalRepository;
import ru.trubin23.tasks_mvp_dagger.data.source.remote.TasksRemoteRepository;

/**
 * Created by Andrey on 22.02.2018.
 */

@Module
public class TasksRepositoryModule {

    private static final int NETWORK_THREAD_COUNT = 3;

    @NonNull
    @Singleton
    @Local
    @Provides
    static TasksLocalDataSource localDataSource(@NonNull TasksDao tasksDao) {
        return new TasksLocalRepository(tasksDao,
                Executors.newSingleThreadExecutor());
    }

    @NonNull
    @Singleton
    @Remote
    @Provides
    static TasksDataSource remoteDataSource() {
        return new TasksRemoteRepository(
                Executors.newFixedThreadPool(NETWORK_THREAD_COUNT));
    }

    @NonNull
    @Singleton
    @Provides
    static TasksDatabase provideDb(@NonNull Application app) {
        return Room.databaseBuilder(app.getApplicationContext(),
                TasksDatabase.class, "Tasks.db").build();
    }

    @NonNull
    @Singleton
    @Provides
    static TasksDao provideTasksDao(@NonNull TasksDatabase db) {
        return db.tasksDao();
    }
}
