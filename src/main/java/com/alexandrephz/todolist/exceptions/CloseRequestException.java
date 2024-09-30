package com.alexandrephz.todolist.exceptions;

public class CloseRequestException extends RuntimeException {
    public CloseRequestException(String message) {
        super(message);
    }
}
