package com.alexandrephz.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "close_requests")
public class CloseRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String closeRequestDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "closeRequestStatus", nullable = false)
    private CloseRequestStatus closeRequestStatus;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name="task_id", nullable = false)
    private Task task;


    public CloseRequest(String closeDescription, CloseRequestStatus closeRequestStatus, Task task) {
        this.closeRequestDescription = closeDescription;
        this.closeRequestStatus = closeRequestStatus;
        this.task = task;
    }
}
