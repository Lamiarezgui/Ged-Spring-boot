package com.example.demo.Controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.AppUserRole;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Users;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.GroupeRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Repository.Users_groupesRepository;
import com.example.demo.Repository.VersionRepository;
import com.example.demo.Services.UsersService;
import com.example.demo.mail.EmailSender;
import com.example.demo.mail.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
public class UsersControllerTest {
    @MockBean
    private EmailSender emailSender;

    @MockBean
    private FichierRepository fichierRepository;

    @MockBean
    private GroupeRepository groupeRepository;

    @Autowired
    private UsersController usersController;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private UsersService usersService;

    @MockBean
    private VersionRepository versionRepository;

    @Test
    public void testUpdateImg() throws UnsupportedEncodingException {
        // Arrange
        UsersRepository usersRepository = mock(UsersRepository.class);
        doNothing().when(usersRepository).updateImg(anyString(), anyLong());
        UsersService usersService = new UsersService(usersRepository, new BCryptPasswordEncoder(),
                mock(FichierRepository.class), mock(VersionRepository.class), mock(GroupeRepository.class),
                mock(Users_groupesRepository.class));
        UsersRepository usersRepository1 = mock(UsersRepository.class);
        FichierRepository fichierRepository = mock(FichierRepository.class);
        VersionRepository versionRepository = mock(VersionRepository.class);
        GroupeRepository groupeRepository = mock(GroupeRepository.class);
        UsersController usersController = new UsersController(usersService, usersRepository1, fichierRepository,
                versionRepository, groupeRepository, new EmailService(new JavaMailSenderImpl()));

        // Act
        usersController.updateImg(new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), 123L);

        // Assert
        verify(usersRepository).updateImg(anyString(), anyLong());
        assertFalse(usersController.getAdmins().isPresent());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        when(this.usersService.getAllUsers()).thenReturn(new ArrayList<Users>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetAllUsers2() throws Exception {
        // Arrange
        when(this.usersService.getAllUsers()).thenReturn(new ArrayList<Users>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users");
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetAdmins() throws Exception {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.usersService.getAdmins()).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/administrateurs");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"numtel\":\"Numtel\","
                                        + "\"password\":\"iloveyou\",\"appUserRole\":\"CONTROLEUR\",\"departement\":\"Departement\",\"users_groupes\":[],"
                                        + "\"fileEntity\":[],\"versions\":[],\"image\":\"Image\",\"resetPasswordToken\":\"ABC123\",\"tokenCreationDate\":[1,1"
                                        + ",1,1,1],\"authorities\":[{\"authority\":\"ROLE PREFIXCONTROLEUR\"}],\"username\":\"jane.doe@example.org\","
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true,\"role"
                                        + "_PREFIX\":\"ROLE PREFIX\"}")));
    }

    @Test
    public void testGetSuperviseurs() throws Exception {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.usersService.getSuperviseurs()).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/superviseurs");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"numtel\":\"Numtel\","
                                        + "\"password\":\"iloveyou\",\"appUserRole\":\"CONTROLEUR\",\"departement\":\"Departement\",\"users_groupes\":[],"
                                        + "\"fileEntity\":[],\"versions\":[],\"image\":\"Image\",\"resetPasswordToken\":\"ABC123\",\"tokenCreationDate\":[1,1"
                                        + ",1,1,1],\"authorities\":[{\"authority\":\"ROLE PREFIXCONTROLEUR\"}],\"username\":\"jane.doe@example.org\","
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true,\"role"
                                        + "_PREFIX\":\"ROLE PREFIX\"}")));
    }

    @Test
    public void testGetControleurs() throws Exception {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.usersService.getControleurs()).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/controleurs");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"numtel\":\"Numtel\","
                                        + "\"password\":\"iloveyou\",\"appUserRole\":\"CONTROLEUR\",\"departement\":\"Departement\",\"users_groupes\":[],"
                                        + "\"fileEntity\":[],\"versions\":[],\"image\":\"Image\",\"resetPasswordToken\":\"ABC123\",\"tokenCreationDate\":[1,1"
                                        + ",1,1,1],\"authorities\":[{\"authority\":\"ROLE PREFIXCONTROLEUR\"}],\"username\":\"jane.doe@example.org\","
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true,\"role"
                                        + "_PREFIX\":\"ROLE PREFIX\"}")));
    }

    @Test
    public void testGetUsers() throws Exception {
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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.usersService.getUsers(anyLong())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(Matchers.containsString(
                                "{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"numtel\":\"Numtel\","
                                        + "\"password\":\"iloveyou\",\"appUserRole\":\"CONTROLEUR\",\"departement\":\"Departement\",\"users_groupes\":[],"
                                        + "\"fileEntity\":[],\"versions\":[],\"image\":\"Image\",\"resetPasswordToken\":\"ABC123\",\"tokenCreationDate\":[1,1"
                                        + ",1,1,1],\"authorities\":[{\"authority\":\"ROLE PREFIXCONTROLEUR\"}],\"username\":\"jane.doe@example.org\","
                                        + "\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true,\"role"
                                        + "_PREFIX\":\"ROLE PREFIX\"}")));
    }

    @Test
    public void testAjouterUser() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groupe/user/{id_g}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        doNothing().when(this.usersService).del((Users) any());
        doNothing().when(this.usersService).update(anyLong());

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
        Optional<Users> ofResult = Optional.<Users>of(users);
        when(this.usersRepository.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("utilisateur supprime")));
    }

    @Test
    public void testDeleteUser2() throws Exception {
        // Arrange
        doNothing().when(this.usersService).del((Users) any());
        doNothing().when(this.usersService).update(anyLong());
        when(this.usersRepository.findById((Long) any())).thenReturn(Optional.<Users>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{id}", 123L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testForgotPassword() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/forgot-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testGetGroupes() throws Exception {
        // Arrange
        when(this.usersService.getUsersg(anyLong())).thenReturn(new ArrayList<Users>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groupeUsers/{id}", 123L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testGetGroupes2() throws Exception {
        // Arrange
        when(this.usersService.getUsersg(anyLong())).thenReturn(new ArrayList<Users>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/groupeUsers/{id}", 123L);
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("[]")));
    }

    @Test
    public void testModifPass() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/modifpass/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testModifUserIng() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/IngUpdate/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testResetPassword() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/reset-password")
                .param("token", "foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testUpdateUsers() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.usersController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

