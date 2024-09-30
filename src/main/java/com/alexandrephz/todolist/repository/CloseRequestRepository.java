package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.CloseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CloseRequestRepository extends JpaRepository<CloseRequest, UUID> {

}
