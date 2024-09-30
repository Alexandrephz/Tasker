package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.RequestReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequestReplyRepository extends JpaRepository<RequestReply, UUID> {
}
