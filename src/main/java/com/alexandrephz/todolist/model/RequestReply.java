package com.alexandrephz.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "requests_replies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String replyDescription;

    @OneToOne
    private CloseRequest closeRequest;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    @Column(name = "requestReplyStatus", nullable = false)
    private RequestReplyStatus requestReplyStatus;

    public RequestReply(String replyDescription, CloseRequest closeRequest) {
        this.replyDescription = replyDescription;
        this.closeRequest = closeRequest;
    }
}
