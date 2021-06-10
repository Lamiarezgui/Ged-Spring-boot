package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Model.Archive;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Versions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {ArchiveRepository.class})
@EnableAutoConfiguration
@DataJpaTest
public class ArchiveRepositoryTest {
    @Autowired
    private ArchiveRepository archiveRepository;

    @Test
    public void testGetAll() {
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
        this.archiveRepository.<Archive>save(archive);
        this.archiveRepository.<Archive>save(archive1);

        // Act and Assert
        assertTrue(this.archiveRepository.getAll().isEmpty());
    }

    @Test
    public void testGetContenu() {
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
        this.archiveRepository.<Archive>save(archive);

        // Act and Assert
        assertTrue(this.archiveRepository.getContenu("foo").isEmpty());
    }

    @Test
    public void testGetIdfiles() {
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
        this.archiveRepository.<Archive>save(archive);
        this.archiveRepository.<Archive>save(archive1);

        // Act and Assert
        assertTrue(this.archiveRepository.getIdfiles("foo").isEmpty());
    }

    @Test
    public void testFindByNumDoss() {
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
        this.archiveRepository.<Archive>save(archive);
        this.archiveRepository.<Archive>save(archive1);

        // Act and Assert
        assertNull(this.archiveRepository.findByNumDoss("foo"));
    }
}

