package ru.job4j.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auto_user")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
