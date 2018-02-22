package ru.trubin23.tasks_mvp_dagger.data.source;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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
        if (mCachedTasks!=null && !mCacheIsDirty){
            callback.onTasksLoaded(new ArrayList<>(mCachedTasks.values()));
            return;
        }

        if (mCacheIsDirty){
            getTasksFromRemoteDataSource(callback);
            return;
        }

        mTaskLocalDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                refreshCache(tasks);
                callback.onTasksLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                getTasksFromRemoteDataSource(callback);
            }
        });
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

    public void refreshTasks(){
        mCacheIsDirty = true;
    }

    private void getTasksFromRemoteDataSource(LoadTasksCallback callback) {

    }

    private void refreshCache(List<Task> tasks) {

    }
}
