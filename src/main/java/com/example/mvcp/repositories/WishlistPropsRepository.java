package com.example.mvcp.repositories;

import com.example.mvcp.entities.WishlistProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistPropsRepository extends JpaRepository<WishlistProps,Integer>  {

    @Query(value = "CALL wishlist_proc(:userid)", nativeQuery = true)
    List<WishlistProps> fillwishlist(@Param("userid") int uid );

}
