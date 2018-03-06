package ru.trubin23.tasks_mvp_dagger.data.source.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Andrey on 04.03.2018.
 */

interface RemoteService {

    @GET("/api_dagger/tasks")
    Call<List<NetworkTask>> getTasks();

    @GET("/api_dagger/tasks/{id}")
    Call<NetworkTask> getTask(@Path("id") String id);

    @POST("/api_dagger/tasks")
    Call<NetworkTask> addTask(@Body NetworkTask task);

    @PUT("/api_dagger/tasks/{id}")
    Call<NetworkTask> updateTask(@Path("id") String id, @Body NetworkTask task);

    @PUT("/api_dagger/tasks/{id}")
    Call<NetworkTask> completeTask(@Path("id") String id, @Body StatusOfTask task);

    @DELETE("/api_dagger/tasks/{id}")
    Call<NetworkTask> deleteTask(@Path("id") String id);
}
