package com.alexandrephz.todolist.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL) // To exclude null values from serialization
public class TaskUpdateDto {
    private UUID id;
    private String taskTitle;
    private String taskDescription;
    private Date taskEnd;
}
