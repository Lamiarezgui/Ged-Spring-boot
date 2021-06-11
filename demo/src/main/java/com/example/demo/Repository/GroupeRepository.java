package com.example.demo.Repository;

import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    @Transactional
    @Modifying
    @Query(value = "Update Groupe g set g.name=:name where g.id=:id")
    void modifiername(long id, String name);

    @Query("select g.id,g.name from Groupe g inner join Users_groupes u on u.groupe.id=g.id where u.users.id=:id")
    List<Object> findGroupes(long id);

    @Query("select g.id,g.name from Groupe g  where g.controleur=:id")
    List<Object> getGroupesControleur(long id);

    @Query("select g.id,g.name,u.firstName,u.lastName from Groupe g, Users u where g.controleur=u.id ")
    List<Object> findAllG();
}
