package com.example.demo.Controller;

import com.example.demo.Model.Archive;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Users;
import com.example.demo.Repository.ArchiveRepository;

import com.example.demo.Services.ArchiveService;
import com.example.demo.Services.FileService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@AllArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    //ajouter les details du dossier
    @PostMapping("archive")
    public void addDoss(@RequestBody Archive archive) {
        archiveService.save(archive);
    }

    // afficher la liste des dossiers
    @GetMapping("archive")
    public List<Object> list() {
        return archiveService.getAllDossiers();

    }

    //ajouter un fichier et ses versions existe deja dans l'app
    @PutMapping("archive/{archive_id}")
    public void addToArchive(@PathVariable("archive_id") String archive_id, @RequestBody FileEntity f) {
        archiveService.addToArchive(f.getId(), archive_id);
    }

    private final FileService fileService;

    //ajouter un fichier au dossier
    @PostMapping("archive/files/{numDoss}")
    public ResponseEntity<String> addFiles(@RequestParam("file") MultipartFile file, @PathVariable("numDoss") String numDoss, @RequestParam("user") Users user) {
        String id = archiveRepository.findByNumDoss(numDoss);
        Archive archive = archiveRepository.getOne(id);
        try {
            fileService.saveArchive(file,user,archive,"PUBLIC");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }


    //afficher le contenu du dossier d'archive
    @GetMapping("archive/contenu/{numDoss}")
    public List<Object> getContenu(@PathVariable("numDoss") String numDoss) {
        return archiveService.getContenus(numDoss);
    }

    private final ArchiveRepository archiveRepository;

    FilesController filesController;

    //download files in archive
    @GetMapping("archive/{numDoss}")
    public ResponseEntity<byte[]> downLoad(@PathVariable("numDoss") String numDoss) {

        List<String> list = archiveRepository.getIdfiles(numDoss);
        for (Object l : list) {
            return filesController.getFile(l.toString());
        }
        return downLoad(numDoss);
    }


    @GetMapping(value = "/zip-download/{id}")
    public void zipDownload(@PathVariable String id, HttpServletResponse response) throws IOException {
        List<String> liste = archiveService.getFiles(id);
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (String ids : liste) {
            Optional<FileEntity> fileEntityOptional = fileService.getFile(ids);
            ZipEntry zipEntry = new ZipEntry(fileEntityOptional.get().getName());
            zipOut.putNextEntry(zipEntry);
            zipOut.closeEntry();

        }
        zipOut.finish();
        zipOut.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/x-msdownload");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=111");
    }


}
