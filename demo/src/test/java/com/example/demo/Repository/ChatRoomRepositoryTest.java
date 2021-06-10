package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.demo.Model.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ChatRoomRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class ChatRoomRepositoryTest {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Test
    public void testFindBySenderIdAndRecipientId() {
        // Arrange
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setSenderId("42");
        chatRoom.setRecipientId("42");
        chatRoom.setChatId("42");
        chatRoom.setId("42");
        this.chatRoomRepository.<ChatRoom>save(chatRoom);
        // Act and Assert
        assertFalse(this.chatRoomRepository.findBySenderIdAndRecipientId("foo", "foo").isPresent());
    }
}

