package com.example.demo.Repository;

import com.example.demo.Model.Versions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface VersionRepository extends JpaRepository<Versions, String> {


    @Query(value = "Update Versions v set v.archive.id=:archive_id where v.fileEntity.id=:id")
    @Modifying
    void ajouterVersionsArchive(String id, String archive_id);

    @Query("update Versions f set f.user.id=NULL where f.user.id=:id")
    @Modifying
    void updat(long id);

    @Query("update Versions f set f.groupe.id=NULL where f.groupe.id=:id")
    @Modifying
    void updatG(long id);


}
