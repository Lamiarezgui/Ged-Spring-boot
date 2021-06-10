package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.AppUserRole;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Users;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UsersRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testFindByEmail() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertNull(this.usersRepository.findByEmail("foo"));
    }

    @Test
    public void testUpdateUsers() {
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
        this.usersRepository.<Users>save(users);
        // Act
        this.usersRepository.UpdateUsers("foo", "foo", "foo", "foo", "foo", 1L);
        // Assert
        List<Users> findAllResult = this.usersRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }

    @Test
    public void testUpdatG() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act
        this.usersRepository.updatG(1L);

        // Assert
        List<Users> findAllResult = this.usersRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }

    @Test
    public void testGetSuperviseurs() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertFalse(this.usersRepository.getSuperviseurs().isPresent());
    }

    @Test
    public void testGetAdmins() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertFalse(this.usersRepository.getAdmins().isPresent());
    }

    @Test
    public void testGetControleurs() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertFalse(this.usersRepository.getControleurs().isPresent());
    }

    @Test
    public void testUpdatePass() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act
        this.usersRepository.updatePass("foo", 1L);

        // Assert
        List<Users> findAllResult = this.usersRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }

    @Test
    public void testFindByResetPasswordToken() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertNull(this.usersRepository.findByResetPasswordToken("foo"));
    }

    @Test
    public void testUpdateImg() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act
        this.usersRepository.updateImg("foo", 1L);

        // Assert
        List<Users> findAllResult = this.usersRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }

    @Test
    public void testUpdateUsersIng() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act
        this.usersRepository.UpdateUsersIng("foo", "foo", "foo", "foo", AppUserRole.CONTROLEUR, 1L);

        // Assert
        List<Users> findAllResult = this.usersRepository.findAll();
        assertTrue(findAllResult instanceof LinkedList);
        assertTrue(((LinkedList) findAllResult).isEmpty());
    }

    @Test
    public void testFindUsers() {
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
        this.usersRepository.<Users>save(users);
        this.usersRepository.<Users>save(users1);

        // Act and Assert
        assertTrue(this.usersRepository.findUsers(1L).isEmpty());
    }
}

