package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.SignupRequestDto;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import com.alexandrephz.todolist.model.Role;
import com.alexandrephz.todolist.model.User;
import com.alexandrephz.todolist.model.UserRole;
import com.alexandrephz.todolist.repository.RoleRepository;
import com.alexandrephz.todolist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(SignupRequestDto newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {

        if (userRepository.existsByUsername(newUserDetails.getUsername())) {
            throw new UserAlreadyExistsException("This username is already registered, choose another one");
        }

        if (userRepository.existsByEmail(newUserDetails.getEmail())) {
            throw new UserAlreadyExistsException("This email is already registered, choose another one");
        }

        User user = new User(newUserDetails.getUsername(),
                newUserDetails.getEmail(),
                encoder.encode(newUserDetails.getPassword()),
                newUserDetails.getFullName());

        Set<String> strRoles = newUserDetails.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(UserRole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(UserRole.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(UserRole.USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Tarefa criada com sucesso " + user));
    }

}
