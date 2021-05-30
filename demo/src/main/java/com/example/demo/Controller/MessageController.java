package com.example.demo.Controller;

import com.example.demo.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController{
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
private UsersService usersService;
    @MessageMapping("/news")
    public void broadcastNews(@Payload String message) {
        this.simpMessagingTemplate.convertAndSend("/topic/news", message);
    }


}
