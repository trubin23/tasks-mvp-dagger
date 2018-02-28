package ru.trubin23.tasks_mvp_dagger.tasks;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;

/**
 * Created by Andrey on 19.02.2018.
 */

public interface TasksContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

        void loadTasks();
    }
}
