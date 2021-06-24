package com.example.demo.Repository;

import com.example.demo.Model.Users_groupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Users_groupesRepository extends JpaRepository<Users_groupes, Long> {
    @Query("update Users_groupes f set  f.users.id=NULL where f.users.id=:id")
    @Modifying
    void update(long id);
}
