package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.MessageStatus;
import com.example.demo.Services.ChatMessageService;
import com.example.demo.Services.ChatRoomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ChatController.class})
@ExtendWith(SpringExtension.class)
public class ChatControllerTest {
    @Autowired
    private ChatController chatController;

    @MockBean
    private ChatMessageService chatMessageService;

    @MockBean
    private ChatRoomService chatRoomService;

    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;

    @Test
    public void testProcessMessage() throws MessagingException {
        // Arrange
        doNothing().when(this.simpMessagingTemplate).convertAndSendToUser(anyString(), anyString(), (Object) any());
        when(this.chatRoomService.getChatId(anyString(), anyString(), anyBoolean())).thenReturn(Optional.<String>of("42"));

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
        when(this.chatMessageService.save((ChatMessage) any())).thenReturn(chatMessage);
        ChatMessage chatMessage1 = new ChatMessage();

        // Act
        this.chatController.processMessage(chatMessage1);

        // Assert
        verify(this.chatMessageService).save((ChatMessage) any());
        verify(this.chatRoomService).getChatId(anyString(), anyString(), anyBoolean());
        verify(this.simpMessagingTemplate).convertAndSendToUser(anyString(), anyString(), (Object) any());
        assertEquals("42", chatMessage1.getChatId());
    }

    @Test
    public void testProcessMessage2() throws MessagingException {
        // Arrange
        doNothing().when(this.simpMessagingTemplate).convertAndSendToUser(anyString(), anyString(), (Object) any());
        when(this.chatRoomService.getChatId(anyString(), anyString(), anyBoolean())).thenReturn(Optional.<String>empty());

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
        when(this.chatMessageService.save((ChatMessage) any())).thenReturn(chatMessage);

        // Act
        this.chatController.processMessage(new ChatMessage());

        // Assert
        verify(this.chatRoomService).getChatId(anyString(), anyString(), anyBoolean());
    }

    @Test
    public void testCountNewMessages() throws Exception {
        // Arrange
        when(this.chatMessageService.countNewMessages(anyString(), anyString())).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/messages/{senderId}/{recipientId}/count", "42", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("3")));
    }

    @Test
    public void testCountNewMessages2() throws Exception {
        // Arrange
        when(this.chatMessageService.findChatMessages(anyString(), anyString())).thenReturn(new ArrayList<ChatMessage>());
        when(this.chatMessageService.countNewMessages(anyString(), anyString())).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/messages/{senderId}/{recipientId}/count", "", "Uri Vars", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testCountNewMessages3() throws Exception {
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
        when(this.chatMessageService.findById(anyString())).thenReturn(chatMessage);
        when(this.chatMessageService.findChatMessages(anyString(), anyString())).thenReturn(new ArrayList<ChatMessage>());
        when(this.chatMessageService.countNewMessages(anyString(), anyString())).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/messages/{senderId}/{recipientId}/count", "", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":\"42\",\"chatId\":\"42\",\"senderId\":\"42\",\"recipientId\":\"42\",\"senderName\":\"Sender Name\",\"recipientName"
                                        + "\":\"Recipient Name\",\"content\":\"Not all who wander are lost\",\"files\":[],\"timestamp\":-3600000,\"status\":"
                                        + "\"RECEIVED\"}")));
    }

    @Test
    public void testFindChatMessages() throws Exception {
        // Arrange
        when(this.chatMessageService.findChatMessages(anyString(), anyString())).thenReturn(new ArrayList<ChatMessage>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/{senderId}/{recipientId}",
                "42", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testFindChatMessages2() throws Exception {
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
        when(this.chatMessageService.findById(anyString())).thenReturn(chatMessage);
        when(this.chatMessageService.findChatMessages(anyString(), anyString())).thenReturn(new ArrayList<ChatMessage>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/{senderId}/{recipientId}", "",
                "Uri Vars", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":\"42\",\"chatId\":\"42\",\"senderId\":\"42\",\"recipientId\":\"42\",\"senderName\":\"Sender Name\",\"recipientName"
                                        + "\":\"Recipient Name\",\"content\":\"Not all who wander are lost\",\"files\":[],\"timestamp\":-3600000,\"status\":"
                                        + "\"RECEIVED\"}")));
    }

    @Test
    public void testFindMessage() throws Exception {
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
        when(this.chatMessageService.findById(anyString())).thenReturn(chatMessage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/messages/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":\"42\",\"chatId\":\"42\",\"senderId\":\"42\",\"recipientId\":\"42\",\"senderName\":\"Sender Name\",\"recipientName"
                                        + "\":\"Recipient Name\",\"content\":\"Not all who wander are lost\",\"files\":[],\"timestamp\":-3600000,\"status\":"
                                        + "\"RECEIVED\"}")));
    }
}

