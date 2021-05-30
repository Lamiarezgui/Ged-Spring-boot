package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@Entity
public class Groupe {
    @Id
    @SequenceGenerator(
            name = "Groupe_sequence",
            sequenceName = "Groupe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Groupe_sequence"
    )
    private Long id;
    private String name;
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Users_groupes> users_groupes;
    @OneToMany
    private List<FileEntity> files;
    @OneToMany
    List<Versions> versions;

    public Groupe() {

    }

}

