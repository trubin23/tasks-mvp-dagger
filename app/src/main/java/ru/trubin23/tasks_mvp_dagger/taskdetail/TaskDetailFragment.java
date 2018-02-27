package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;

/**
 * Created by Andrey on 26.02.2018.
 */

public class TaskDetailFragment extends DaggerFragment
        implements TaskDetailContract.View {

    @Inject
    TaskDetailContract.Presenter mPresenter;

    @Inject
    public TaskDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        ButterKnife.bind(this, root);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }
}
