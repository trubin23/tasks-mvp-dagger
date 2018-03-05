package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        mNetworkIO.execute(() -> RetrofitClient.getTasks(
                new ProcessingResponse<List<NetworkTask>>() {
                    @Override
                    public void responseBody(@NonNull List<NetworkTask> body) {
                        List<Task> tasks = TaskMapper.networkTaskListToTaskList(body);
                        callback.onTasksLoaded(tasks);
                    }

                    @Override
                    public void dataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                }));
        callback.onDataNotAvailable();
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        mNetworkIO.execute(() -> RetrofitClient.getTask(taskId,
                new ProcessingResponse<NetworkTask>() {
                    @Override
                    public void responseBody(@NonNull NetworkTask body) {
                        Task task = TaskMapper.networkTaskToTask(body);
                        callback.onTaskLoaded(task);
                    }

                    @Override
                    public void dataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                }));
    }

    @Override
    public void saveTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

        mNetworkIO.execute(() ->
                RetrofitClient.addTask(networkTask, new ProcessingResponse<>()));
    }

    @Override
    public void updateTask(@NonNull Task task) {
        NetworkTask networkTask = TaskMapper.taskToNetworkTask(task);

        mNetworkIO.execute(() ->
                RetrofitClient.updateTask(networkTask, new ProcessingResponse<>()));
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        mNetworkIO.execute(() ->
                RetrofitClient.daleteTask(taskId, new ProcessingResponse<>()));
    }

    @Override
    public void completeTask(@NonNull String taskId, boolean completed) {

    }
}
