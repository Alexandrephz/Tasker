package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Task findByTaskTitle(String taskTitle);
    List<Task> findAllByOrderByTaskStartedDesc();

}

