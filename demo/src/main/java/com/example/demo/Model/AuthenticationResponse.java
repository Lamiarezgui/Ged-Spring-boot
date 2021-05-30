package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor

public class AuthenticationResponse implements Serializable {
    private Long id;
    private String username;
    private final String jwt;





}