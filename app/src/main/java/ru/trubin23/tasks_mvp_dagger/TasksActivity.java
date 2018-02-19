package ru.trubin23.tasks_mvp_dagger;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class TasksActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);
    }
}
