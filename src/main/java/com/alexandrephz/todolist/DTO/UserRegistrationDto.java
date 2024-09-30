package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private UserRole userRole;

}
