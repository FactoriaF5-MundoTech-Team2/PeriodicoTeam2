package com.periodico.mundotech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.periodico.mundotech.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
