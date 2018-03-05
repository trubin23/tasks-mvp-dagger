package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 05.03.2018.
 */

class TaskMapper {

    static Task networkTaskToTask(@NonNull NetworkTask networkTask){
        return new Task(networkTask.getTitle(), networkTask.getDescription(),
                networkTask.getId(), networkTask.getDateOfCreation(),
                networkTask.getCompleted() != 0);
    }
}
