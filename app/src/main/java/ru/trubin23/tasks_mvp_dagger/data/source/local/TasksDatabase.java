package ru.trubin23.tasks_mvp_dagger.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 21.02.2018.
 */

@Database(entities = {Task.class}, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract TasksDao tasksDao();
}
