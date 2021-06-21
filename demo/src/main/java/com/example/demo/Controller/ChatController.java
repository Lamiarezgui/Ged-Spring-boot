package com.example.demo.Controller;

import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.ChatNotification;
import com.example.demo.Model.ChatRoom;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.Services.ChatMessageService;
import com.example.demo.Services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;

   /* @MessageMapping("/chat")
    //@PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/chat",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }*/
    @PostMapping("/messagerie")
    public void sendmessage(@RequestBody ChatMessage chatMessage){
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());
        chatMessageService.save(chatMessage);

    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
   // @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable long senderId,
            @PathVariable long recipientId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    //@PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    public ResponseEntity<?> findChatMessages ( @PathVariable long senderId,
                                                @PathVariable long recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
    @GetMapping("/chatRoom")
    public List<ChatRoom> getRoom(){
        return chatRoomService.getRoom();
    }

    @GetMapping("/messages/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    public ResponseEntity<?> findMessage (@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity
                .ok(chatMessageService.findById(id));
    }
}
