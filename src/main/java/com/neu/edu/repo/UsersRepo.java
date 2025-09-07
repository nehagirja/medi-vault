package com.neu.edu.repo;

import com.neu.edu.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users, UUID> {
    Users findByEmail(String email);

    Users findByEmailAndPassword(String email, String password);
}
