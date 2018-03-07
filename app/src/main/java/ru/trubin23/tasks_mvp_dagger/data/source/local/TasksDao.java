package ru.trubin23.tasks_mvp_dagger.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 21.02.2018.
 */

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    List<Task> getTasks();

    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    Task getTaskById(String taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("DELETE FROM tasks WHERE taskId = :taskId")
    void deleteTaskById(String taskId);

    @Query("DELETE FROM tasks WHERE completed=1")
    int deleteCompletedTasks();

    @Query("DELETE FROM tasks")
    void deleteTasks();

    @Query("UPDATE tasks SET completed = :completed WHERE taskId = :taskId")
    void updateCompleted(String taskId, boolean completed);
}
