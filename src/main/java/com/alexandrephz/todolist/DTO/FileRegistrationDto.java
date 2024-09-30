package com.alexandrephz.todolist.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRegistrationDto {
    private String fileRealName;
    private String fileStoredName;
    private UUID commentId;
}
