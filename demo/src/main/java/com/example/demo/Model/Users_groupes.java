package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users_groupes {
    @Id
    @SequenceGenerator(
            name = "usersgroupe_sequence",
            sequenceName = "usersgroupe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "usersgroupe_sequence"
    )
    private long id;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Groupe groupe;
}
