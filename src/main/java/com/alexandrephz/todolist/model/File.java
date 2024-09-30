package com.alexandrephz.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String fileRealName;

    @Column(nullable = false, updatable = false)
    private String fileStoredName;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;


    @ManyToOne
    @JoinColumn(name="comment_id", nullable = false)
    private Comment comment;

    public File(Comment comment, String fileRealName, String fileUploadedName) {
        this.comment = comment;
        this.fileRealName = fileRealName;
        this.fileStoredName = fileUploadedName;

    }

}
