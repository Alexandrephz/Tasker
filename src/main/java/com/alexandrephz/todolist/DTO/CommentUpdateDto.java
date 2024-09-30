package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.Task;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentUpdateDto {
    private UUID id;
    private String comment;
    private Task task;
}
