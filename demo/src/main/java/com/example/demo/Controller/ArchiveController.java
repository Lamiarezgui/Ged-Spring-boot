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


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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


    /*
        @GetMapping("/zip/{numDoss}")
        public void toZip(@PathVariable String numDoss, OutputStream out, HttpServletResponse response) throws RuntimeException {
            long start = System.currentTimeMillis();
            ZipOutputStream zos = null;

            try {
                zos = new ZipOutputStream(out);
                List<String> list = archiveRepository.getIdfiles(numDoss);
                for (String l : list) {
                    ResponseEntity<byte[]> srcFiles = filesController.getFile(l);

                    for (byte srcFile : srcFiles) {
                        byte[] buf = new byte[BUFFER_SIZE];
                        zos.putNextEntry(new ZipEntry(srcFiles.getClass().getName()));
                        int len;
                        FileInputStream in = new FileInputStream(String.valueOf(srcFile));
                        while ((len = in.read(buf)) != -1) {
                            zos.write(buf, 0, len);
                        }
                        zos.closeEntry();
                        in.close();
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setContentType("application/x-msdownload");
                        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archive "+numDoss);
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
    */



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
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("archive/{numDoss}")
    public void doGet(@PathVariable("numDoss") String numDoss, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set the content type based to zip
        response.setHeader("Content-Type", "application/zip");
        response.setHeader("Content-Disposition",
                "attachment; filename=archiveDossier"+numDoss+".zip");

        // List of files to be downloaded

            List<FileEntity> files = archiveRepository.getfiles(numDoss);

            ServletOutputStream out = response.getOutputStream();
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(out));
            for (FileEntity file : files) {

                zos.putNextEntry(new ZipEntry(file.getName()));

                // Get the file
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(String.valueOf(file));

                } catch (FileNotFoundException fnfe) {
                    // If the file does not exists, write an error entry instead of
                    // file
                    // contents
                    zos.write(("ERROR: Could not find file " + file.getName())
                            .getBytes());
                    zos.closeEntry();
                    continue;
                }

                BufferedInputStream fif = new BufferedInputStream(fis);

                // Write the contents of the file
                int data = 0;
                while ((data = fif.read()) != -1) {
                    zos.write(data);
                }
                fif.close();

                zos.closeEntry();
                System.out.println("Finished adding file " + file.getName());
            }

            zos.close();

    }


}
