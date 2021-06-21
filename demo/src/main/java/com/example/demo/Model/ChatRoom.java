package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class ChatRoom {
@Id
@SequenceGenerator(
        name = "chat_sequence",
        sequenceName = "chat_sequence",
        allocationSize = 1
)
@GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "chat_sequence"
)
        private long id;
        private String chatId;
        private long senderId;
        private long recipientId;

}
