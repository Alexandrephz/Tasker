package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findAllByOrderByGroupNameDesc();

    Group findByGroupName(String groupName);
}
