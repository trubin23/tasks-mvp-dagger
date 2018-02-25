package ru.trubin23.tasks_mvp_dagger.tasks.tasklist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.trubin23.tasks_mvp_dagger.R;
import ru.trubin23.tasks_mvp_dagger.data.Task;

/**
 * Created by Andrey on 25.02.2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksHolder> {

    private List<Task> mTasks;
    private TaskItemListener mTaskItemListener;

    public TasksAdapter(@NonNull TaskItemListener taskItemListener) {
        mTasks = new ArrayList<>();
        mTaskItemListener = taskItemListener;
    }

    @Override
    public TasksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TasksHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TasksHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.setTask(task);
        holder.itemView.setOnClickListener(v -> mTaskItemListener.onTaskClick(task.getId()));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void setTasks(@NonNull List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    class TasksHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView mTitleTV;

        @BindView(R.id.item_date)
        TextView mDateTV;

        TasksHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setTask(@NonNull Task task) {
            mTitleTV.setText(task.getTitle());
            mDateTV.setText(task.getDateOfCreation());
        }
    }
}
