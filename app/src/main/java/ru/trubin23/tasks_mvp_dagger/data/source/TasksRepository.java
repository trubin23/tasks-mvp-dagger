package ru.trubin23.tasks_mvp_dagger.data.source;

import javax.inject.Singleton;

import ru.trubin23.tasks_mvp_dagger.data.source.local.TasksLocalDataSource;

/**
 * Created by Andrey on 20.02.2018.
 */

@Singleton
public class TasksRepository implements TasksDataSource {

    private final TasksDataSource mTaskRemoteDataSource;
    private final TasksLocalDataSource mTaskLocalDataSource;

    public TasksRepository(@Remote TasksDataSource taskRemoteDataSource,
                           @Local TasksLocalDataSource taskLocalDataSource) {
        mTaskRemoteDataSource = taskRemoteDataSource;
        mTaskLocalDataSource = taskLocalDataSource;
    }
}
