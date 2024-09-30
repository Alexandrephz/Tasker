package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.*;
import com.alexandrephz.todolist.exceptions.CommentNotFoundException;
import com.alexandrephz.todolist.exceptions.CommentServiceLogicException;
import com.alexandrephz.todolist.exceptions.TaskAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.model.Comment;
import com.alexandrephz.todolist.model.Task;
import com.alexandrephz.todolist.model.TaskStatus;
import com.alexandrephz.todolist.repository.CommentRepository;
import com.alexandrephz.todolist.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<ApiResponseDto<?>> createComment(CommentRegistrationDto newCommentDetails, FileUploadDto newUploadFileDetails) throws CommentServiceLogicException, TaskNotFoundException {
        try {
            Task task = taskRepository.getReferenceById(newCommentDetails.getTask().getId());
            if ( task.getStatus() != TaskStatus.CANCELLED && task.getStatus() != TaskStatus.FINISHED) {
                if (!newCommentDetails.getHaveImage()) {
                    Comment comment = new Comment(newCommentDetails.getComment(), false, newCommentDetails.getTask());
                    commentRepository.save(comment);
                    task.setStatus(TaskStatus.IN_PROGRESS);
                    taskRepository.save(task);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Comentario adicionado com sucesso" + comment));
                } else {
                    Comment comment = new Comment(newCommentDetails.getComment(), true, newCommentDetails.getTask());
                    commentRepository.save(comment);
                    task.setStatus(TaskStatus.IN_PROGRESS);
                    taskRepository.save(task);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Comentario adicionado com sucesso" + comment));
                }
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(),"Não foi possivel adicionar um comentario devido ao status da tarefa"));
        } catch (Exception e){
            throw new TaskNotFoundException(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateComment(CommentUpdateDto commentUpdateDto) throws CommentServiceLogicException, CommentServiceLogicException, TaskNotFoundException {
        try {
            Task task = taskRepository.getReferenceById(commentUpdateDto.getTask().getId());
            if ( task.getStatus() != TaskStatus.CANCELLED && task.getStatus() != TaskStatus.FINISHED) {
                Comment comment = commentRepository.getReferenceById(commentUpdateDto.getId());
                objectMapper.updateValue(comment, commentUpdateDto);
                commentRepository.save(comment);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "Tarefa criada com sucesso " + comment));
            }
            } catch (Exception e) {
            throw new CommentNotFoundException(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), "Não foi possivel editar o comentario devido ao status da Task" + commentUpdateDto));
    }

}
