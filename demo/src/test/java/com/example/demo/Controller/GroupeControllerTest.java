package com.example.demo.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.GroupeRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Services.GroupeService;
import com.example.demo.Services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GroupeController.class})
@ExtendWith(SpringExtension.class)
public class GroupeControllerTest {
    @Autowired
    private GroupeController groupeController;

    @MockBean
    private GroupeRepository groupeRepository;

    @MockBean
    private GroupeService groupeService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private UsersService usersService;

    @Test
    public void testAjoutGroup() throws Exception {
        // Arrange
        doNothing().when(this.groupeService).ajouterGroupe(anyString());

        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(groupe);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groupe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetAllGroupes() throws Exception {
        // Arrange
        when(this.groupeService.getAllGroupes()).thenReturn(new ArrayList<Groupe>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groupes");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetAllGroupes2() throws Exception {
        // Arrange
        when(this.groupeService.getAllGroupes()).thenReturn(new ArrayList<Groupe>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/groupes");
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetGroupes() throws Exception {
        // Arrange
        when(this.groupeService.getGroupes(anyLong())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userGroupes/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetGroupes2() throws Exception {
        // Arrange
        when(this.groupeService.getGroupes(anyLong())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/userGroupes/{id}", 123L);
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testDeleteGroupe() throws Exception {
        // Arrange
        doNothing().when(this.groupeService).delete((Groupe) any());
        doNothing().when(this.groupeService).updateG(anyLong());

        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");
        Optional<Groupe> ofResult = Optional.<Groupe>of(groupe);
        when(this.groupeRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/groupe/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("groupe supprime")));
    }

    @Test
    public void testDeleteGroupe2() throws Exception {
        // Arrange
        doNothing().when(this.groupeService).delete((Groupe) any());
        doNothing().when(this.groupeService).updateG(anyLong());
        when(this.groupeRepository.findById((Long) any())).thenReturn(Optional.<Groupe>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/groupe/{id}", 123L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateNameGroupe() throws Exception {
        // Arrange
        doNothing().when(this.groupeService).modifierName(anyString(), anyLong());

        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(groupe);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/groupe/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.groupeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

