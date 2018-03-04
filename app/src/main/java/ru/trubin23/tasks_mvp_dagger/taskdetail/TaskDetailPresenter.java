package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Strings;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.Task;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private String mTaskId;

    private TasksRepository mTasksRepository;

    private TaskDetailContract.View mTaskDetailView;

    @Inject
    TaskDetailPresenter(@Nullable String taskId,
                        @NonNull TasksRepository tasksRepository) {
        mTaskId = taskId;
        mTasksRepository = tasksRepository;
    }

    @Override
    public void takeView(@NonNull TaskDetailContract.View view) {
        mTaskDetailView = view;
        openTask();
    }

    @Override
    public void dropView() {
        mTaskDetailView = null;
    }

    private void openTask() {
        if (Strings.isNullOrEmpty(mTaskId)) {
            if (mTaskDetailView != null) {
                mTaskDetailView.showMissingTask();
            }
            return;
        }

        mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
            @Override
            public void onTaskLoaded(@NonNull Task task) {
                if (mTaskDetailView != null) {
                    mTaskDetailView.setTitle(task.getTitle());
                    mTaskDetailView.setDescription(task.getDescription());
                    mTaskDetailView.setDateOfCreation(task.getDateOfCreation());
                    mTaskDetailView.setComplete(task.isCompleted());
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mTaskDetailView != null) {
                    mTaskDetailView.showMissingTask();
                }
            }
        });
    }

    @Override
    public void editTask() {
        if (Strings.isNullOrEmpty(mTaskId)) {
            if (mTaskDetailView != null) {
                mTaskDetailView.showMissingTask();
            }
            return;
        }
        if (mTaskDetailView != null) {
            mTaskDetailView.showEditTask(mTaskId);
        }
    }

    @Override
    public void deleteTask() {
        if (Strings.isNullOrEmpty(mTaskId)) {
            if (mTaskDetailView != null) {
                mTaskDetailView.showMissingTask();
            }
            return;
        }
        mTasksRepository.deleteTask(mTaskId);
        if (mTaskDetailView != null) {
            mTaskDetailView.showTaskDeleted(mTaskId);
        }
    }

    @Override
    public void changeCompletedTask(boolean completed) {
        if (mTaskId != null) {
            mTasksRepository.completeTask(mTaskId, completed);
        }
    }
}
