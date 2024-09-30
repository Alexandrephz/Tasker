package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.CloseRequestDto;
import com.alexandrephz.todolist.DTO.RequestReplyDto;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.exceptions.CloseRequestException;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.service.CloseRequestService;
import com.alexandrephz.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CloseRequestController {
    @Autowired
    public CloseRequestService closeRequestService;

    @PostMapping("/request/new")
    public ResponseEntity<ApiResponseDto<?>> createTask(@RequestBody CloseRequestDto closeRequestDto)
            throws CloseRequestException, TaskNotFoundException {
        return closeRequestService.createRequest(closeRequestDto);
    }

    @PostMapping("/request/close")
    public ResponseEntity<ApiResponseDto<?>> decideRequest(@RequestBody RequestReplyDto requestReplyDto)
            throws CloseRequestException {
        return closeRequestService.decideRequest(requestReplyDto);
    }

    @GetMapping("/request")
    public ResponseEntity<ApiResponseDto<?>> findByTaskTitle(@RequestParam UUID close_request)
            throws TaskNotFoundException, TaskServiceLogicException {

        return closeRequestService.listRequest(close_request);
    }
}
