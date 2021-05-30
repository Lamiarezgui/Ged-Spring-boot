package com.example.demo.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;
}

