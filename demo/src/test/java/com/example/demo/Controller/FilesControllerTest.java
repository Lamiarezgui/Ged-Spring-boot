package com.example.demo.Controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.example.demo.Model.AppUserRole;
import com.example.demo.Model.Archive;
import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.MessageStatus;
import com.example.demo.Model.Users;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Services.FileService;
import com.example.demo.Services.VersionsService;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FilesController.class})
@ExtendWith(SpringExtension.class)
public class FilesControllerTest {
    @MockBean
    private FichierRepository fichierRepository;

    @MockBean
    private FileService fileService;

    @Autowired
    private FilesController filesController;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private VersionsService versionsService;

    @Test
    public void testGetVersionsFile() throws Exception {
        // Arrange
        when(this.fileService.getVersionsFile(anyString())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/ListVersions/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetVersionsFile2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setLastName("Doe");
        users.setEmail("jane.doe@example.org");
        users.setPassword("iloveyou");
        users.setFileEntity(new ArrayList<FileEntity>());
        users.setAppUserRole(AppUserRole.CONTROLEUR);
        users.setResetPasswordToken("ABC123");
        users.setDepartement("Departement");
        users.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setFirstName("Jane");
        users.setUsers_groupes(new ArrayList<Users_groupes>());
        users.setVersions(new ArrayList<Versions>());
        users.setNumtel("Numtel");
        users.setId(123L);
        users.setROLE_PREFIX("ROLE PREFIX");
        users.setImage("Image");

        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");

        Archive archive = new Archive();
        archive.setNumDoss("Num Doss");
        archive.setFiles(new ArrayList<FileEntity>());
        archive.setNomPersonnel("Nom Personnel");
        archive.setSerie("Serie");
        archive.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive.setCin("Cin");
        archive.setTheme_titre("Theme titre");
        archive.setPrivilege("Privilege");
        archive.setMatricule("Matricule");
        archive.setMotdeCle("Motde Cle");
        archive.setVersions(new ArrayList<Versions>());
        archive.setId("42");
        archive.setNbrePieces(1);

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

        FileEntity fileEntity = new FileEntity();
        fileEntity.setUser(users);
        fileEntity.setGroupe(groupe);
        fileEntity.setContentType("text/plain");
        fileEntity.setArchive(archive);
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setChatMessage(chatMessage);
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity.setSize(3L);
        fileEntity.setPrivilege("Privilege");
        Optional<FileEntity> ofResult = Optional.<FileEntity>of(fileEntity);
        when(this.fileService.getFile(anyString())).thenReturn(ofResult);
        when(this.fileService.getVersionsFile(anyString())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/ListVersions/{id}", "",
                "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("AAAAAAAA")));
    }

    @Test
    public void testFilesGroupe() throws Exception {
        // Arrange
        when(this.fileService.getAllFilesGr(anyLong())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/ListFilesGroupe/{groupe_id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetFile() throws Exception {
        // Arrange
        Users users = new Users();
        users.setLastName("Doe");
        users.setEmail("jane.doe@example.org");
        users.setPassword("iloveyou");
        users.setFileEntity(new ArrayList<FileEntity>());
        users.setAppUserRole(AppUserRole.CONTROLEUR);
        users.setResetPasswordToken("ABC123");
        users.setDepartement("Departement");
        users.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users.setFirstName("Jane");
        users.setUsers_groupes(new ArrayList<Users_groupes>());
        users.setVersions(new ArrayList<Versions>());
        users.setNumtel("Numtel");
        users.setId(123L);
        users.setROLE_PREFIX("ROLE PREFIX");
        users.setImage("Image");

        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");

        Archive archive = new Archive();
        archive.setNumDoss("Num Doss");
        archive.setFiles(new ArrayList<FileEntity>());
        archive.setNomPersonnel("Nom Personnel");
        archive.setSerie("Serie");
        archive.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive.setCin("Cin");
        archive.setTheme_titre("Theme titre");
        archive.setPrivilege("Privilege");
        archive.setMatricule("Matricule");
        archive.setMotdeCle("Motde Cle");
        archive.setVersions(new ArrayList<Versions>());
        archive.setId("42");
        archive.setNbrePieces(1);

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

        FileEntity fileEntity = new FileEntity();
        fileEntity.setUser(users);
        fileEntity.setGroupe(groupe);
        fileEntity.setContentType("text/plain");
        fileEntity.setArchive(archive);
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setChatMessage(chatMessage);
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity.setSize(3L);
        fileEntity.setPrivilege("Privilege");
        Optional<FileEntity> ofResult = Optional.<FileEntity>of(fileEntity);
        when(this.fileService.getFile(anyString())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("AAAAAAAA")));
    }

    @Test
    public void testGetFile2() throws Exception {
        // Arrange
        when(this.fileService.getAllFiles()).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/{id}", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testList() throws Exception {
        // Arrange
        when(this.fileService.getAllFiles(anyLong())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/ListFiles/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testListFiles() throws Exception {
        // Arrange
        when(this.fileService.getAllFiles()).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testListFiles2() throws Exception {
        // Arrange
        when(this.fileService.getAllFiles()).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/files");
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testListFilesPublic() throws Exception {
        // Arrange
        when(this.fileService.getAllFilesPublic()).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/files/public");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.filesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }
}

