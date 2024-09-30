package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {
    private MultipartFile fileComment;
}

