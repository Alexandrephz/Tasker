package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.CloseRequestStatus;
import com.alexandrephz.todolist.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CloseRequestDto {
    private String closeDescription;
    private Task task;
}
