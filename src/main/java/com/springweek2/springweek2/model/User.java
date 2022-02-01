package com.springweek2.springweek2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    public User (String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.kakaoId = null;
    }

    public User (String username, String password, UserRoleEnum role, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.kakaoId = kakaoId;
    }
}
