package com.BRAINWAVE_JD_01.repository;

import com.BRAINWAVE_JD_01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}