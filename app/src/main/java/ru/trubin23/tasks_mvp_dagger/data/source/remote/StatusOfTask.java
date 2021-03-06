package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 06.03.2018.
 */

class StatusOfTask {

    @SerializedName("completed")
    @Expose
    private Integer mCompleted;

    StatusOfTask(boolean completed) {
        mCompleted = booleanToInteger(completed);
    }

    @NonNull
    Integer getCompleted() {
        return mCompleted;
    }

    void setCompleted(@NonNull Integer completed) {
        mCompleted = completed;
    }

    @NonNull
    static Integer booleanToInteger(boolean completed) {
        return completed ? 1 : 0;
    }
}
