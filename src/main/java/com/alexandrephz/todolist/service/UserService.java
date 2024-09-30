package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.UserRegistrationDto;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<ApiResponseDto<?>> createUser(UserRegistrationDto userRegistrationDto)
        throws UserNotFoundException, UserAlreadyExistsException;
}
