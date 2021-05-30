package com.example.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Users implements UserDetails, Serializable {

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String numtel;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private String departement;
    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private List<Users_groupes> users_groupes;

    @OneToMany
    private List<FileEntity> fileEntity;
    @OneToMany
    private List<Versions> versions;
    @Lob
    private String image;


    private String resetPasswordToken;
    private LocalDateTime tokenCreationDate;

    public Users() {

    }

    public Users(String firstName, String lastName, String email, String numtel, String password, AppUserRole appUserRole, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
        this.appUserRole = appUserRole;
        this.image = image;
    }

    String ROLE_PREFIX = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + appUserRole));

        return list;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
