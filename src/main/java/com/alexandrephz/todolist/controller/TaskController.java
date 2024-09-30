package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.DTO.TaskUpdateDto;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    public TaskService taskService;


    @GetMapping("/tasks/all")
    public ResponseEntity<ApiResponseDto<?>> getAllTasks() throws TaskServiceLogicException, TaskNotFoundException {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks/new")
    public ResponseEntity<ApiResponseDto<?>> createTask(@RequestBody TaskRegistrationDto taskRegistrationDto)
            throws TaskAlreadyExistsException, TaskServiceLogicException {
        return taskService.createTask(taskRegistrationDto);
    }

    @GetMapping("/tasks")
    public ResponseEntity<ApiResponseDto<?>> findByTaskTitle(@RequestParam String task) throws TaskNotFoundException, TaskServiceLogicException {

        return taskService.findByTaskTitle(task);
    }
    @PatchMapping("/tasks/{UUID}")
    public ResponseEntity<ApiResponseDto<?>> updateTask(@RequestBody TaskUpdateDto taskUpdateDto)
            throws TaskServiceLogicException, TaskNotFoundException {
        return taskService.updateTask(taskUpdateDto);
    }
}
