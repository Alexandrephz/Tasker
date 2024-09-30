package com.alexandrephz.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name="taskTitle", nullable = false, length = 80)
    private String taskTitle;

    @Column(name="taskDescription", nullable = false, length = 140)
    private String taskDescription;

    @CreationTimestamp
    private Date taskStarted;

    @UpdateTimestamp
    private Date taskUpdated;

    @Column(name = "taskEnd", nullable = false)
    private Date taskEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "taskStatus", nullable = false)
    private TaskStatus status;

    @OneToMany(mappedBy = "task")
    private Set<Comment> comments;

    @OneToOne
    private Group group;


    public Task(String taskTitle, String taskDescription, Date taskEnd, TaskStatus status, Group group) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskEnd = taskEnd;
        this.status = status;
        this.group = group;
    }
}
