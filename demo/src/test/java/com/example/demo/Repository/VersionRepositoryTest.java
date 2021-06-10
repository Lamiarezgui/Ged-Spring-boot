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
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {VersionRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class VersionRepositoryTest {
    @Autowired
    private VersionRepository versionRepository;

    @Test
    public void testAjouterVersionsArchive() throws UnsupportedEncodingException {
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
        fileEntity.setUser(users1);
        fileEntity.setGroupe(groupe1);
        fileEntity.setContentType("text/plain");
        fileEntity.setArchive(archive1);
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setChatMessage(chatMessage);
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity.setSize(3L);
        fileEntity.setPrivilege("Privilege");

        Versions versions = new Versions();
        versions.setUser(users);
        versions.setGroupe(groupe);
        versions.setContentType("text/plain");
        versions.setArchive(archive);
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("Name");
        versions.setFileEntity(fileEntity);
        versions.setData("AAAAAAAA".getBytes("UTF-8"));
        versions.setSize(3L);
        versions.setPrivilege("Privilege");

        Users users2 = new Users();
        users2.setLastName("Doe");
        users2.setEmail("jane.doe@example.org");
        users2.setPassword("iloveyou");
        users2.setFileEntity(new ArrayList<FileEntity>());
        users2.setAppUserRole(AppUserRole.CONTROLEUR);
        users2.setResetPasswordToken("ABC123");
        users2.setDepartement("Departement");
        users2.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setFirstName("Jane");
        users2.setUsers_groupes(new ArrayList<Users_groupes>());
        users2.setVersions(new ArrayList<Versions>());
        users2.setNumtel("Numtel");
        users2.setId(123L);
        users2.setROLE_PREFIX("ROLE PREFIX");
        users2.setImage("Image");

        Groupe groupe2 = new Groupe();
        groupe2.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe2.setFiles(new ArrayList<FileEntity>());
        groupe2.setVersions(new ArrayList<Versions>());
        groupe2.setId(123L);
        groupe2.setName("Name");

        Archive archive2 = new Archive();
        archive2.setNumDoss("Num Doss");
        archive2.setFiles(new ArrayList<FileEntity>());
        archive2.setNomPersonnel("Nom Personnel");
        archive2.setSerie("Serie");
        archive2.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive2.setCin("Cin");
        archive2.setTheme_titre("Theme titre");
        archive2.setPrivilege("Privilege");
        archive2.setMatricule("Matricule");
        archive2.setMotdeCle("Motde Cle");
        archive2.setVersions(new ArrayList<Versions>());
        archive2.setId("42");
        archive2.setNbrePieces(1);

        Users users3 = new Users();
        users3.setLastName("Doe");
        users3.setEmail("jane.doe@example.org");
        users3.setPassword("iloveyou");
        users3.setFileEntity(new ArrayList<FileEntity>());
        users3.setAppUserRole(AppUserRole.CONTROLEUR);
        users3.setResetPasswordToken("ABC123");
        users3.setDepartement("Departement");
        users3.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users3.setFirstName("Jane");
        users3.setUsers_groupes(new ArrayList<Users_groupes>());
        users3.setVersions(new ArrayList<Versions>());
        users3.setNumtel("Numtel");
        users3.setId(123L);
        users3.setROLE_PREFIX("ROLE PREFIX");
        users3.setImage("Image");

        Groupe groupe3 = new Groupe();
        groupe3.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe3.setFiles(new ArrayList<FileEntity>());
        groupe3.setVersions(new ArrayList<Versions>());
        groupe3.setId(123L);
        groupe3.setName("Name");

        Archive archive3 = new Archive();
        archive3.setNumDoss("Num Doss");
        archive3.setFiles(new ArrayList<FileEntity>());
        archive3.setNomPersonnel("Nom Personnel");
        archive3.setSerie("Serie");
        archive3.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive3.setCin("Cin");
        archive3.setTheme_titre("Theme titre");
        archive3.setPrivilege("Privilege");
        archive3.setMatricule("Matricule");
        archive3.setMotdeCle("Motde Cle");
        archive3.setVersions(new ArrayList<Versions>());
        archive3.setId("42");
        archive3.setNbrePieces(1);

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
        fileEntity1.setUser(users3);
        fileEntity1.setGroupe(groupe3);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive3);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");

        Versions versions1 = new Versions();
        versions1.setUser(users2);
        versions1.setGroupe(groupe2);
        versions1.setContentType("text/plain");
        versions1.setArchive(archive2);
        versions1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions1.setId("42");
        versions1.setName("Name");
        versions1.setFileEntity(fileEntity1);
        versions1.setData("AAAAAAAA".getBytes("UTF-8"));
        versions1.setSize(3L);
        versions1.setPrivilege("Privilege");
        this.versionRepository.<Versions>save(versions);
        this.versionRepository.<Versions>save(versions1);

        // Act
        this.versionRepository.ajouterVersionsArchive("foo", "foo");

        // Assert
        List<Versions> findAllResult = this.versionRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
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
        fileEntity.setUser(users1);
        fileEntity.setGroupe(groupe1);
        fileEntity.setContentType("text/plain");
        fileEntity.setArchive(archive1);
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setChatMessage(chatMessage);
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity.setSize(3L);
        fileEntity.setPrivilege("Privilege");

        Versions versions = new Versions();
        versions.setUser(users);
        versions.setGroupe(groupe);
        versions.setContentType("text/plain");
        versions.setArchive(archive);
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("Name");
        versions.setFileEntity(fileEntity);
        versions.setData("AAAAAAAA".getBytes("UTF-8"));
        versions.setSize(3L);
        versions.setPrivilege("Privilege");

        Users users2 = new Users();
        users2.setLastName("Doe");
        users2.setEmail("jane.doe@example.org");
        users2.setPassword("iloveyou");
        users2.setFileEntity(new ArrayList<FileEntity>());
        users2.setAppUserRole(AppUserRole.CONTROLEUR);
        users2.setResetPasswordToken("ABC123");
        users2.setDepartement("Departement");
        users2.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setFirstName("Jane");
        users2.setUsers_groupes(new ArrayList<Users_groupes>());
        users2.setVersions(new ArrayList<Versions>());
        users2.setNumtel("Numtel");
        users2.setId(123L);
        users2.setROLE_PREFIX("ROLE PREFIX");
        users2.setImage("Image");

        Groupe groupe2 = new Groupe();
        groupe2.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe2.setFiles(new ArrayList<FileEntity>());
        groupe2.setVersions(new ArrayList<Versions>());
        groupe2.setId(123L);
        groupe2.setName("Name");

        Archive archive2 = new Archive();
        archive2.setNumDoss("Num Doss");
        archive2.setFiles(new ArrayList<FileEntity>());
        archive2.setNomPersonnel("Nom Personnel");
        archive2.setSerie("Serie");
        archive2.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive2.setCin("Cin");
        archive2.setTheme_titre("Theme titre");
        archive2.setPrivilege("Privilege");
        archive2.setMatricule("Matricule");
        archive2.setMotdeCle("Motde Cle");
        archive2.setVersions(new ArrayList<Versions>());
        archive2.setId("42");
        archive2.setNbrePieces(1);

        Users users3 = new Users();
        users3.setLastName("Doe");
        users3.setEmail("jane.doe@example.org");
        users3.setPassword("iloveyou");
        users3.setFileEntity(new ArrayList<FileEntity>());
        users3.setAppUserRole(AppUserRole.CONTROLEUR);
        users3.setResetPasswordToken("ABC123");
        users3.setDepartement("Departement");
        users3.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users3.setFirstName("Jane");
        users3.setUsers_groupes(new ArrayList<Users_groupes>());
        users3.setVersions(new ArrayList<Versions>());
        users3.setNumtel("Numtel");
        users3.setId(123L);
        users3.setROLE_PREFIX("ROLE PREFIX");
        users3.setImage("Image");

        Groupe groupe3 = new Groupe();
        groupe3.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe3.setFiles(new ArrayList<FileEntity>());
        groupe3.setVersions(new ArrayList<Versions>());
        groupe3.setId(123L);
        groupe3.setName("Name");

        Archive archive3 = new Archive();
        archive3.setNumDoss("Num Doss");
        archive3.setFiles(new ArrayList<FileEntity>());
        archive3.setNomPersonnel("Nom Personnel");
        archive3.setSerie("Serie");
        archive3.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive3.setCin("Cin");
        archive3.setTheme_titre("Theme titre");
        archive3.setPrivilege("Privilege");
        archive3.setMatricule("Matricule");
        archive3.setMotdeCle("Motde Cle");
        archive3.setVersions(new ArrayList<Versions>());
        archive3.setId("42");
        archive3.setNbrePieces(1);

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
        fileEntity1.setUser(users3);
        fileEntity1.setGroupe(groupe3);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive3);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");

        Versions versions1 = new Versions();
        versions1.setUser(users2);
        versions1.setGroupe(groupe2);
        versions1.setContentType("text/plain");
        versions1.setArchive(archive2);
        versions1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions1.setId("42");
        versions1.setName("Name");
        versions1.setFileEntity(fileEntity1);
        versions1.setData("AAAAAAAA".getBytes("UTF-8"));
        versions1.setSize(3L);
        versions1.setPrivilege("Privilege");
        this.versionRepository.<Versions>save(versions);
        this.versionRepository.<Versions>save(versions1);

        // Act
        this.versionRepository.updat(1L);

        // Assert
        List<Versions> findAllResult = this.versionRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
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
        fileEntity.setUser(users1);
        fileEntity.setGroupe(groupe1);
        fileEntity.setContentType("text/plain");
        fileEntity.setArchive(archive1);
        fileEntity.setVersions(new ArrayList<Versions>());
        fileEntity.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity.setId("42");
        fileEntity.setChatMessage(chatMessage);
        fileEntity.setName("Name");
        fileEntity.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity.setSize(3L);
        fileEntity.setPrivilege("Privilege");

        Versions versions = new Versions();
        versions.setUser(users);
        versions.setGroupe(groupe);
        versions.setContentType("text/plain");
        versions.setArchive(archive);
        versions.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions.setId("42");
        versions.setName("Name");
        versions.setFileEntity(fileEntity);
        versions.setData("AAAAAAAA".getBytes("UTF-8"));
        versions.setSize(3L);
        versions.setPrivilege("Privilege");

        Users users2 = new Users();
        users2.setLastName("Doe");
        users2.setEmail("jane.doe@example.org");
        users2.setPassword("iloveyou");
        users2.setFileEntity(new ArrayList<FileEntity>());
        users2.setAppUserRole(AppUserRole.CONTROLEUR);
        users2.setResetPasswordToken("ABC123");
        users2.setDepartement("Departement");
        users2.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users2.setFirstName("Jane");
        users2.setUsers_groupes(new ArrayList<Users_groupes>());
        users2.setVersions(new ArrayList<Versions>());
        users2.setNumtel("Numtel");
        users2.setId(123L);
        users2.setROLE_PREFIX("ROLE PREFIX");
        users2.setImage("Image");

        Groupe groupe2 = new Groupe();
        groupe2.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe2.setFiles(new ArrayList<FileEntity>());
        groupe2.setVersions(new ArrayList<Versions>());
        groupe2.setId(123L);
        groupe2.setName("Name");

        Archive archive2 = new Archive();
        archive2.setNumDoss("Num Doss");
        archive2.setFiles(new ArrayList<FileEntity>());
        archive2.setNomPersonnel("Nom Personnel");
        archive2.setSerie("Serie");
        archive2.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive2.setCin("Cin");
        archive2.setTheme_titre("Theme titre");
        archive2.setPrivilege("Privilege");
        archive2.setMatricule("Matricule");
        archive2.setMotdeCle("Motde Cle");
        archive2.setVersions(new ArrayList<Versions>());
        archive2.setId("42");
        archive2.setNbrePieces(1);

        Users users3 = new Users();
        users3.setLastName("Doe");
        users3.setEmail("jane.doe@example.org");
        users3.setPassword("iloveyou");
        users3.setFileEntity(new ArrayList<FileEntity>());
        users3.setAppUserRole(AppUserRole.CONTROLEUR);
        users3.setResetPasswordToken("ABC123");
        users3.setDepartement("Departement");
        users3.setTokenCreationDate(LocalDateTime.of(1, 1, 1, 1, 1));
        users3.setFirstName("Jane");
        users3.setUsers_groupes(new ArrayList<Users_groupes>());
        users3.setVersions(new ArrayList<Versions>());
        users3.setNumtel("Numtel");
        users3.setId(123L);
        users3.setROLE_PREFIX("ROLE PREFIX");
        users3.setImage("Image");

        Groupe groupe3 = new Groupe();
        groupe3.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe3.setFiles(new ArrayList<FileEntity>());
        groupe3.setVersions(new ArrayList<Versions>());
        groupe3.setId(123L);
        groupe3.setName("Name");

        Archive archive3 = new Archive();
        archive3.setNumDoss("Num Doss");
        archive3.setFiles(new ArrayList<FileEntity>());
        archive3.setNomPersonnel("Nom Personnel");
        archive3.setSerie("Serie");
        archive3.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        archive3.setCin("Cin");
        archive3.setTheme_titre("Theme titre");
        archive3.setPrivilege("Privilege");
        archive3.setMatricule("Matricule");
        archive3.setMotdeCle("Motde Cle");
        archive3.setVersions(new ArrayList<Versions>());
        archive3.setId("42");
        archive3.setNbrePieces(1);

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
        fileEntity1.setUser(users3);
        fileEntity1.setGroupe(groupe3);
        fileEntity1.setContentType("text/plain");
        fileEntity1.setArchive(archive3);
        fileEntity1.setVersions(new ArrayList<Versions>());
        fileEntity1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        fileEntity1.setId("42");
        fileEntity1.setChatMessage(chatMessage1);
        fileEntity1.setName("Name");
        fileEntity1.setData("AAAAAAAA".getBytes("UTF-8"));
        fileEntity1.setSize(3L);
        fileEntity1.setPrivilege("Privilege");

        Versions versions1 = new Versions();
        versions1.setUser(users2);
        versions1.setGroupe(groupe2);
        versions1.setContentType("text/plain");
        versions1.setArchive(archive2);
        versions1.setDate(LocalDateTime.of(1, 1, 1, 1, 1));
        versions1.setId("42");
        versions1.setName("Name");
        versions1.setFileEntity(fileEntity1);
        versions1.setData("AAAAAAAA".getBytes("UTF-8"));
        versions1.setSize(3L);
        versions1.setPrivilege("Privilege");
        this.versionRepository.<Versions>save(versions);
        this.versionRepository.<Versions>save(versions1);

        // Act
        this.versionRepository.updatG(1L);

        // Assert
        List<Versions> findAllResult = this.versionRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }
}

