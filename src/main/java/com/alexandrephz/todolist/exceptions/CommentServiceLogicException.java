package com.alexandrephz.todolist.exceptions;

public class CommentServiceLogicException extends Exception {
    public CommentServiceLogicException() {
        super("Something wen wrong while saving the comment. Please try again Later!");
    }
}
