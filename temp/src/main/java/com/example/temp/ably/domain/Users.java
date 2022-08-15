package com.example.temp.ably.domain;

import lombok.Getter;
import org.apache.catalina.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "users_id")
    private Long id;
    private String email;
    private String name;
    private String password;

    public Users(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this. password = password;
    }
}
