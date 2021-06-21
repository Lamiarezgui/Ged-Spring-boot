package com.example.demo.Repository;

import com.example.demo.Model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveRepository extends JpaRepository<Archive, String> {
    @Query(value = "select count(f) as count,to_char(f.date,'MONTH') AS MONTH from archive f group by MONTH order by MONTH", nativeQuery = true)
    List<Object> countDossMonth();

    @Query(value = "select count(f)as count,EXTRACT(YEAR from f.date) AS year from archive f  group by year order by year", nativeQuery = true)
    List<Object> countDossYear();

    @Query(value = "select count(f)as count,EXTRACT(DAY from f.date) AS day from archive f  group by day order by day", nativeQuery = true)
    List<Object> countDossDay();

    @Query("SELECT a.numDoss,a.theme_titre,a.date,a.cin,a.matricule,a.motdeCle,a.nbrePieces,a.nomPersonnel,a.serie from Archive a")
    List<Object> getAll();

    @Query("select f.id,f.name,f.date,f.size,f.user.lastName,f.user.firstName from Archive a inner join FileEntity f on f.archive.id=a.id where a.numDoss=:numDoss")
    List<Object> getContenu(String numDoss);

    @Query("select f.id from Archive a inner join FileEntity f on f.archive.id=a.id where a.numDoss=:numDoss")
    List<String> getIdfiles(String numDoss);

    @Query("select a.id from Archive a where a.numDoss=:numDoss")
    String findByNumDoss(String numDoss);
}
