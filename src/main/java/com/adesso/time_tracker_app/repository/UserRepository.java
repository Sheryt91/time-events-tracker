package com.adesso.time_tracker_app.repository;

import com.adesso.time_tracker_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

   Optional<User> findByUsername(String username);
}
