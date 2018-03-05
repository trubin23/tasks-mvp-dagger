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

    @NonNull
    String getId() {
        return mId;
    }

    void setId(@NonNull String id) {
        mId = id;
    }

    @NonNull
    String getTitle() {
        return mTitle;
    }

    void setTitle(@NonNull String title) {
        mTitle = title;
    }

    @NonNull
    String getDescription() {
        return mDescription;
    }

    void setDescription(@NonNull String description) {
        mDescription = description;
    }

    @NonNull
    String getDateOfCreation() {
        return mDateOfCreation;
    }

    void setDateOfCreation(@NonNull String dateOfCreation) {
        mDateOfCreation = dateOfCreation;
    }

    @NonNull
    Byte getCompleted() {
        return mCompleted;
    }

    void setCompleted(@NonNull Byte completed) {
        mCompleted = completed;
    }
}
