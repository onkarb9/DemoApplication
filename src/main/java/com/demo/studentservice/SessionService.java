package com.demo.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SessionRepository;
import com.demo.entities.Session;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public String createSession(String username) {
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(token, username, now);
        sessionRepository.save(session);
        return token;
    }

    public Session findByToken(String token) {
        Session session = sessionRepository.findByToken(token);
        if (session != null) {
            // Update last accessed time
            session.setLastAccessedTime(LocalDateTime.now());
            sessionRepository.save(session);
            return session;
        }
        return null;
    }

    public void deleteByToken(String token) {
        Session session = sessionRepository.findByToken(token);
        if (session != null) {
            sessionRepository.delete(session);
        }
    }
}