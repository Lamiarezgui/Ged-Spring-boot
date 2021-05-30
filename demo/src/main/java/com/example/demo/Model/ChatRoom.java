package com.example.demo.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class ChatRoom {
@Id
        private String id;
        private String chatId;
        private String senderId;
        private String recipientId;

}
