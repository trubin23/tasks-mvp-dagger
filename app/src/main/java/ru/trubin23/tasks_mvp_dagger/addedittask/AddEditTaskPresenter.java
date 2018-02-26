package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;
import ru.trubin23.tasks_mvp_dagger.data.source.TasksRepository;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 25.02.2018.
 */

@ActivityScoped
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
    }

    @Override
    public void dropView() {
        mAddEditTaskView = null;
    }
}
