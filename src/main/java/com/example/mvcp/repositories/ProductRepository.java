package com.example.mvcp.repositories;

import com.example.mvcp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.cid = ?1")
    List<Product> findByCid(int cid);

    @Query("select p from Product p where p.pid = ?1")
    Product findByPid(int pid);

    @Query("select p from Product p where p.title like ?1 or p.detail like ?1")
    List<Product> findProductByTitle(String search);
}
