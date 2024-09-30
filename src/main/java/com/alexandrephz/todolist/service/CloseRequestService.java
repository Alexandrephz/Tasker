package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.*;
import com.alexandrephz.todolist.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CloseRequestService {
    ResponseEntity<ApiResponseDto<?>> createRequest(CloseRequestDto closeRequestDto)
            throws CloseRequestException, CloseRequestNotFoundException, TaskNotFoundException;
    ResponseEntity<ApiResponseDto<?>> listRequest(UUID closeRequest)
            throws CloseRequestNotFoundException;
    ResponseEntity<ApiResponseDto<?>> decideRequest(RequestReplyDto requestReplyDto)
            throws CloseRequestNotFoundException, RequestReplyException;
}
