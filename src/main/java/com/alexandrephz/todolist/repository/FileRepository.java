package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    File findFileByCommentId(UUID commentId);
}
