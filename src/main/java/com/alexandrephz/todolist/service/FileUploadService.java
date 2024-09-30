package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.CommentRegistrationDto;
import com.alexandrephz.todolist.DTO.FileUploadDto;
import com.alexandrephz.todolist.DTO.GetUploadedDto;
import com.alexandrephz.todolist.exceptions.FileServiceUploadException;
import com.alexandrephz.todolist.model.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FileUploadService {
    String uploadFile(FileUploadDto newUploadFile, Comment newCommentFileDetails) throws FileServiceUploadException;

    ResponseEntity<ApiResponseDto<?>> uploadFile(CommentRegistrationDto newCommentDetails, FileUploadDto newUploadFileDetails)
            throws FileServiceUploadException;

    ResponseEntity<ApiResponseDto<?>> getObject(GetUploadedDto getUploadedFile)
            throws FileServiceUploadException;
}
