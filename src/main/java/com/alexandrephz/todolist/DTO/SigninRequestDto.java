package com.alexandrephz.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequestDto {
    private String password;
    private String username;

    public Object getUsername() { return username; }
}
