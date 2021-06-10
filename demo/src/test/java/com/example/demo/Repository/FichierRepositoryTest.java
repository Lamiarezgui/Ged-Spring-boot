package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.AppUserRole;
import com.example.demo.Model.Archive;
import com.example.demo.Model.ChatMessage;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.MessageStatus;
import com.example.demo.Model.Users;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {FichierRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class FichierRepositoryTest {
    @Autowired
    private FichierRepository fichierRepository;

    @Test
    public void testGetVersionFile() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.getVersionFile("foo").isEmpty());
    }

    @Test
    public void testGetAllFilesP() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.getAllFilesP(1L).isEmpty());
    }

    @Test
    public void testAjouterFilesArchive() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act
        this.fichierRepository.ajouterFilesArchive("foo", "foo");

        // Assert
        List<FileEntity> findAllResult = this.fichierRepository.findAll();
        assertTrue(findAllResult instanceof java.util.LinkedList);
        assertTrue(findAllResult.isEmpty());
    }

    @Test
    public void testFindFileById() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.findFileById("foo").isEmpty());
    }

    @Test
    public void testGetAllFilesG() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.getAllFilesG(1L).isEmpty());
    }

    @Test
    public void testGetAllFilesPublic() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.getAllFilesPublic().isEmpty());
    }

    @Test
    public void testUpdat() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act
        this.fichierRepository.updat(1L);

        // Assert
        List<FileEntity> findAllResult = this.fichierRepository.findAll();
        assertTrue(findAllResult instanceof java.util.LinkedList);
        assertTrue(findAllResult.isEmpty());
    }

    @Test
    public void testUpdatG() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act
        this.fichierRepository.updatG(1L);

        // Assert
        List<FileEntity> findAllResult = this.fichierRepository.findAll();
        assertTrue(findAllResult instanceof java.util.LinkedList);
        assertTrue(findAllResult.isEmpty());
    }

    @Test
    public void testGetAllFiles() throws UnsupportedEncodingException {
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

        Users users1 = new Users();
        users1.setLastName("Doe");
        users1.setEmail("jane.doe@example.org");
        users1.setPassword("iloveyou");
        users1.setFileEntity(new ArrayList<FileEntity>());
        users1.setAppUserRole(AppUserRole.CONTROLEUR);
        users1.setResetPasswordToken("ABC123");
        users1.setDepartement("Departement");
        users1.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users1.setFirstName("Jane");
        users1.setUsers_groupes(new ArrayList<Users_groupes>());
        users1.setVersions(new ArrayList<Versions>());
        users1.setNumtel("Numtel");
        users1.setId(123L);
        users1.setROLE_PREFIX("ROLE PREFIX");
        users1.setImage("Image");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");

        Archive archive1 = new Archive();
        archive1.setNumDoss("Num Doss");
        archive1.setFiles(new ArrayList<FileEntity>());
        archive1.setNomPersonnel("Nom Personnel");
        archive1.setSerie("Serie");
        archive1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive1.setCin("Cin");
        archive1.setTheme_titre("Theme titre");
        archive1.setPrivilege("Privilege");
        archive1.setMatricule("Matricule");
        archive1.setMotdeCle("Motde Cle");
        archive1.setVersions(new ArrayList<Versions>());
        archive1.setId("42");
        archive1.setNbrePieces(1);

        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setSenderId("42");
        chatMessage1.setFiles(new ArrayList<FileEntity>());
        chatMessage1.setRecipientId("42");
        chatMessage1.setChatId("42");
        chatMessage1.setId("42");
        chatMessage1.setSenderName("Sender Name");
        chatMessage1.setRecipientName("Recipient Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chatMessage1.setTimestamp(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        chatMessage1.setStatus(MessageStatus.RECEIVED);
        chatMessage1.setContent("Not all who wander are lost");

        FileEntity fileEntity1 = new FileEntity();
        fileEntity1.setUser(users1);
        fileEntity1.setGroupe(groupe1);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive1);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");
        this.fichierRepository.<FileEntity>save(fileEntity);
        this.fichierRepository.<FileEntity>save(fileEntity1);

        // Act and Assert
        assertTrue(this.fichierRepository.getAllFiles().isEmpty());
    }
}

