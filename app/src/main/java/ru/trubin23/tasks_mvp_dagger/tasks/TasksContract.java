package ru.trubin23.tasks_mvp_dagger.tasks;

import android.support.annotation.NonNull;

import java.util.List;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;
import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 19.02.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

        void showLoadingTasksError();

        void showTasks(@NonNull List<Task> tasks);
    }

    interface Presenter extends BasePresenter<View> {

        void loadTasks();
    }
}
