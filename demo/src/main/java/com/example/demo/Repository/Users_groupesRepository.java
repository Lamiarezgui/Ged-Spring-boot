package com.example.demo.Repository;

import com.example.demo.Model.Users_groupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Users_groupesRepository extends JpaRepository<Users_groupes, Long> {
    @Query("delete from Users_groupes f where f.users.id=:id")
    @Modifying
    @Transactional
    void update(long id);
}
