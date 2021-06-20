package com.example.demo.Services;

import com.example.demo.Model.*;
import com.example.demo.Repository.ArchiveRepository;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private final FichierRepository fileRepository;


    @Autowired
    public FileService(FichierRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void save(MultipartFile file, Users user,String priv) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setDate(LocalDate.now());
        fileEntity.setPrivilege(priv);
        fileEntity.setUser(user);
        fileRepository.save(fileEntity);
    }
    public void saveGr(MultipartFile file, Users user,Groupe groupe,String priv) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setDate(LocalDate.now());
        fileEntity.setPrivilege(priv);
        fileEntity.setUser(user);
        fileEntity.setGroupe(groupe);
        fileRepository.save(fileEntity);
    }

    public Optional<FileEntity> getFile(String id) {
        return fileRepository.findById(id);
    }

    public List<Object> getAllFiles(long id) {
        return fileRepository.getAllFilesP(id);
    }
    public List<Object> getAllFilesGr(long id) {
        return fileRepository.getAllFilesG(id);
    }
    public List<Object> getAllFilesPublic() {
        return fileRepository.getAllFilesPublic();
    }
    public List<Object> getVersionsFile(String id) {
        return fileRepository.getVersionFile(id);
    }

    public List<Object> getAllFiles() {
       return fileRepository.getAllFiles();
    }
ArchiveRepository archiveRepository;

    public void saveArchive(MultipartFile file,Users user,Archive archive,String privilege) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        fileEntity.setDate(LocalDate.now());
        fileEntity.setPrivilege(privilege);
        fileEntity.setUser(user);

        fileEntity.setArchive(archive);
        fileRepository.save(fileEntity);
    }

   /* public boolean delete(String id) {
        try {
            fileRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }}*/

}