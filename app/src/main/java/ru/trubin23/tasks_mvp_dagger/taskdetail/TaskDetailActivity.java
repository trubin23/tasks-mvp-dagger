package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.util.ActivityUtils;

public class TaskDetailActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Inject
    TaskDetailFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        TaskDetailFragment taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (taskDetailFragment == null){
            taskDetailFragment = mFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.content_frame);
        }
    }
}
