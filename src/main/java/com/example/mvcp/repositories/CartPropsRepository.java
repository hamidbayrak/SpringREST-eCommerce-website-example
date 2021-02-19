package com.example.mvcp.repositories;

import com.example.mvcp.entities.CartProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartPropsRepository extends JpaRepository<CartProps,Integer> {
    @Query(value = "CALL cart_proc(:userid)",nativeQuery = true)
    List<CartProps> fillCart(@Param("userid") int uid);
}
