package com.Vy.telegram_bot.repository;

import com.Vy.telegram_bot.model.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);

    List<User> existsByEmail(String email);
    List<User> existsByPassword(String password);
}
