package com.alexandrephz.todolist.repository;

import com.alexandrephz.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByOrderByUsernameDesc();
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String userName);

}
