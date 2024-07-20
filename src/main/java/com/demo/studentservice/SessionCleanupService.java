package com.demo.studentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.demo.entities.Session;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionCleanupService {
	
	@Autowired
    SessionService sessionService;

    @Scheduled(fixedRate = 60000)
    public void cleanUpInactiveSessions() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime thresholdTime = currentTime.minusMinutes(1);

        List<Session> inactiveSessions=sessionService.findInactiveSessions(thresholdTime);
        
        for(Session session : inactiveSessions) {
        	sessionService.deleteSession(session.getId());
        }
    }
}