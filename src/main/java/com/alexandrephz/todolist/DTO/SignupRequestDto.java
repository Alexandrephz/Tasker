package com.alexandrephz.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String fullName;
    private String username;
    private String email;
    private Set<String> role;
    private String password;
}
