package com.example.demo.Repository;

import com.example.demo.Model.Users_groupes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users_groupesRepository extends JpaRepository<Users_groupes, Long> {
}
