package com.demo.dao;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);
    void deleteByLastAccessedTimeBefore(LocalDateTime now);
}