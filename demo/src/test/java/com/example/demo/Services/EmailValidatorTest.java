package com.example.demo.Services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailValidator.class})
@ExtendWith(SpringExtension.class)
public class EmailValidatorTest {
    @Autowired
    private EmailValidator emailValidator;

    @Test
    public void testTest() {
        // Arrange, Act and Assert
        assertTrue(this.emailValidator.test("foo"));
    }
}

