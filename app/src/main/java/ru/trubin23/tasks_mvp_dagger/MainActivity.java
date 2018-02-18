package ru.trubin23.tasks_mvp_dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
