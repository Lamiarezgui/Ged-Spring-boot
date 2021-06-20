package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "versions")
public class Versions {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String contentType;
    private Long size;
    @Lob
    private byte[] data;
    private LocalDate date;
    private String privilege;

    @ManyToOne
    @JoinColumn(name = "file_entity_id")
    private FileEntity fileEntity;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Users user;
    @ManyToOne
    private Groupe groupe;
    @ManyToOne
    private Archive archive;


}


