package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.CloseRequestDto;
import com.alexandrephz.todolist.DTO.RequestReplyDto;
import com.alexandrephz.todolist.exceptions.CloseRequestException;
import com.alexandrephz.todolist.exceptions.CloseRequestNotFoundException;
import com.alexandrephz.todolist.exceptions.RequestReplyException;
import com.alexandrephz.todolist.exceptions.TaskNotFoundException;
import com.alexandrephz.todolist.model.*;
import com.alexandrephz.todolist.repository.CloseRequestRepository;
import com.alexandrephz.todolist.repository.RequestReplyRepository;
import com.alexandrephz.todolist.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CloseRequestServiceImpl implements CloseRequestService{
    @Autowired
    CloseRequestRepository closeRequestRepository;

    @Autowired
    RequestReplyRepository requestReplyRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> createRequest(CloseRequestDto closeRequestDto)
            throws CloseRequestException, CloseRequestNotFoundException, TaskNotFoundException {
        try {
            Task task = taskRepository.getReferenceById(closeRequestDto.getTask().getId());
            if (task.getStatus() != TaskStatus.CANCELLED && task.getStatus() != TaskStatus.FINISHED && task.getStatus() != TaskStatus.PAUSED){
                CloseRequest closeRequest = new CloseRequest(closeRequestDto.getCloseDescription(), CloseRequestStatus.PENDING, closeRequestDto.getTask());
                closeRequestRepository.save(closeRequest);
                task.setStatus(TaskStatus.PAUSED);
                taskRepository.save(task);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Request criada com sucesso" + closeRequest));
            }else {
                throw new CloseRequestException("Tarefa tem um status que não permite o cancelamento" + task);
            }
        } catch (Exception e) {
            throw new TaskNotFoundException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> listRequest(UUID closeRequest)
            throws CloseRequestNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> decideRequest(RequestReplyDto requestReplyDto)
            throws CloseRequestNotFoundException, RequestReplyException {
        try {
            CloseRequest closeRequest = closeRequestRepository.getReferenceById(requestReplyDto.getCloseRequest().getId());
            Task task = closeRequest.getTask();
            if (closeRequest.getCloseRequestStatus() == CloseRequestStatus.PENDING){
                RequestReply requestReply = new RequestReply(requestReplyDto.getReplyDescription(), requestReplyDto.getCloseRequest());
                switch (requestReplyDto.getRequestReplyStatus()){
                    case APPROVED -> {
                        requestReply.setRequestReplyStatus(RequestReplyStatus.APPROVED);
                        closeRequest.setCloseRequestStatus(CloseRequestStatus.APROVED);
                        task.setStatus(TaskStatus.FINISHED);
                    }
                    case DENY -> {
                        requestReply.setRequestReplyStatus(RequestReplyStatus.DENY);
                        closeRequest.setCloseRequestStatus(CloseRequestStatus.DENIED);
                        task.setStatus(TaskStatus.IN_PROGRESS);
                    }
                    case null, default -> throw new RequestReplyException("Voce deve aprovar ou negar a autorização");
                }
                requestReplyRepository.save(requestReply);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),"Request foi definida como " + requestReplyDto.getRequestReplyStatus() + " com sucesso" + closeRequest.getCloseRequestDescription()));
            }else {
                throw new CloseRequestException("Request tem um status que não permite a aprovação" + requestReplyDto);
            }
        } catch (Exception e) {
            throw new CloseRequestNotFoundException(e.getMessage());
        }
    }
}
