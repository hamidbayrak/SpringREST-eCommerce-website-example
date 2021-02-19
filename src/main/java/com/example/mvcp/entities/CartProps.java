package com.example.mvcp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CartProps {
    @Id
    private int cartid;
    private int pid;
    private String img1;
    private String title;
    private String price;
    private int quantity;
    private int uid;
}
