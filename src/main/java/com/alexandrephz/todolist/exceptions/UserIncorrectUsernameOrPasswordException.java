package com.alexandrephz.todolist.exceptions;

public class UserIncorrectUsernameOrPasswordException extends RuntimeException {
    public UserIncorrectUsernameOrPasswordException(String message) {
        super(message);
    }
}
