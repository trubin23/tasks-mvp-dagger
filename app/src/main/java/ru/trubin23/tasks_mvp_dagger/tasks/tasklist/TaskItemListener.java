package ru.trubin23.tasks_mvp_dagger.tasks.tasklist;

import android.support.annotation.NonNull;

/**
 * Created by Andrey on 25.02.2018.
 */

public interface TaskItemListener {

    void onTaskClick(@NonNull String taskId);
}
