package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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

    private TasksFilter mCurrentFiltering = TasksFilter.ALL_TASKS;

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
                List<Task> tasksToShow = filterTask(tasks);

                showTasks(tasksToShow);
            }

            @Override
            public void onDataNotAvailable() {
                if (mTasksView != null) {
                    mTasksView.showLoadingTasksError();
                }
            }
        });
    }

    @NonNull
    private List<Task> filterTask(@NonNull List<Task> tasks) {
        List<Task> tasksToShow = new ArrayList<>();

        for (Task task : tasks) {
            switch (mCurrentFiltering) {
                case ACTIVE_TASKS:
                    if (!task.isCompleted()) {
                        tasksToShow.add(task);
                    }
                    break;
                case COMPLETED_TASKS:
                    if (task.isCompleted()) {
                        tasksToShow.add(task);
                    }
                    break;
                case ALL_TASKS:
                    tasksToShow.add(task);
                    break;
                default:
                    tasksToShow.add(task);
                    break;
            }
        }

        return tasksToShow;
    }

    private void showTasks(@NonNull List<Task> tasks) {
        if (tasks.isEmpty()) {
            showEmptyTasks();
        } else {
            if (mTasksView != null) {
                mTasksView.showTasks(tasks);
            }
            showFilterLabel();
        }
    }

    private void showEmptyTasks() {
        if (mTasksView == null) {
            return;
        }
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showNoActiveTasks();
                break;
            case COMPLETED_TASKS:
                mTasksView.showNoCompletedTasks();
                break;
            case ALL_TASKS:
                mTasksView.showNoTasks();
                break;
        }
    }

    private void showFilterLabel() {
        if (mTasksView == null) {
            return;
        }
        switch (mCurrentFiltering) {
            case ACTIVE_TASKS:
                mTasksView.showActiveFilterLabel();
                break;
            case COMPLETED_TASKS:
                mTasksView.showCompletedFilterLabel();
                break;
            case ALL_TASKS:
                mTasksView.showAllFilterLabel();
                break;
        }
    }

    @Override
    public void changeCompletedTask(@NonNull String taskId, boolean completed) {
        mTasksRepository.completeTask(taskId, completed);
        if (mTasksView != null) {
            if (completed) {
                mTasksView.showTaskMarkedComplete();
            } else {
                mTasksView.showTaskMarkedActive();
            }
        }
    }

    @Override
    public void clearCompletedTasks() {
        mTasksRepository.clearCompletedTasks();
        if (mTasksView != null) {
            mTasksView.showCompletedTasksCleared();
        }
        loadTasks(false);
    }

    @Override
    public void setFiltering(@NonNull TasksFilter activeTasks) {

    }
}
