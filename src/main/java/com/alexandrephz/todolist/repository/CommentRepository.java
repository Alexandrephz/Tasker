package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.Comment;
import com.alexandrephz.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findByTask(Task id);
}
