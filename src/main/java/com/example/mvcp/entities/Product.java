package com.example.mvcp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int pid;
    private String title;
    private String detail;
    private String price;
    private String price_old;
    private String img1;
    private String img2;
    private String img3;
    private String date;
    private int cid;

}
