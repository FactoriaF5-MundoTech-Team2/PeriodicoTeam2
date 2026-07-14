package com.periodico.mundotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.periodico.mundotech.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}



