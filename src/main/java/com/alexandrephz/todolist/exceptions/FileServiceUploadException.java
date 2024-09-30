package com.alexandrephz.todolist.exceptions;

public class FileServiceUploadException extends RuntimeException {
    public FileServiceUploadException(String message) {
        super("There is a error uploading the file");
    }
}
