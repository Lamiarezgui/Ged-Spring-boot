package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.Services.FileService;
import com.example.demo.Services.VersionsService;
import lombok.AllArgsConstructor;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.ws.Response;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@AllArgsConstructor
@RequestMapping("files")
public class FilesController {
    @Autowired
    private final FileService fileService;
    @Autowired
    private final VersionsService versionsService;

    UsersRepository usersRepository;

    //ajouter un fichier dans mes fichiers
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("/private")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user) {
        try {
            fileService.save(file, user, "PRIVEE");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    //ajouter un fichier pour le public
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("/public")
    public ResponseEntity<String> uploadPublic(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user) {
        try {
            fileService.save(file, user, "PUBLIC");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    //ajouter un fichier dans un groupe
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("/groupe")
    public ResponseEntity<String> uploadGroupe(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user, @RequestParam("groupe") Groupe groupe) {
        try {
            fileService.saveGr(file, user, groupe, "GROUPE");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    FichierRepository fichierRepository;

    // afficher la liste des fichiers pour un utilisateur
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/ListFiles/{id}")
    public List<Object> list(@PathVariable("id") long id) {
        return fileService.getAllFiles(id);
    }

    // afficher la liste des fichiers pour un groupe
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("ListFilesGroupe/{groupe_id}")
    public List<Object> FilesGroupe(@PathVariable("groupe_id") long id) {
        return fileService.getAllFilesGr(id);
    }

    // afficher la liste des fichiers public
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/public")
    public List<Object> listFilesPublic() {
        return fileService.getAllFilesPublic();
    }

    //afficher les versions de fichier
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/ListVersions/{id}")
    public List<Object> getVersionsFile(@PathVariable String id) {
        return fileService.getVersionsFile(id);
    }

    //afficher tous les fichiers avec les fichiers des utilisateurs supprimes
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping
    public List<Object> listFiles() {
        return fileService.getAllFiles();
    }

    // countFiles
    @GetMapping("/countfilesPerMonth")
    public List<Object> countFiles() {
        System.out.println(fichierRepository.countFilesMonth());
        return fichierRepository.countFilesMonth();

    }
    // countFiles

    @GetMapping("/countfilesPerYear")
    public List<Object> countFilesperYear() {
        System.out.println(fichierRepository.countFilesYear());
        return fichierRepository.countFilesYear();

    }

    @GetMapping("/countfilesPerDay")
    public List<Object> countFilesperDay() {
        System.out.println(fichierRepository.countFilesDay());
        return fichierRepository.countFilesDay();

    }


    //exporter le fichier
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {

        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        fileService.addVar(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }

    @GetMapping("/countexported")
    public List<Object> countvar() {
        return fichierRepository.countFilesExportedbyName();
    }

    //visualiser un fichier
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @GetMapping("viewFile/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String id) {
        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }


        FileEntity fileEntity = fileEntityOptional.get();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }

  /*  @DeleteMapping("/{id}")
    public String deleteFile(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File n'existe pas :: " + id));

        fileRepository.delete(file);

        return "file deleted";
    }*/

    //ajouter une version au fichier privee
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("/{id}")
    public ResponseEntity<String> updateV(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user, @PathVariable String id) {
        try {
            versionsService.saved(file, user, id, "PRIVEE");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    //ajouter une version au fichier public
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("Public/{id}")
    public ResponseEntity<String> updateP(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user, @PathVariable String id) {
        try {
            versionsService.saved(file, user, id, "PUBLIC");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    //ajouter version dans un groupe
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PostMapping("AddVersGroup/{id}")
    public ResponseEntity<String> update(@RequestParam("file") MultipartFile file, @RequestParam("user") Users user, @RequestParam("groupe") Groupe groupe, @PathVariable String id) {
        try {
            versionsService.save(file, user, groupe, id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }


    @GetMapping("ocr")
    public void Ocr() throws IOException {


        try {
            Process proc = Runtime.getRuntime().exec(new String[]{"C:/Users/rezgu/PycharmProjects/pythonProject/dist/main.exe"});
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String s = null;
            // read the output
            while ((s = stdInput.readLine()) != null) {

                System.out.println(s);
            }
            // read any errors

            while ((s = stdError.readLine()) != null) {

                System.out.println(s);
            }

            System.exit(0);

        } catch (IOException e) {

            System.out.println("exception occured");

            e.printStackTrace();

        }
    }


}