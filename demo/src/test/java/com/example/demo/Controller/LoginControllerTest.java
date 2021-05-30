package com.example.demo.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.example.demo.Model.AuthenticationRequest;
import com.example.demo.Model.Users;
import com.example.demo.Services.RegisterUserService;
import com.example.demo.Services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
public class LoginControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;


    @Autowired
    private LoginController loginController;

    @MockBean
    private RegisterUserService registerUserService;

    @MockBean
    private UsersService usersService;

    @Test
    public void testCreateAuthenticationToken() throws Exception {
        // Arrange
        when(this.usersService.loadUserByUsername(anyString())).thenReturn(new Users());
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new BearerTokenAuthenticationToken("ABC123"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AuthenticationRequest()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{\"jwt\":null}")));
    }

    @Test
    public void testCreateAuthenticationToken2() throws Exception {
        // Arrange
        when(this.usersService.loadUserByUsername(anyString())).thenReturn(new Users());
        when(this.authenticationManager.authenticate((org.springframework.security.core.Authentication) any()))
                .thenReturn(new BearerTokenAuthenticationToken("ABC123"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AuthenticationRequest()));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{\"jwt\":null}")));
    }


}

