package ru.trubin23.tasks_mvp_dagger.data.source.local;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 21.02.2018.
 */

@Singleton
public class TasksLocalRepository implements TasksLocalDataSource {

    private final TasksDao mTasksDao;

    private final Executor mDiskIO;

    public TasksLocalRepository(@NonNull TasksDao tasksDao,
                                @NonNull Executor diskIO) {
        mTasksDao = tasksDao;
        mDiskIO = diskIO;
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

    @Override
    public void deleteAllTasks() {

    }
}
