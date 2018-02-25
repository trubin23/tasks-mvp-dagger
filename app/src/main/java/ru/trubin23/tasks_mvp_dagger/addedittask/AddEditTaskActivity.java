package ru.trubin23.tasks_mvp_dagger.addedittask;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import ru.trubin23.tasks_mvp_dagger.R;

public class AddEditTaskActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_act);
    }
}
