package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.DTO.TaskUpdateDto;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.model.TaskStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TaskService {
    ResponseEntity<ApiResponseDto<?>> createTask(TaskRegistrationDto newTaskDetails)
        throws TaskAlreadyExistsException, TaskServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllTasks()
        throws TaskNotFoundException, TaskServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> findByTaskTitle(String task)
        throws  TaskNotFoundException, TaskServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateTask(TaskUpdateDto updateTaskDetails)
        throws TaskNotFoundException, TaskServiceLogicException;

}
