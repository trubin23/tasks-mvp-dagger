package ru.trubin23.tasks_mvp_dagger.addedittask;

import ru.trubin23.tasks_mvp_dagger.BasePresenter;
import ru.trubin23.tasks_mvp_dagger.BaseView;

/**
 * Created by Andrey on 25.02.2018.
 */

public interface AddEditTaskContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
