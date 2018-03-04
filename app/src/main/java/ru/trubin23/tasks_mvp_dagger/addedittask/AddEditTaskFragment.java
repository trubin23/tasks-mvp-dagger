package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import ru.trubin23.tasks_mvp_dagger.R;

/**
 * Created by Andrey on 25.02.2018.
 */

public class AddEditTaskFragment extends DaggerFragment
        implements AddEditTaskContract.View {

    @BindView(R.id.add_task_title)
    EditText mTaskTitle;

    @BindView(R.id.add_task_description)
    EditText mTaskDescription;

    @Inject
    AddEditTaskContract.Presenter mPresenter;

    @Inject
    public AddEditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        ButterKnife.bind(this, root);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setOnClickListener(v -> mPresenter.saveTask(
                mTaskTitle.getText().toString(),
                mTaskDescription.getText().toString()));

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

    @Override
    public void showEmptyTaskError() {
        Snackbar.make(mTaskTitle, R.string.empty_task_message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTaskList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setTitle(@NonNull String title) {
        mTaskTitle.setText(title);
    }

    @Override
    public void setDescription(@NonNull String description) {
        mTaskDescription.setText(description);
    }
}
