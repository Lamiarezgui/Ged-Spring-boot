package com.example.demo.Repository;

import com.example.demo.Model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findBySenderIdAndRecipientId(long senderId, long recipientId);

    @Query("select c from ChatRoom c")
    List<ChatRoom> getRoom();
}