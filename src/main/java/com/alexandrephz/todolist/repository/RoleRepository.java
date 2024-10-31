package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.Role;
import com.alexandrephz.todolist.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}