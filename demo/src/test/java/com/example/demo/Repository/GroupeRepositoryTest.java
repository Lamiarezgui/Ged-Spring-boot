package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users_groupes;
import com.example.demo.Model.Versions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {GroupeRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class GroupeRepositoryTest {
    @Autowired
    private GroupeRepository groupeRepository;

    @Test
    public void testModifiername() {
        // Arrange
        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");
        this.groupeRepository.<Groupe>save(groupe);
        this.groupeRepository.<Groupe>save(groupe1);

        // Act
        this.groupeRepository.modifiername(1L, "foo");

        // Assert
        List<Groupe> findAllResult = this.groupeRepository.findAll();
        assertTrue(findAllResult instanceof java.util.LinkedList);
        assertTrue(findAllResult.isEmpty());
    }

    @Test
    public void testFindGroupes() {
        // Arrange
        Groupe groupe = new Groupe();
        groupe.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe.setFiles(new ArrayList<FileEntity>());
        groupe.setVersions(new ArrayList<Versions>());
        groupe.setId(123L);
        groupe.setName("Name");

        Groupe groupe1 = new Groupe();
        groupe1.setUsers_groupes(new ArrayList<Users_groupes>());
        groupe1.setFiles(new ArrayList<FileEntity>());
        groupe1.setVersions(new ArrayList<Versions>());
        groupe1.setId(123L);
        groupe1.setName("Name");
        this.groupeRepository.<Groupe>save(groupe);
        this.groupeRepository.<Groupe>save(groupe1);

        // Act and Assert
        assertTrue(this.groupeRepository.findGroupes(1L).isEmpty());
    }
}

