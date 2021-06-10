package com.example.demo.Services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
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
import com.example.demo.Repository.FichierRepository;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FileService.class})
@ExtendWith(SpringExtension.class)
public class FileServiceTest {
    @MockBean
    private FichierRepository fichierRepository;

    @Autowired
    private FileService fileService;

    @Test
    public void testSave() throws IOException {
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
        when(this.fichierRepository.save((FileEntity) any())).thenReturn(fileEntity);
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

        // Act
        this.fileService.save(file,
                new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR, "Image"),
                "Priv");

        // Assert
        verify(this.fichierRepository).save((FileEntity) any());
    }

    @Test
    public void testSaveGr() throws IOException {
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
        when(this.fichierRepository.save((FileEntity) any())).thenReturn(fileEntity);
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
        Users user = new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR,
                "Image");

        // Act
        this.fileService.saveGr(file, user, new Groupe(), "Priv");

        // Assert
        verify(this.fichierRepository).save((FileEntity) any());
    }

    @Test
    public void testGetFile() throws UnsupportedEncodingException {
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
        when(this.fichierRepository.findById(anyString())).thenReturn(ofResult);

        // Act
        Optional<FileEntity> actualFile = this.fileService.getFile("42");

        // Assert
        assertSame(ofResult, actualFile);
        assertTrue(actualFile.isPresent());
        verify(this.fichierRepository).findById(anyString());
    }

    @Test
    public void testGetAllFiles() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.fichierRepository.getAllFiles()).thenReturn(objectList);

        // Act
        List<Object> actualAllFiles = this.fileService.getAllFiles();

        // Assert
        assertSame(objectList, actualAllFiles);
        assertTrue(actualAllFiles.isEmpty());
        verify(this.fichierRepository).getAllFiles();
    }

    @Test
    public void testGetAllFiles2() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.fichierRepository.getAllFilesP(anyLong())).thenReturn(objectList);

        // Act
        List<Object> actualAllFiles = this.fileService.getAllFiles(123L);

        // Assert
        assertSame(objectList, actualAllFiles);
        assertTrue(actualAllFiles.isEmpty());
        verify(this.fichierRepository).getAllFilesP(anyLong());
    }

    @Test
    public void testSaveArchive() throws IOException {
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
        when(this.fichierRepository.save((FileEntity) any())).thenReturn(fileEntity);
        MockMultipartFile file = new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
        Users user = new Users("Jane", "Doe", "jane.doe@example.org", "Numtel", "iloveyou", AppUserRole.CONTROLEUR,
                "Image");

        // Act
        this.fileService.saveArchive(file, user, new Archive(), "Privilege");

        // Assert
        verify(this.fichierRepository).save((FileEntity) any());
    }

    @Test
    public void testGetAllFilesGr() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.fichierRepository.getAllFilesG(anyLong())).thenReturn(objectList);

        // Act
        List<Object> actualAllFilesGr = this.fileService.getAllFilesGr(123L);

        // Assert
        assertSame(objectList, actualAllFilesGr);
        assertTrue(actualAllFilesGr.isEmpty());
        verify(this.fichierRepository).getAllFilesG(anyLong());
    }

    @Test
    public void testGetAllFilesPublic() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.fichierRepository.getAllFilesPublic()).thenReturn(objectList);

        // Act
        List<Object> actualAllFilesPublic = this.fileService.getAllFilesPublic();

        // Assert
        assertSame(objectList, actualAllFilesPublic);
        assertTrue(actualAllFilesPublic.isEmpty());
        verify(this.fichierRepository).getAllFilesPublic();
    }

    @Test
    public void testGetVersionsFile() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.fichierRepository.getVersionFile(anyString())).thenReturn(objectList);

        // Act
        List<Object> actualVersionsFile = this.fileService.getVersionsFile("42");

        // Assert
        assertSame(objectList, actualVersionsFile);
        assertTrue(actualVersionsFile.isEmpty());
        verify(this.fichierRepository).getVersionFile(anyString());
    }
}

