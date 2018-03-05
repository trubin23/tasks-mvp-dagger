package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 05.03.2018.
 */

class TaskMapper {

    @NonNull
    static Task networkTaskToTask(@NonNull NetworkTask networkTask) {
        return new Task(networkTask.getTitle(), networkTask.getDescription(),
                networkTask.getId(), networkTask.getDateOfCreation(),
                networkTask.getCompleted() != 0);
    }

    @NonNull
    static NetworkTask taskToNetworkTask(@NonNull Task task) {
        return new NetworkTask(task.getId(), task.getTitle(),
                task.getDescription(), task.getDateOfCreation(),
                task.isCompleted());
    }

    @NonNull
    static List<Task> networkTaskListToTaskList(@NonNull List<NetworkTask> networkTaskList) {
        List<Task> taskList = new ArrayList<>();

        for (NetworkTask networkTask : networkTaskList) {
            Task task = networkTaskToTask(networkTask);
            taskList.add(task);
        }

        return taskList;
    }
}
