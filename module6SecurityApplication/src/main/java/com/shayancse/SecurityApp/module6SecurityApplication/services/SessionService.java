package com.shayancse.SecurityApp.module6SecurityApplication.services;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.Session;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2; //hardcoding session limit as 2

    public void generateNewSession(User user, String refreshToken) {

        //first get all the sessions of that particular user
        List<Session> userSessions = sessionRepository.findByUser(user);

        //If the session limit is exceeding then delete the least recently used session
        if (userSessions.size() == SESSION_LIMIT) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));
            //Session::getLastUsedAt is a Java Method Reference.
            //It is a shorthand way of referring to the getLastUsedAt() method of the Session class.

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        //Now create a new session and save it
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    //This validSession method will be called from our /refresh API only
    public void validateSession(String refreshToken) {
        //just check whether there is a session according to this refreshToken is present inside my database or not
        Session session = sessionRepository.findByRefreshToken(refreshToken).orElseThrow(
                ()-> new SessionAuthenticationException("Session not found for the refreshToken: " + refreshToken)
                );

        //Update the session's last used date time
        session.setLastUsedAt(LocalDateTime.now());

        //Now save the session
        sessionRepository.save(session);
    }

}
