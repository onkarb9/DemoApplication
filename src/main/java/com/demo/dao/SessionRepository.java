package com.demo.dao;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);
    void deleteByLastAccessedTimeBefore(LocalDateTime now);
	Session findByUsername(String username);
	
	@Query("SELECT s FROM Session s WHERE s.lastAccessedTime < :thresholdTime")
	List<Session> findInactiveSessions(@Param("thresholdTime") LocalDateTime thresholdTime);
}