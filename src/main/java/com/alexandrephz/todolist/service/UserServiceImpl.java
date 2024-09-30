package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.UserRegistrationDto;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserNotFoundException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import com.alexandrephz.todolist.model.User;
import com.alexandrephz.todolist.model.UserRole;
import com.alexandrephz.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponseDto<?>> createUser(UserRegistrationDto userRegistrationDto) throws UserNotFoundException, UserAlreadyExistsException {

        try {
            if (userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException("Email já cadastrado, por favor escolha outro");
            }
            if (userRepository.findByUsername(userRegistrationDto.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException("Usuário já cadastrado, por favor escolha outro");
            }
        } catch (Exception e) {
            log.error("Error checking user existence: {}", e.getMessage());
            throw new UserServiceLogicException(e.getMessage());
        }

        try {
            userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            User newUser = new User(userRegistrationDto.getUsername(), userRegistrationDto.getFullName(),
                    userRegistrationDto.getPassword(), userRegistrationDto.getEmail(),
                    UserRole.USER);
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Usuário criado com sucesso: " + newUser));
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new UserServiceLogicException(e.getMessage());
        }
    }
}
