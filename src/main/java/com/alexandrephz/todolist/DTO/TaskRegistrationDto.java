package com.alexandrephz.todolist.DTO;


import com.alexandrephz.todolist.model.Group;
import com.alexandrephz.todolist.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRegistrationDto {
    private String taskTitle;
    private String taskDescription;
    private Date taskEnd;
    private UUID groupId;
    private TaskStatus taskStatus;
}
