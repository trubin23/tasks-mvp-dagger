package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.util.ActivityUtils;

public class AddEditTaskActivity extends DaggerAppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    public static final String EDIT_TASK_ID = "EDIT_TASK_ID";

    @Inject
    AddEditTaskFragment mFragment;

    @Inject
    @Nullable
    String mTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_act);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        AddEditTaskFragment fragment = (AddEditTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (fragment == null) {
            fragment = mFragment;

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.content_frame);
        }
    }
}
