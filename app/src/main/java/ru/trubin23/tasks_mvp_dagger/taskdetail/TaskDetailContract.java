package ru.trubin23.tasks_mvp_dagger.taskdetail;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;

/**
 * Created by Andrey on 26.02.2018.
 */

public interface TaskDetailContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
