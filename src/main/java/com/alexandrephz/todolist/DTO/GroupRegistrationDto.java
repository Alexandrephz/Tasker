package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.User;
import com.alexandrephz.todolist.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GroupRegistrationDto {
    private UUID groupId;
    private String groupName;
    private String groupDescription;
    private UserRole userRole;

}
