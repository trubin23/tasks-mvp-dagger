package ru.trubin23.tasks_mvp_dagger.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 20.02.2018.
 */

public interface TasksDataSource {

    interface LoadTasksCallback {

        void onTasksLoaded(@NonNull List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback{

        void onTaskLoaded(@NonNull Task task);

        void onDataNotAvailable();
    }

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void updateTask(@NonNull Task task);

    void deleteTask(@NonNull String taskId);
}
