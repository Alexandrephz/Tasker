package com.alexandrephz.todolist.exceptions;

public class CloseRequestNotFoundException extends RuntimeException {
    public CloseRequestNotFoundException(String message) {
        super(message);
    }
}
