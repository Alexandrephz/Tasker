package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.DTO.UserRegistrationDto;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import com.alexandrephz.todolist.service.TaskService;
import com.alexandrephz.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping("/user/new")
    public ResponseEntity<ApiResponseDto<?>> createTask(@RequestBody UserRegistrationDto userRegistrationDto)
            throws UserAlreadyExistsException, UserServiceLogicException {
        return userService.createUser(userRegistrationDto);
    }
}
