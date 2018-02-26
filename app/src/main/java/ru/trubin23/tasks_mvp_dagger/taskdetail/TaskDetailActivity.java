package ru.trubin23.tasks_mvp_dagger.taskdetail;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import ru.trubin23.tasks_mvp_dagger.R;

public class TaskDetailActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);
    }
}
