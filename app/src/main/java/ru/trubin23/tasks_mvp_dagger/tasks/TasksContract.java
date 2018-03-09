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

        void showTasks(@NonNull List<Task> tasks);

        void showLoadingTasksError();

        void showCompletedTasksCleared();

        void showTaskMarkedActive();

        void showTaskMarkedComplete();

        void showNoActiveTasks();

        void showNoCompletedTasks();

        void showNoTasks();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();
    }

    interface Presenter extends BasePresenter<View> {

        void loadTasks(boolean forceUpdate);

        void changeCompletedTask(@NonNull String taskId, boolean completed);

        void clearCompletedTasks();

        void setFiltering(@NonNull TasksFilter activeTasks);
    }
}
