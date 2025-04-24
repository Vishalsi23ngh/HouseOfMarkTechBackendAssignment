package com.example.houseOfMarkTechBackend.controller;

import com.example.houseOfMarkTechBackend.model.Task;
import com.example.houseOfMarkTechBackend.model.TaskRequest;
import com.example.houseOfMarkTechBackend.model.User;
import com.example.houseOfMarkTechBackend.service.AuthService;
import com.example.houseOfMarkTechBackend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final AuthService authService = new AuthService();
    private final TaskService taskService = new TaskService();

    private User getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return authService.getUserByToken(token);
    }

    @PostMapping
    public Task addTask(@RequestBody TaskRequest request, HttpServletRequest httpReq) {
        User user = getUser(httpReq);
        return taskService.addTask(user.getUsername(), request.getDescription());
    }

    @GetMapping
    public List<Task> getTasks(HttpServletRequest request) {
        User user = getUser(request);
        return taskService.getTasks(user.getUsername());
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id, HttpServletRequest request) {
        User user = getUser(request);
        boolean removed = taskService.deleteTask(user.getUsername(), id);
        return removed ? "Task deleted" : "Task not found";
    }
}