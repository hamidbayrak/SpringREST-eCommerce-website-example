package com.example.mvcp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class WishlistProps {
    @Id
    private int wid;
    private int pid;
    private String img1;
    private String title;
    private String price;
    private int uid;

}
