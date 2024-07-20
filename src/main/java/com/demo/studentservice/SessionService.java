package com.demo.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SessionRepository;
import com.demo.entities.Session;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;
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
    
    public boolean isSessionValid(String token)
    {
    		Session dbSession = sessionRepository.findByToken(token);
    		if (dbSession != null && token.equals(sessionRepository.findByUsername(dbSession.getUsername()).getToken())) {
    			dbSession.setLastAccessedTime(LocalDateTime.now());
				sessionRepository.save(dbSession);
                return true;
    		}
		return false;
    }

    public void deleteByToken(String token) {
        Session session = sessionRepository.findByToken(token);
        if (session != null) {
            sessionRepository.delete(session);
        }
    }
    

	public List<Session> findInactiveSessions(LocalDateTime thresholdTime) {
		
		return sessionRepository.findInactiveSessions(thresholdTime);
	}

	public void deleteSession(Long id) {
		
		sessionRepository.deleteById(id);
	}

	public Session findByToken(String token) {
        Session session = sessionRepository.findByToken(token);
        if (session != null) {
            return session;
        }
        return null;
    }
	
	public boolean findByUsername(String username) {
        Session session=sessionRepository.findByUsername(username);
       if(session!=null)
    	   return true;
       else return false;
    }
}