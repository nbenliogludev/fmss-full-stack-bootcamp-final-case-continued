package com.nbenliogludev.userauthentication.repository;

import com.nbenliogludev.userauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author nbenliogludev
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

