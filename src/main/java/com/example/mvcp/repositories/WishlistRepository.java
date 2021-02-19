package com.example.mvcp.repositories;

import com.example.mvcp.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {

}
