package com.alexandrephz.todolist.controller;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.CommentRegistrationDto;
import com.alexandrephz.todolist.DTO.CommentUpdateDto;
import com.alexandrephz.todolist.DTO.FileUploadDto;
import com.alexandrephz.todolist.exceptions.*;
import com.alexandrephz.todolist.service.CommentService;
import com.alexandrephz.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    public CommentService commentService;

    @Autowired
    public TaskService taskService;

    @GetMapping("/comment")
    public ResponseEntity<ApiResponseDto<?>> findCommentsByTask(@RequestParam String Task) throws TaskNotFoundException, TaskServiceLogicException {
        return taskService.findByTaskTitle(Task);
    }

    @PostMapping("/comment/new")
    public ResponseEntity<ApiResponseDto<?>> createNewComment(@ModelAttribute CommentRegistrationDto commentRegistrationDto,
                                                              @RequestParam(value = "fileComment", required = false) MultipartFile fileComment)
            throws CommentServiceLogicException, FileServiceUploadException, TaskNotFoundException {
        FileUploadDto fileUploadDto = new FileUploadDto(fileComment);
        return commentService.createComment(commentRegistrationDto, fileUploadDto);
    }

    @PatchMapping("/comment/{uuid}")
    public ResponseEntity<ApiResponseDto<?>> updateComment(@RequestBody CommentUpdateDto commentUpdateDto)
            throws CommentServiceLogicException, CommentNotFoundException, TaskNotFoundException {
        return commentService.updateComment(commentUpdateDto);
    }
}
