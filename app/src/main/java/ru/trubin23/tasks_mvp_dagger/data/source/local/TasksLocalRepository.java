package ru.trubin23.tasks_mvp_dagger.data.source.local;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 21.02.2018.
 */

@Singleton
public class TasksLocalRepository implements TasksLocalDataSource {

    private final TasksDao mTasksDao;

    private final Executor mDiskIO;

    @Inject
    public TasksLocalRepository(@NonNull TasksDao tasksDao,
                                @NonNull Executor diskIO) {
        mTasksDao = tasksDao;
        mDiskIO = diskIO;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        mDiskIO.execute(() -> {
            List<Task> tasks = mTasksDao.getTasks();
            if (tasks.isEmpty()) {
                callback.onDataNotAvailable();
            } else {
                callback.onTasksLoaded(tasks);
            }
        });
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        mDiskIO.execute(() -> {
            Task task = mTasksDao.getTaskById(taskId);
            if (task == null) {
                callback.onDataNotAvailable();
            } else {
                callback.onTaskLoaded(task);
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mDiskIO.execute(() -> mTasksDao.insertTask(task));
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mDiskIO.execute(() -> mTasksDao.updateTask(task));
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        mDiskIO.execute(() -> mTasksDao.deleteTaskById(taskId));
    }

    @Override
    public void deleteAllTasks() {
        mDiskIO.execute(mTasksDao::deleteTasks);
    }

    @Override
    public void completeTask(@NonNull String taskId, boolean completed) {

    }
}
