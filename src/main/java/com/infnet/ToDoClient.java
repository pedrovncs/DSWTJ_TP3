package com.infnet;
import com.infnet.services.TasksService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoClient {
    private Retrofit retrofit;
    private static ToDoClient instance = null;

    private ToDoClient() {
        String baseUrl = "https://jsonplaceholder.typicode.com";
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ToDoClient getInstance() {
        if (instance == null) {
            instance = new ToDoClient();
        }
        return instance;
    }

    public TasksService getTasksService() {
        return retrofit.create(TasksService.class);
    }


}
