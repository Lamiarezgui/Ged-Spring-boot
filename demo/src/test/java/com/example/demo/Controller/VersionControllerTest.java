package com.example.demo.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.VersionRepository;
import com.example.demo.Services.VersionsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VersionController.class})
@ExtendWith(SpringExtension.class)
public class VersionControllerTest {
    @Autowired
    private VersionController versionController;

    @MockBean
    private VersionRepository versionRepository;

    @MockBean
    private VersionsService versionsService;

    @Test
    public void testDeleteFile() throws Exception {
        // Arrange
        FileEntity fileEntity = new FileEntity();
        fileEntity.setContentType("text/plain");
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes());
        fileEntity.setPrivilege("Privilege");
        fileEntity.setSize(3L);

        Versions versions = new Versions();
        versions.setContentType("text/plain");
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("Name");
        versions.setData("AAAAAAAA".getBytes());
        versions.setFileEntity(fileEntity);
        versions.setPrivilege("Privilege");
        versions.setSize(3L);
        Optional<Versions> ofResult = Optional.<Versions>of(versions);
        doNothing().when(this.versionRepository).delete((Versions) any());
        when(this.versionRepository.findById(anyString())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/files/versions/{id}", "value");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("file deleted")));
    }

    @Test
    public void testDeleteFile2() throws Exception {
        // Arrange
        doNothing().when(this.versionRepository).delete((Versions) any());
        when(this.versionRepository.findById(anyString())).thenReturn(Optional.<Versions>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/files/versions/{id}", "value");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetFile() throws Exception {
        // Arrange
        FileEntity fileEntity = new FileEntity();
        fileEntity.setContentType("text/plain");
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes());
        fileEntity.setPrivilege("Privilege");
        fileEntity.setSize(3L);

        Versions versions = new Versions();
        versions.setContentType("text/plain");
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("Name");
        versions.setData("AAAAAAAA".getBytes());
        versions.setFileEntity(fileEntity);
        versions.setPrivilege("Privilege");
        versions.setSize(3L);
        Optional<Versions> ofResult = Optional.<Versions>of(versions);
        when(this.versionsService.getVersions(anyString())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/versions/{id}", "value");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("AAAAAAAA")));
    }

    @Test
    public void testGetFile2() throws Exception {
        // Arrange
        when(this.versionsService.getVersions(anyString())).thenReturn(Optional.<Versions>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/versions/{id}", "value");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testList() throws Exception {
        // Arrange
        when(this.versionsService.getAllVersions()).thenReturn(new ArrayList<Versions>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/versions");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testList2() throws Exception {
        // Arrange
        FileEntity fileEntity = new FileEntity();
        fileEntity.setContentType("text/plain");
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setName("?");
        fileEntity.setData("AAAAAAAA".getBytes());
        fileEntity.setPrivilege("?");
        fileEntity.setSize(3L);

        Versions versions = new Versions();
        versions.setContentType("text/plain");
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("?");
        versions.setData("AAAAAAAA".getBytes());
        versions.setFileEntity(fileEntity);
        versions.setPrivilege("?");
        versions.setSize(3L);

        ArrayList<Versions> versionsList = new ArrayList<Versions>();
        versionsList.add(versions);
        when(this.versionsService.getAllVersions()).thenReturn(versionsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/versions");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "[{\"id\":\"42\",\"name\":\"?\",\"contentType\":\"text/plain\",\"size\":3,\"data\":null,\"date\":[1,1,1,1,1],\"privilege"
                                        + "\":null,\"fileEntity\":null}]")));
    }

    @Test
    public void testList3() throws Exception {
        // Arrange
        FileEntity fileEntity = new FileEntity();
        fileEntity.setContentType("text/plain");
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setName("?");
        fileEntity.setData("AAAAAAAA".getBytes());
        fileEntity.setPrivilege("?");
        fileEntity.setSize(3L);

        Versions versions = new Versions();
        versions.setContentType("text/plain");
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("?");
        versions.setData("AAAAAAAA".getBytes());
        versions.setFileEntity(fileEntity);
        versions.setPrivilege("?");
        versions.setSize(3L);

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setContentType("text/plain");
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setName("?");
        fileEntity1.setData("AAAAAAAA".getBytes());
        fileEntity1.setPrivilege("?");
        fileEntity1.setSize(3L);

        Versions versions1 = new Versions();
        versions1.setContentType("text/plain");
        versions1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions1.setId("42");
        versions1.setName("?");
        versions1.setData("AAAAAAAA".getBytes());
        versions1.setFileEntity(fileEntity1);
        versions1.setPrivilege("?");
        versions1.setSize(3L);

        ArrayList<Versions> versionsList = new ArrayList<Versions>();
        versionsList.add(versions1);
        versionsList.add(versions);
        when(this.versionsService.getAllVersions()).thenReturn(versionsList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/versions");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.versionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "[{\"id\":\"42\",\"name\":\"?\",\"contentType\":\"text/plain\",\"size\":3,\"data\":null,\"date\":[1,1,1,1,1],\"privilege"
                                        + "\":null,\"fileEntity\":null},{\"id\":\"42\",\"name\":\"?\",\"contentType\":\"text/plain\",\"size\":3,\"data\":null,\"date"
                                        + "\":[1,1,1,1,1],\"privilege\":null,\"fileEntity\":null}]")));
    }
}

