package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

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
        loadTasks();
    }

    @Override
    public void dropView() {
        mTasksView = null;
    }

    public void loadTasks() {
        if (mRefreshTasks){
            mTasksRepository.refreshTasks();
            mRefreshTasks = false;
        }

        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<Task> tasks) {
                mTasksView.showTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                mTasksView.showLoadingTasksError();
            }
        });
    }
}
