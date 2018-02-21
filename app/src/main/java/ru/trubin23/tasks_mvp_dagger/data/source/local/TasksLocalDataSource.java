package ru.trubin23.tasks_mvp_dagger.data.source.local;

import ru.trubin23.tasks_mvp_dagger.data.source.TasksDataSource;

/**
 * Created by Andrey on 20.02.2018.
 */

public interface TasksLocalDataSource extends TasksDataSource {

    void deleteAllTasks();
}
