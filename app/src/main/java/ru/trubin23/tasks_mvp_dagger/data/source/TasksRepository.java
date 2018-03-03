package ru.trubin23.tasks_mvp_dagger.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
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

    private Map<String, Task> mCachedTasks;

    private boolean mCacheIsDirty = false;

    @Inject
    TasksRepository(@Remote TasksDataSource taskRemoteDataSource,
                    @Local TasksLocalDataSource taskLocalDataSource) {
        mTaskRemoteDataSource = taskRemoteDataSource;
        mTaskLocalDataSource = taskLocalDataSource;
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {
        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty) {
            getTasksFromRemoteDataSource(callback);
        } else {
            getTasksFromLocalDataSource(callback, true);
        }
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        Task cachedTask = getTaskWithId(taskId);

        if (cachedTask != null) {
            callback.onTaskLoaded(cachedTask);
            return;
        }

        mTaskLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(@NonNull Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                getTaskFromRemoteDataSource(taskId, callback);
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {
        mTaskRemoteDataSource.saveTask(task);
        mTaskLocalDataSource.saveTask(task);

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(@NonNull Task task) {
        mTaskRemoteDataSource.updateTask(task);
        mTaskLocalDataSource.updateTask(task);

        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.put(task.getId(), task);
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        mTaskRemoteDataSource.deleteTask(taskId);
        mTaskLocalDataSource.deleteTask(taskId);

        if (mCachedTasks != null) {
            mCachedTasks.remove(taskId);
        }
    }

    @Override
    public void completeTask(@NonNull String taskId, boolean completed) {
        mTaskRemoteDataSource.completeTask(taskId, completed);
        mTaskLocalDataSource.completeTask(taskId, completed);
    }

    public void refreshTasks() {
        mCacheIsDirty = true;
    }

    private void getTasksFromLocalDataSource(@NonNull LoadTasksCallback callback,
                                             boolean handleErrors) {
        mTaskLocalDataSource.getTasks(new LoadTasksCallback() {

            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                refreshCache(tasks);
                callback.onTasksLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                if (handleErrors) {
                    getTasksFromRemoteDataSource(callback);
                } else {
                    callback.onDataNotAvailable();
                }
            }
        });
    }

    private void getTasksFromRemoteDataSource(@NonNull LoadTasksCallback callback) {
        mTaskRemoteDataSource.getTasks(new LoadTasksCallback() {

            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            }

            @Override
            public void onDataNotAvailable() {
                getTasksFromLocalDataSource(callback, false);
            }
        });
    }

    private void refreshCache(@NonNull List<Task> tasks) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();

        for (Task task : tasks) {
            mCachedTasks.put(task.getId(), task);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(@NonNull List<Task> tasks) {
        mTaskLocalDataSource.deleteAllTasks();
        for (Task task : tasks) {
            mTaskLocalDataSource.saveTask(task);
        }
    }

    @Nullable
    private Task getTaskWithId(@NonNull String taskId) {
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(taskId);
        }
    }

    private void getTaskFromRemoteDataSource(@NonNull String taskId,
                                             @NonNull GetTaskCallback callback) {
        mTaskRemoteDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(@NonNull Task task) {
                if (mCachedTasks == null) {
                    mCachedTasks = new LinkedHashMap<>();
                }
                mCachedTasks.put(task.getId(), task);

                mTaskLocalDataSource.saveTask(task);

                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
