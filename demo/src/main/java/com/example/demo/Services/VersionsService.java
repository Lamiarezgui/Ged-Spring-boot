package com.example.demo.Services;

import com.example.demo.Model.FileEntity;
import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users;
import com.example.demo.Model.Versions;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VersionsService {
    @Autowired
    private final VersionRepository vers;
    @Autowired
    private final FichierRepository fileRepository;

    @Autowired
    public VersionsService(VersionRepository vers, FichierRepository fileRepository) {
        this.vers = vers;
        this.fileRepository = fileRepository;
    }
    public void save(MultipartFile file, Users user,Groupe groupe, String id) throws IOException {
        FileEntity f = fileRepository.getOne(id);

        Versions version = new Versions();
        version.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        version.setContentType(file.getContentType());
        version.setData(file.getBytes());
        version.setSize(file.getSize());
        version.setDate(LocalDate.now());
        version.setPrivilege("GROUPE");
        version.setFileEntity(f);
        version.setUser(user);
        version.setGroupe(groupe);
        vers.save(version);
    }
    public void saved(MultipartFile file, Users user, String id,String priv) throws IOException {
        FileEntity f = fileRepository.getOne(id);

        Versions version = new Versions();
        version.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        version.setContentType(file.getContentType());
        version.setData(file.getBytes());
        version.setSize(file.getSize());
        version.setDate(LocalDate.now());
        version.setFileEntity(f);
        version.setPrivilege(priv);
        version.setUser(user);
        vers.save(version);
    }

    public Optional<Versions> getVersions(String id) {
        return vers.findById(id);
    }

    public List<Versions> getAllVersions() {
        return vers.findAll();
    }


}
