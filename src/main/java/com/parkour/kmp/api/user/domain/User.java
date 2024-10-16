package com.parkour.kmp.api.user.domain;

import com.parkour.kmp.api.common.domain.TimestampEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "users", indexes = @Index(name = "idx_user_email", columnList = "email"))
public class User extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    protected User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }


}