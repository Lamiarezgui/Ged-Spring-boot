package com.example.demo.mail;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    @Test
    public void testSend() throws MailException {
        // Arrange
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        this.emailService.send("alice.liddell@example.org", "jane.doe@example.org","vos coordonnees");

        // Assert
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    @Test
    public void testSend2() throws MailException {
        // Arrange
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        this.emailService.send("utf-8", "jane.doe@example.org","vos coordonnes");

        // Assert
        verify(this.javaMailSender).createMimeMessage();
        verify(this.javaMailSender).send((MimeMessage) any());
    }

    @Test
    public void testSend3() throws MailException {
        // Arrange
        doNothing().when(this.javaMailSender).send((MimeMessage) any());
        when(this.javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> this.emailService.send("Vos Coordonees", "jane.doe@example.org","vos coordonnees"));
        verify(this.javaMailSender).createMimeMessage();
    }
}

