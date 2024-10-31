package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.SigninRequestDto;
import com.alexandrephz.todolist.DTO.SignupRequestDto;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserIncorrectUsernameOrPasswordException;
import com.alexandrephz.todolist.exceptions.UserNotFoundException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserLoginService {
    ResponseEntity<ApiResponseDto<?>> signinUser (SigninRequestDto newUserDetails)
            throws UserNotFoundException, UserIncorrectUsernameOrPasswordException, UserServiceLogicException;
}
