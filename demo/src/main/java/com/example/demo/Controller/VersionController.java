package com.example.demo.Controller;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.VersionRepository;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.Services.VersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("files/versions")
public class VersionController {

    @Autowired
    private final VersionsService versionsService;
    @Autowired
    private final VersionRepository versionRepository;

    @Autowired
    public VersionController(VersionsService versionsService, VersionRepository versionRepository) {

        this.versionsService = versionsService;
        this.versionRepository = versionRepository;
    }
//afficher la liste des versions ma3andk mata3ml beha normalement

    @GetMapping
    public List<Versions> list() {
        return versionsService.getAllVersions()
                .stream()
                .map(this::mapToVersions)
                .collect(Collectors.toList());
    }


    public Versions mapToVersions(Versions versions) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/versions")
                .path(versions.getId())
                .toUriString();
        Versions fileResponse = new Versions();
        fileResponse.setId(versions.getId());
        fileResponse.setName(versions.getName());
        fileResponse.setContentType(versions.getContentType());
        fileResponse.setSize(versions.getSize());
        fileResponse.setDate(versions.getDate());


        return fileResponse;
    }
//exporter une version
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getVersion(@PathVariable String id) {
        Optional<Versions> versionsOptional = versionsService.getVersions(id);

        if (!versionsOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        Versions versions = versionsOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + versions.getName() + "\"")
                .contentType(MediaType.valueOf(versions.getContentType()))
                .body(versions.getData());
    }
    //visualiser une version
    @GetMapping("viewVersion/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String id) {
        Optional<Versions> versionsOptional = versionsService.getVersions(id);

        if (!versionsOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }


        Versions versions = versionsOptional.get();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(versions.getContentType()))
                .body(versions.getData());
    }

    //supprimer une version
    @DeleteMapping("/{id}")
    public String deleteVersion(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        Versions file = versionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File n'existe pas :: " + id));

        versionRepository.delete(file);

        return "file deleted";
    }

}
