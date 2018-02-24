package ru.trubin23.tasks_mvp_dagger;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 19.02.2018.
 */

public interface BasePresenter<T> {

    void takeView(@NonNull T view);

    void dropView();
}
