package com.infnet;

import com.infnet.services.TasksService;
import models.Task;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final TasksService taskService = ToDoClient.getInstance().getTasksService();
    private static Task exampleTask = new Task(201, 101, "Tarefa de exemplo", false);

    public static void main(String[] args) {
//        exibirLista();

//        exibirTarefa(1);

//        marcarTarefak(1);

//        criarTarefa(exampleTask);

        listarIncompletas();


    }

    public static void exibirLista() {
        try {
            Call<List<Task>> call = taskService.list();
            Response<List<Task>> response = call.execute();
            if (response.isSuccessful()) {
                List<Task> tasks = response.body();
                if (tasks != null) {
                    for (Task task : tasks) {
                        System.out.println("Tarefa " + task.getId() + ": " + task.getTitle());
                    }
                }
            } else {
                System.out.println("Erro na requisição: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void exibirTarefa(int id) {
        try {
            Call<Task> call = taskService.getTask(id);
            Response<Task> response = call.execute();
            if (response.isSuccessful()) {
                Task task = response.body();
                if (task != null) {
                    System.out.println("Tarefa: " + task.getId());
                    System.out.println("Título: " + task.getTitle());
                    System.out.println("Concluída: " + task.isCompleted());
                    System.out.println("Usuário: " + task.getUserId());
                }
            } else {
                System.out.println("Erro na requisição: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void marcarTarefak(int id) {
        try {
            Call<Task> call = taskService.getTask(id);
            Response<Task> response = call.execute();
            if (response.isSuccessful()) {
                Task task = response.body();
                task.setCompleted(!true);
                System.out.println("Tarefa " + task.getId() + " marcada como concluída");
            } else {
                System.out.println("Erro na requisição: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void criarTarefa( Task newTask) {
        try {
            Call<Task> call = taskService.createTask(newTask);
            Response<Task> response = call.execute();
            if (response.isSuccessful()) {
                Task task = response.body();
                if (task != null) {
                    System.out.println("Tarefa " + task.getId() + " criada");
                }
            } else {
                System.out.println("Erro na requisição: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void listarIncompletas(){
        try {
            Call<List<Task>> call = taskService.listUncompleted();
            Response<List<Task>> response = call.execute();
            if (response.isSuccessful()) {
                List<Task> tasks = response.body();
                if (tasks != null) {
                    for (Task task : tasks) {
                        System.out.println("Tarefa " + task.getId() + ": " + task.getTitle());
                    }
                }
            } else {
                System.out.println("Erro na requisição: " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
