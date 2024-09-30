package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.CommentRegistrationDto;
import com.alexandrephz.todolist.DTO.FileUploadDto;
import com.alexandrephz.todolist.DTO.GetUploadedDto;
import com.alexandrephz.todolist.exceptions.FileServiceUploadException;
import com.alexandrephz.todolist.model.Comment;
import com.alexandrephz.todolist.repository.FileRepository;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;

import com.alexandrephz.todolist.model.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    FileRepository fileRepository;

    @Override
    public String uploadFile(FileUploadDto newUploadFile, Comment newCommentFileDetails) throws FileServiceUploadException {
        if (newUploadFile != null && !newUploadFile.getFileComment().isEmpty()) {
            BasicAWSCredentials creds = new BasicAWSCredentials("", "");
            final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1)
                    .withCredentials(new AWSStaticCredentialsProvider(creds)).build();

            String fileRealName = newUploadFile.getFileComment().getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String fileUploadName = uuid.toString() + "-" + fileRealName;

            ObjectMetadata data = new ObjectMetadata();
            data.setContentLength(newUploadFile.getFileComment().getSize()); // Set the file size
            System.out.println(newCommentFileDetails.getId());
            System.out.println(fileRealName);
            System.out.println(fileUploadName);
            try {
                s3.putObject("", fileUploadName, newUploadFile.getFileComment().getInputStream(), data);

                // Save file metadata to the database
                File newFile = new File(newCommentFileDetails, fileRealName, fileUploadName);
                fileRepository.save(newFile);
                return "ok";
            } catch (AmazonServiceException | IOException e) {
                System.err.println(e.getMessage());
                return "err";


            }
        }
        return "ok";
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> uploadFile(CommentRegistrationDto newCommentDetails, FileUploadDto newUploadFileDetails) throws FileServiceUploadException {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getObject(GetUploadedDto getUploadedFile) throws FileServiceUploadException {
        return null;
    }
}
