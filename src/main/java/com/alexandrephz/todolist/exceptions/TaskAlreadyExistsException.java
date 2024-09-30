package com.alexandrephz.todolist.exceptions;

public class TaskAlreadyExistsException extends Exception {
    public TaskAlreadyExistsException(String message){
        super(message);
    }
}
