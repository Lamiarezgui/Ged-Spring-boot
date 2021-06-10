package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.example.demo.Repository.ArchiveRepository;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Repository.VersionRepository;
import com.example.demo.Services.ArchiveService;
import com.example.demo.Services.FileService;
import com.example.demo.Services.VersionsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ArchiveController.class})
@ExtendWith(SpringExtension.class)
public class ArchiveControllerTest {
    @Autowired
    private ArchiveController archiveController;

    @MockBean
    private ArchiveRepository archiveRepository;

    @MockBean
    private ArchiveService archiveService;

    @MockBean
    private FileService fileService;

    @MockBean
    private FilesController filesController;

    @Test
    public void testAddDoss() throws Exception {
        // Arrange
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
        String content = (new ObjectMapper()).writeValueAsString(archive);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/archive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.archiveController)
                .build()
                .perform(requestBuilder);
        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400)); }

    @Test
    public void testAddFiles() throws UnsupportedEncodingException {
        // Arrange
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
        ArchiveRepository archiveRepository = mock(ArchiveRepository.class);
        when(archiveRepository.getOne(anyString())).thenReturn(archive);
        when(archiveRepository.findByNumDoss(anyString())).thenReturn("foo");
        ArchiveService archiveService = new ArchiveService(mock(ArchiveRepository.class), mock(VersionRepository.class),
                mock(FichierRepository.class));
        FileService fileService = new FileService(mock(FichierRepository.class));
        FileService fileService1 = new FileService(mock(FichierRepository.class));
        ArchiveController archiveController = new ArchiveController(archiveService, fileService, archiveRepository,
                new FilesController(fileService1,
                        new VersionsService(mock(VersionRepository.class), mock(FichierRepository.class)),
                        mock(UsersRepository.class), mock(FichierRepository.class)));
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

        // Act
        ResponseEntity<String> actualAddFilesResult = archiveController.addFiles(file, "Num Doss",
                new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR, "Image"));

        // Assert
        assertEquals("File uploaded successfully: ", actualAddFilesResult.getBody());
        assertEquals("<200 OK OK,File uploaded successfully: ,[]>", actualAddFilesResult.toString());
        assertEquals(HttpStatus.OK, actualAddFilesResult.getStatusCode());
        verify(archiveRepository).getOne(anyString());
        verify(archiveRepository).findByNumDoss(anyString());
    }

    @Test
    public void testAddFiles2() throws UnsupportedEncodingException {
        // Arrange
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
        ArchiveRepository archiveRepository = mock(ArchiveRepository.class);
        when(archiveRepository.getOne(anyString())).thenReturn(archive);
        when(archiveRepository.findByNumDoss(anyString())).thenReturn("foo");
        ArchiveService archiveService = new ArchiveService(mock(ArchiveRepository.class), mock(VersionRepository.class),
                mock(FichierRepository.class));
        FileService fileService = new FileService(mock(FichierRepository.class));
        ArchiveController archiveController = new ArchiveController(archiveService, null, archiveRepository,
                new FilesController(fileService,
                        new VersionsService(mock(VersionRepository.class), mock(FichierRepository.class)),
                        mock(UsersRepository.class), mock(FichierRepository.class)));
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

        // Act
        ResponseEntity<String> actualAddFilesResult = archiveController.addFiles(file, "Num Doss",
                new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR, "Image"));

        // Assert
        assertEquals("Could not upload the file: !", actualAddFilesResult.getBody());
        assertEquals("<500 INTERNAL_SERVER_ERROR Internal Server Error,Could not upload the file: !,[]>",
                actualAddFilesResult.toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualAddFilesResult.getStatusCode());
        verify(archiveRepository).getOne(anyString());
        verify(archiveRepository).findByNumDoss(anyString());
    }

    @Test
    public void testAddFiles3() {
        // Arrange
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
        ArchiveRepository archiveRepository = mock(ArchiveRepository.class);
        when(archiveRepository.getOne(anyString())).thenReturn(archive);
        when(archiveRepository.findByNumDoss(anyString())).thenReturn("foo");
        ArchiveService archiveService = new ArchiveService(mock(ArchiveRepository.class), mock(VersionRepository.class),
                mock(FichierRepository.class));
        FileService fileService = new FileService(mock(FichierRepository.class));
        FileService fileService1 = new FileService(mock(FichierRepository.class));
        ArchiveController archiveController = new ArchiveController(archiveService, fileService, archiveRepository,
                new FilesController(fileService1,
                        new VersionsService(mock(VersionRepository.class), mock(FichierRepository.class)),
                        mock(UsersRepository.class), mock(FichierRepository.class)));
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("foo");

        // Act
        ResponseEntity<String> actualAddFilesResult = archiveController.addFiles(multipartFile, "Num Doss",
                new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR, "Image"));

        // Assert
        assertEquals("File uploaded successfully: foo", actualAddFilesResult.getBody());
        assertEquals("<200 OK OK,File uploaded successfully: foo,[]>", actualAddFilesResult.toString());
        assertEquals(HttpStatus.OK, actualAddFilesResult.getStatusCode());
        verify(archiveRepository).getOne(anyString());
        verify(archiveRepository).findByNumDoss(anyString());
        verify(multipartFile, times(2)).getOriginalFilename();
    }

    @Test
    public void testAddDoss2() throws Exception {
        // Arrange
        doNothing().when(this.archiveService).save((Archive) any());

        Archive archive = new Archive();
        archive.setNumDoss("Num Doss");
        archive.setFiles(new ArrayList<FileEntity>());
        archive.setNomPersonnel("Nom Personnel");
        archive.setSerie("Serie");
        archive.setDate(null);
        archive.setCin("Cin");
        archive.setTheme_titre("Theme titre");
        archive.setPrivilege("Privilege");
        archive.setMatricule("Matricule");
        archive.setMotdeCle("Motde Cle");
        archive.setVersions(new ArrayList<Versions>());
        archive.setId("42");
        archive.setNbrePieces(1);
        String content = (new ObjectMapper()).writeValueAsString(archive);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/archive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.archiveController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddToArchive() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(fileEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/archive/{archive_id}", "Archive id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.archiveController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testGetContenu() throws Exception {
        // Arrange
        when(this.archiveService.getContenus(anyString())).thenReturn(new ArrayList<Object>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/archive/contenu/{numDoss}", "Num Doss");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.archiveController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }
}

