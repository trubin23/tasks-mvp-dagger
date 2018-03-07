package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.util.ActivityUtils;

public class TaskDetailActivity extends DaggerAppCompatActivity {

    public static final String TASK_ID = "TASK_ID";

    @Inject
    TaskDetailFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (taskDetailFragment == null) {
            taskDetailFragment = mFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.content_frame);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
