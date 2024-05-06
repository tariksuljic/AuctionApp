package com.example.auctionapp.websockets;

import com.example.auctionapp.exceptions.GeneralException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class MainSocketHandler implements WebSocketHandler {
    public Map<String, WebSocketSession> sessions = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(MainSocketHandler.class);

    public MainSocketHandler() { }

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        final String userId = extractUserIdFromUrl(session);

        if (userId != null) {
            if (sessions.containsKey(userId)) {
                logger.warn("A WebSocket session for userId: " + userId + " already exists.");

                session.close();
            } else {
                sessions.put(userId, session);

                logger.info("WebSocket session established for userId: " + userId);
            }
        }
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus closeStatus) throws Exception {
        final String userId = extractUserIdFromUrl(session);

        if (userId != null) {
            sessions.remove(userId);

            logger.info("WebSocket session closed for userId: " + userId);
        }
    }

    @Override
    public void handleTransportError(final WebSocketSession session, Throwable exception) throws Exception {
        logger.error("Error happened" + session.getId() + "with reason###" + exception.getMessage());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleMessage(final WebSocketSession session, final WebSocketMessage<?> message)
            throws Exception {
        final String messageReceived = (String) message.getPayload();
    }

    public void sendMessage(final String userId, String message) {
        final WebSocketSession session = sessions.get(userId);

        if (session == null) {
            return;
        }

        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            } else {
                logger.warn("Cannot send message. Session closed for user ID: " + userId);
            }
        } catch (IOException e) {
            throw new GeneralException(e);
        }
    }

    private String extractUserIdFromUrl(final WebSocketSession session) throws IOException {
        final URI uri = session.getUri();

        if (uri == null) {
            logger.error("URI is null in session ID: " + session.getId());

            session.close();
            return null;
        }

        final String query = uri.getQuery();
        String userId = null;

        if (query != null && query.startsWith("userId=")) {
            userId = query.substring("userId=".length());
        }

        logger.info("User ID is:" + userId);
        
        return userId;
    }
}
