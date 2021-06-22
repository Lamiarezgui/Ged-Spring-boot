package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Archive {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String numDoss;
    private String serie;
    private LocalDateTime date;
    private String privilege;
    private int nbrePieces;
    private String theme_titre;
    private String nomPersonnel;
    private String matricule;
    private String cin;
    private String motdeCle;
    private int var;

    @OneToMany
    private List<FileEntity> files;
    @OneToMany
    private List<Versions> versions;

    public Archive() {

    }
}
