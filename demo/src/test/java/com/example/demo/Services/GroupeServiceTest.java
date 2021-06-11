package com.example.demo.Services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.GroupeRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Repository.VersionRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GroupeService.class})
@ExtendWith(SpringExtension.class)
public class GroupeServiceTest {
    @MockBean
    private FichierRepository fichierRepository;

    @MockBean
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeService groupeService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private VersionRepository versionRepository;

    @Test
    public void testAjouterGroupe() {
        // Arrange
        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");
        groupe.setControleur(Long.valueOf(1));
        when(this.groupeRepository.save((Groupe) any())).thenReturn(groupe);

        // Act
        this.groupeService.ajouterGroupe("Name",1);

        // Assert
        verify(this.groupeRepository).save((Groupe) any());
    }

    @Test
    public void testModifierName() {
        // Arrange
        doNothing().when(this.groupeRepository).modifiername((Long) any(), anyString());

        // Act
        this.groupeService.modifierName("Name", 123L);

        // Assert
        verify(this.groupeRepository).modifiername((Long) any(), anyString());
    }

    @Test
    public void testGetAllGroupes() {
        // Arrange
        ArrayList<Groupe> groupeList = new ArrayList<Groupe>();
        when(this.groupeRepository.findAll()).thenReturn(groupeList);

        // Act
        List<Object> actualAllGroupes = this.groupeService.getAllGroupes();

        // Assert
        assertSame(groupeList, actualAllGroupes);
        assertTrue(actualAllGroupes.isEmpty());
        verify(this.groupeRepository).findAll();
    }

    @Test
    public void testGetGroupes() {
        // Arrange
        ArrayList<Object> objectList = new ArrayList<Object>();
        when(this.groupeRepository.findGroupes(anyLong())).thenReturn(objectList);

        // Act
        List<Object> actualGroupes = this.groupeService.getGroupes(123L);

        // Assert
        assertSame(objectList, actualGroupes);
        assertTrue(actualGroupes.isEmpty());
        verify(this.groupeRepository).findGroupes(anyLong());
    }

    @Test
    public void testUpdateG() {
        // Arrange
        doNothing().when(this.versionRepository).updatG(anyLong());
        doNothing().when(this.usersRepository).updatG(anyLong());
        doNothing().when(this.fichierRepository).updatG(anyLong());

        // Act
        this.groupeService.updateG(123L);

        // Assert
        verify(this.fichierRepository).updatG(anyLong());
        verify(this.usersRepository).updatG(anyLong());
        verify(this.versionRepository).updatG(anyLong());
    }

    @Test
    public void testDelete() {
        // Arrange
        doNothing().when(this.groupeRepository).delete((Groupe) any());

        // Act
        this.groupeService.delete(new Groupe());

        // Assert
        verify(this.groupeRepository).delete((Groupe) any());
    }
}

