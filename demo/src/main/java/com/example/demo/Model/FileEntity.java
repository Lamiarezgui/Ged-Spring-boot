package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String contentType;

    private Long size;

    @Lob
    private byte[] data;
    private LocalDateTime date;
    private String privilege;
    @OneToMany
    private List<Versions> versions;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Users user;
    @ManyToOne
    private Archive archive;
    @ManyToOne
    private Groupe groupe;


    public FileEntity() {

    }
}
