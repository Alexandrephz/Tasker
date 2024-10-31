package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.SigninRequestDto;
import com.alexandrephz.todolist.DTO.SignupRequestDto;
import com.alexandrephz.todolist.exceptions.UserIncorrectUsernameOrPasswordException;
import com.alexandrephz.todolist.exceptions.UserNotFoundException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import com.alexandrephz.todolist.service.UserLoginService;
import com.alexandrephz.todolist.service.UserRegistrationService;
import com.alexandrephz.todolist.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SigninRequestDto loginRequest)
    throws UserIncorrectUsernameOrPasswordException, UserNotFoundException, UserServiceLogicException
    {
        return userLoginService.signinUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@RequestBody SignupRequestDto signUpRequest)
    throws UserNotFoundException, UserServiceLogicException {

        return userRegistrationService.registerUser(signUpRequest);
    }
}