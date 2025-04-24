package com.example.houseOfMarkTechBackend.service;

import com.example.houseOfMarkTechBackend.model.Task;
import com.example.houseOfMarkTechBackend.repository.TaskStorage;

import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    public Task addTask(String username, String description) {
        Task task = new Task();
        task.setId(System.currentTimeMillis());
        task.setUsername(username);
        task.setDescription(description);
        task.setCompleted(false);
        TaskStorage.tasks.add(task);
        return task;
    }

    public List<Task> getTasks(String username) {
        return TaskStorage.tasks.stream()
                .filter(task -> task.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public boolean deleteTask(String username, long taskId) {
        return TaskStorage.tasks.removeIf(task -> task.getId() == taskId && task.getUsername().equals(username));
    }
}
