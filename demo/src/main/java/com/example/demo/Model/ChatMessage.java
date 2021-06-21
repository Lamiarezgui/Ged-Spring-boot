package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ChatMessage {
    @Id
    @SequenceGenerator(
            name = "chatM_sequence",
            sequenceName = "chatM_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "chatM_sequence"
    )
    private long id;
    private String chatId;
    private long senderId;
    private long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    @OneToMany
    private List<FileEntity> files;
    private Date timestamp;
    private MessageStatus status;
}

