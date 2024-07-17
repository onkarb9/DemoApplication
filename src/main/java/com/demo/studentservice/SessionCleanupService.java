package com.demo.studentservice;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.dao.SessionRepository;

import java.time.LocalDateTime;

@Service
public class SessionCleanupService {

    private final SessionRepository sessionRepository;

    public SessionCleanupService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void cleanUpExpiredTokens() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(5);
        sessionRepository.deleteByLastAccessedTimeBefore(expirationTime);
    }
}