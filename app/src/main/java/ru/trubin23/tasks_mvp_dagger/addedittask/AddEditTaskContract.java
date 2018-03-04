package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;

/**
 * Created by Andrey on 25.02.2018.
 */

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter> {

        void showEmptyTaskError();

        void showTaskList();

        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);
    }

    interface Presenter extends BasePresenter<View> {

        void saveTask(@NonNull String title, @NonNull String description);
    }
}
