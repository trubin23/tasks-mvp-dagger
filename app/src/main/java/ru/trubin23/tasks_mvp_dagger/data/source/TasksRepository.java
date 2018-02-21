package ru.trubin23.tasks_mvp_dagger.data.source;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import ru.trubin23.tasks_mvp_dagger.data.Task;
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

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void updateTask(@NonNull Task task) {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
