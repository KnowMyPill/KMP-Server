package com.parkour.kmp.api.user.domain;

import com.parkour.kmp.api.common.domain.TimestampEntity;
import com.parkour.kmp.api.history.domain.History;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users", indexes = @Index(name = "idx_user_token", columnList = "token"))
public class User extends TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();

    protected User() {}

    public User(String token) {
        this.token = token;
    }

}