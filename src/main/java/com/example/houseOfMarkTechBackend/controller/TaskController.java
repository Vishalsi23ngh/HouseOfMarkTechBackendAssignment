package com.example.houseOfMarkTechBackend.controller;

import com.example.houseOfMarkTechBackend.model.Task;
import com.example.houseOfMarkTechBackend.model.TaskRequest;
import com.example.houseOfMarkTechBackend.model.User;
import com.example.houseOfMarkTechBackend.service.AuthService;
import com.example.houseOfMarkTechBackend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
    public ResponseEntity<?> addTask(@RequestBody TaskRequest request,
                                     HttpServletRequest httpReq) {
        try {
            Task task = taskService.addTask(getUser(httpReq).getUsername(),
                    request.getDescription());
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            // Log e if you like, then return:
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Your request is invalid, please try again");
        }
    }


    @GetMapping
    public List<Task> getTasks(HttpServletRequest request) {
        User user = getUser(request);
        if(user == null){
            return new ArrayList<>();
        }
        return taskService.getTasks(user.getUsername());
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable long id, HttpServletRequest request) {
        User user = getUser(request);
        if (user == null){
            return "no user exist with this id";
        }
        boolean removed = taskService.deleteTask(user.getUsername(), id);
        return removed ? "Task deleted" : "Task not found";
    }
}