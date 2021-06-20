package com.example.demo.Controller;

import com.example.demo.Model.Archive;
import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Users;
import com.example.demo.Repository.ArchiveRepository;

import com.example.demo.Services.ArchiveService;
import com.example.demo.Services.FileService;
import lombok.AllArgsConstructor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@RestController
@AllArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    //ajouter les details du dossier
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @PostMapping("archive")
    public void addDoss(@RequestBody Archive archive) {
        archiveService.save(archive);
    }

    // afficher la liste des dossiers
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("archive")
    public List<Object> list() {
        return archiveService.getAllDossiers();

    }

    // countDoss per month
    @GetMapping("/countDossPerMonth")
    public List<Object> countFiles() {
        return archiveRepository.countDossMonth();

    }

    // countDoss per year
    @GetMapping("/countDossPerYear")
    public List<Object> countFilesperYear() {
        return archiveRepository.countDossYear();

    }

    //count Doss per day
    @GetMapping("/countDossPerDay")
    public List<Object> countFilesperDay() {
        return archiveRepository.countDossDay();

    }

    private static final int BUFFER_SIZE = 2 * 1024;

    @GetMapping("/zip/{numDoss}")
    public void toZip(@PathVariable String numDoss, OutputStream out, HttpServletResponse response) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(out);
            List<String> list = archiveRepository.getIdfiles(numDoss);
            for (Object l : list) {
                List<File> srcFiles = (List<File>) filesController.getFile(l.toString());

                for (File srcFile : srcFiles) {
                    byte[] buf = new byte[BUFFER_SIZE];
                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    int len;
                    FileInputStream in = new FileInputStream(srcFile);
                    while ((len = in.read(buf)) != -1) {
                        zos.write(buf, 0, len);
                    }
                    zos.closeEntry();
                    in.close();
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/x-msdownload");
                    response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + srcFile.getName());
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("Compression completed, time consuming:" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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

    //ajouter un fichier et ses versions existe deja dans l'app
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @PutMapping("archive/{archive_id}")
    public void addToArchive(@PathVariable("archive_id") String archive_id, @RequestBody FileEntity f) {
        archiveService.addToArchive(f.getId(), archive_id);
    }

    private final FileService fileService;

    //ajouter un fichier au dossier
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @PostMapping("archive/files/{numDoss}")
    public ResponseEntity<String> addFiles(@RequestParam("file") MultipartFile file, @PathVariable("numDoss") String numDoss, @RequestParam("user") Users user) {
        String id = archiveRepository.findByNumDoss(numDoss);
        Archive archive = archiveRepository.getOne(id);
        try {
            fileService.saveArchive(file, user, archive, "PUBLIC");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }


    //afficher le contenu du dossier d'archive
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("archive/contenu/{numDoss}")
    public List<Object> getContenu(@PathVariable("numDoss") String numDoss) {
        return archiveService.getContenus(numDoss);
    }

    private final ArchiveRepository archiveRepository;

    FilesController filesController;

    //download files in archive
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("archive/{numDoss}")
    public ResponseEntity<byte[]> downLoad(@PathVariable("numDoss") String numDoss) {

        List<String> list = archiveRepository.getIdfiles(numDoss);
        for (Object l : list) {
            return filesController.getFile(l.toString());
        }
        return downLoad(numDoss);
    }


}
