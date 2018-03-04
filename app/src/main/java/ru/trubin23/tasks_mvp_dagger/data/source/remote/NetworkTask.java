package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 04.03.2018.
 */

public class NetworkTask {

    @SerializedName("id")
    @Expose
    private String mId;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("dateOfCreation")
    @Expose
    private String mDateOfCreation;

    @SerializedName("completed")
    @Expose
    private Byte mCompleted;

    NetworkTask(@NonNull String title, @NonNull String description,
                @NonNull String id, @NonNull String dateOfCreation,
                @NonNull Byte completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDateOfCreation = dateOfCreation;
        mCompleted = completed;
    }

    String getId() {
        return mId;
    }

    void setId(String id) {
        mId = id;
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    String getDescription() {
        return mDescription;
    }

    void setDescription(String description) {
        mDescription = description;
    }

    String getDateOfCreation() {
        return mDateOfCreation;
    }

    void setDateOfCreation(String dateOfCreation) {
        mDateOfCreation = dateOfCreation;
    }

    Byte getCompleted() {
        return mCompleted;
    }

    void setCompleted(Byte completed) {
        mCompleted = completed;
    }
}
