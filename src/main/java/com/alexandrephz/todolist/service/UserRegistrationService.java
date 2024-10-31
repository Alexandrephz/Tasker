package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.SignupRequestDto;
import com.alexandrephz.todolist.DTO.TaskRegistrationDto;
import com.alexandrephz.todolist.DTO.TaskUpdateDto;
import com.alexandrephz.todolist.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {
    ResponseEntity<ApiResponseDto<?>> registerUser(SignupRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;
}
