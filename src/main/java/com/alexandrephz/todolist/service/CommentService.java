package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.*;
import com.alexandrephz.todolist.exceptions.CommentServiceLogicException;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.exceptions.TaskServiceLogicException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    ResponseEntity<ApiResponseDto<?>> createComment(CommentRegistrationDto newCommentDetails, FileUploadDto newFileDetails)
            throws CommentServiceLogicException, TaskNotFoundException;
    ResponseEntity<ApiResponseDto<?>> updateComment(CommentUpdateDto commentUpdateDto)
            throws CommentServiceLogicException, CommentServiceLogicException, TaskNotFoundException;
}
