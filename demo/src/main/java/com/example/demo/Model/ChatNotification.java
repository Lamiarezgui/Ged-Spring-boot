package com.example.demo.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatNotification {
    private long id;
    private long senderId;
    private String senderName;


}

