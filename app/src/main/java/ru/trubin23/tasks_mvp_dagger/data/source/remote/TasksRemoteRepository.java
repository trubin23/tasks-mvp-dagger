package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;

/**
 * Created by Andrey on 21.02.2018.
 */

@Singleton
public class TasksRemoteRepository implements TasksDataSource {

    private final Executor mNetworkIO;

    public TasksRemoteRepository(@NonNull Executor networkIO) {
        mNetworkIO = networkIO;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        callback.onDataNotAvailable();
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
