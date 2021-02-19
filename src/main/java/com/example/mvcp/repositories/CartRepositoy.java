package com.example.mvcp.repositories;

import com.example.mvcp.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepositoy extends JpaRepository<Cart,Integer> {
}
