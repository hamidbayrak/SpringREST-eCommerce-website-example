package com.example.mvcp.repositories;

import com.example.mvcp.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByMailAndPassword(String mail, String password);
    Users findById (int id);
}
