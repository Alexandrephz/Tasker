package com.alexandrephz.todolist.model;

import com.alexandrephz.todolist.DTO.UserRegistrationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public User(String username, String fullName, String password, String email, UserRole userRole) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.userRole = userRole; // Corrected here
    }
}
