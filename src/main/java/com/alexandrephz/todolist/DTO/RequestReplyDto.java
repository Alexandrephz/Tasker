package com.alexandrephz.todolist.DTO;

import com.alexandrephz.todolist.model.CloseRequest;
import com.alexandrephz.todolist.model.RequestReplyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReplyDto {
    private String replyDescription;
    private CloseRequest closeRequest;
    private RequestReplyStatus requestReplyStatus;

}
