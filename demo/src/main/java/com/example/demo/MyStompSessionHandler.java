package com.example.demo;

import com.example.demo.Model.ChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(MyStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("user/1/queue/messages", this);
        logger.info("Subscribed to user/1/queue/messages");
        session.send("/app/chat", getSampleMessage());
        logger.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ChatMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ChatMessage msg = (ChatMessage) payload;
        logger.info("Received : " + msg.getContent() + " from : " + msg.getSenderId(),msg.getRecipientId());
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    private ChatMessage getSampleMessage() {
        ChatMessage msg = new ChatMessage();
        msg.setRecipientId("1");
        msg.setSenderId("2");
        msg.setContent("hellllo");
        return msg;
    }
}