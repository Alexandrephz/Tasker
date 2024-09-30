package com.alexandrephz.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(nullable = false, length = 240, updatable = true)
    private String comment;

    @Column(nullable = true, updatable = true)
    private Boolean haveImage;

    @OneToMany(mappedBy = "comment")
    private Set<File> files;

    @ManyToOne
    @JoinColumn(name="task_id", nullable = false)
    private Task task;

    public Comment(String comment, Boolean haveImage, Task task) {
        this.comment = comment;
        this.haveImage = haveImage;
        this.task = task;
    }

    public UUID getCommentId() {
        return getId();
    }
}
