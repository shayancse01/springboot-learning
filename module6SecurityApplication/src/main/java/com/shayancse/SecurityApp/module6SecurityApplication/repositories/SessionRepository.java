package com.shayancse.SecurityApp.module6SecurityApplication.repositories;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.Session;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
