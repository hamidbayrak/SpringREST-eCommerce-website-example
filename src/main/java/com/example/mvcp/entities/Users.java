package com.example.mvcp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int uid;
    private String name;
    private String mail;
    private String password;
    private String address;
    private String remember;
    private boolean isAdmin;
}
