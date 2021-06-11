package com.example.demo.Repository;

import com.example.demo.Model.AppUserRole;
import com.example.demo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u where u.email=?1")
    Users findByEmail(String email);


    @Modifying
    @Query("update Users a set a.firstName=:firstName, a.lastName=:lastName, a.email=:email,a.numtel=:numtel,a.password=:password where a.id=:id")
    void UpdateUsers(String firstName, String lastName, String email, String numtel, String password, long id);

    @Query("delete from Users_groupes u where u.groupe.id=:id")
    @Modifying
    void updatG(long id);

    @Query("Select u from Users u where u.appUserRole='SUPERVISEUR'")
    Optional<Users> getSuperviseurs();

    @Query("Select u from Users u where u.appUserRole='ADMINISTRATEUR'")
    Optional<Users> getAdmins();

    @Query("Select u from Users u where u.appUserRole='CONTROLEUR' or u.appUserRole='ADMINISTRATEUR'")
    List<Users> getControleurs();


    @Modifying
    @Query("update Users u set u.password=:password where u.id=:id")
    void updatePass(String password, long id);

    Users findByResetPasswordToken(String token);


    @Modifying
    @Query("update Users a set a.image=:image where a.id=:id")
    void updateImg(String image, long id);


    @Modifying
    @Query("update Users a set a.firstName=:firstName, a.lastName=:lastName, a.email=:email,a.numtel=:numtel,a.appUserRole=:appUserRole where a.id=:id")
    void UpdateUsersIng(String firstName, String lastName, String email, String numtel, AppUserRole appUserRole, long id);

    @Query("select u from Users u inner join Users_groupes g on g.users.id=u.id where g.groupe.id=:id")
    List<Users> findUsers(long id);
}
