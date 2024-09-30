package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.Comment;
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
public class CommentRegistrationDto {
    private String comment;
    private Boolean haveImage = false;
    private Task task;
    private UUID commentId;

}
