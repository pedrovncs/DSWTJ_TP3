package com.infnet.services;
import models.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface TasksService {
    @GET("/todos")
    Call<List<Task>> list();

    @GET("/todos/{id}")
    Call<Task> getTask(@Path("id") int id);

    @GET("/posts?completed=false")
    Call<List<Task>> listUncompleted();

    @POST("/posts")
    Call<Task> createTask(@Body Task task);

}

