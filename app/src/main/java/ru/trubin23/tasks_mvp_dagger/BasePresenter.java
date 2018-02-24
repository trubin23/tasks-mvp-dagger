package ru.trubin23.tasks_mvp_dagger;

/**
 * Created by Andrey on 19.02.2018.
 */

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
