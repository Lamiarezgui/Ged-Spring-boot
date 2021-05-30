package com.example.demo.Repository;

import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    @Modifying
    @Query("update ChatMessage c set c.status=:status where c.senderId=:senderId and c.recipientId=:recipientId ")
    void update(String senderId, String recipientId, MessageStatus status);
}
