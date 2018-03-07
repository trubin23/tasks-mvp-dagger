package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;

/**
 * Created by Andrey on 19.02.2018.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private final TasksRepository mTasksRepository;

    private TasksContract.View mTasksView;

    private boolean mRefreshTasks = true;

    @Inject
    TasksPresenter(@NonNull TasksRepository tasksRepository) {
        mTasksRepository = tasksRepository;
    }

    @Override
    public void takeView(@NonNull TasksContract.View view) {
        mTasksView = view;
        loadTasks(mRefreshTasks);
        mRefreshTasks = false;
    }

    @Override
    public void dropView() {
        mTasksView = null;
    }

    public void loadTasks(boolean forceUpdate) {
        if (forceUpdate) {
            mTasksRepository.refreshTasks();
        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                if (mTasksView != null) {
                    mTasksView.showTasks(tasks);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mTasksView != null) {
                    mTasksView.showLoadingTasksError();
                }
            }
        });
    }

    @Override
    public void changeCompletedTask(@NonNull String taskId, boolean completed) {
        mTasksRepository.completeTask(taskId, completed);
    }

    @Override
    public void clearCompletedTasks() {
        mTasksRepository.clearCompletedTasks();
    }
}
