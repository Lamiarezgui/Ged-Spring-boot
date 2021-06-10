package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.MessageStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ChatMessageRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class ChatMessageRepositoryTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    public void testCountBySenderIdAndRecipientIdAndStatus() {
        // Arrange
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId("42");
        chatMessage.setFiles(new ArrayList<FileEntity>());
        chatMessage.setRecipientId("42");
        chatMessage.setChatId("42");
        chatMessage.setId("42");
        chatMessage.setSenderName("Sender Name");
        chatMessage.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage.setTimestamp(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessage.setContent("Not all who wander are lost");

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");
        this.chatMessageRepository.<ChatMessage>save(chatMessage);
        this.chatMessageRepository.<ChatMessage>save(chatMessage1);

        // Act and Assert
        assertEquals(0L,
                this.chatMessageRepository.countBySenderIdAndRecipientIdAndStatus("foo", "foo", MessageStatus.RECEIVED));
    }

    @Test
    public void testFindByChatId() {
        // Arrange
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId("42");
        chatMessage.setFiles(new ArrayList<FileEntity>());
        chatMessage.setRecipientId("42");
        chatMessage.setChatId("42");
        chatMessage.setId("42");
        chatMessage.setSenderName("Sender Name");
        chatMessage.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage.setTimestamp(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessage.setContent("Not all who wander are lost");

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");
        this.chatMessageRepository.<ChatMessage>save(chatMessage);
        this.chatMessageRepository.<ChatMessage>save(chatMessage1);

        // Act and Assert
        assertTrue(this.chatMessageRepository.findByChatId("foo").isEmpty());
    }

    @Test
    public void testUpdate() {
        // Arrange
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId("42");
        chatMessage.setFiles(new ArrayList<FileEntity>());
        chatMessage.setRecipientId("42");
        chatMessage.setChatId("42");
        chatMessage.setId("42");
        chatMessage.setSenderName("Sender Name");
        chatMessage.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage.setTimestamp(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessage.setContent("Not all who wander are lost");

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");
        this.chatMessageRepository.<ChatMessage>save(chatMessage);
        this.chatMessageRepository.<ChatMessage>save(chatMessage1);

        // Act
        this.chatMessageRepository.update("foo", "foo", MessageStatus.RECEIVED);

        // Assert
        List<ChatMessage> findAllResult = this.chatMessageRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }
}

