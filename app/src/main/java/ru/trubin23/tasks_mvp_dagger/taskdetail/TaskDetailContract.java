package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;

/**
 * Created by Andrey on 26.02.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

        void showMissingTask();

        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);

        void setDateOfCreation(@NonNull String dateOfCreation);
    }

    interface Presenter extends BasePresenter<View> {

        void editTask();

        void deleteTask();
    }
}
