package ru.trubin23.tasks_mvp_dagger.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.di.ActivityScoped;

/**
 * Created by Andrey on 19.02.2018.
 */

@ActivityScoped
public class TasksFragment extends DaggerFragment implements TasksContract.View {

    @Inject
    TasksContract.Presenter mPresenter;

    @Inject
    public TasksFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);

        return root;
    }
}
