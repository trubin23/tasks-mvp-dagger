package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;

/**
 * Created by Andrey on 25.02.2018.
 */

public class AddEditTaskPresenter implements AddEditTaskContract.Presenter {

    private String mTaskId;

    private TasksDataSource mTasksRepository;

    private AddEditTaskContract.View mAddEditTaskView;

    @Inject
    AddEditTaskPresenter(@Nullable String taskId, @NonNull TasksRepository tasksRepository) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
    }

    @Override
    public void takeView(@NonNull AddEditTaskContract.View view) {
        mAddEditTaskView = view;
        populateTask();
    }

    @Override
    public void dropView() {
        mAddEditTaskView = null;
    }

    private void populateTask() {
        if (isNewTask()) {
            return;
        }

        mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(@NonNull Task task) {
                if (mAddEditTaskView != null) {
                    mAddEditTaskView.setTitle(task.getTitle());
                    mAddEditTaskView.setDescription(task.getDescription());
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mAddEditTaskView != null) {
                    mAddEditTaskView.showEmptyTaskError();
                }
            }
        });
    }

    private boolean isNewTask() {
        return mTaskId == null;
    }

    @Override
    public void saveTask(@NonNull String title, @NonNull String description) {
        Task task;
        if (isNewTask()) {
            task = new Task(title, description);
        } else {
            task = new Task(title, description, mTaskId);
        }

        if (task.isEmpty()) {
            if (mAddEditTaskView != null) {
                mAddEditTaskView.showEmptyTaskError();
            }
        } else {
            if (isNewTask()) {
                mTasksRepository.saveTask(task);
            } else {
                mTasksRepository.updateTask(task);
            }
            if (mAddEditTaskView != null) {
                mAddEditTaskView.showTaskList();
            }
        }
    }
}
