package com.example.demo.Services;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UsersService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return Optional
                .ofNullable(usersRepository.findByEmail(email))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Users users) {
        boolean userExists = Optional
                .ofNullable(usersRepository
                        .findByEmail(users.getEmail()))
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("Email existe déjà");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(users.getPassword());

        users.setPassword(encodedPassword);

        usersRepository.save(users);
        return "Compte ajoute";
    }

    public void updateImg(MultipartFile file, long id) {
        String img;
        try {
            img = Base64.getEncoder().encodeToString(file.getBytes());
            usersRepository.updateImg(img, id);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    FichierRepository fichierRepository;
    VersionRepository versionRepository;

    public void update(long id) {
        fichierRepository.updat(id);
        versionRepository.updat(id);
    }

    public void del(Users user) {

        usersRepository.delete(user);
    }

    public void modifuser(Users user, long id) {
        if (user.getPassword()!=null){
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());
        usersRepository.UpdateUsers(user.getFirstName(),user.getLastName(),user.getEmail(),user.getNumtel(),encodedPassword, id);}
        else {
           Users u= usersRepository.getOne(id);
            usersRepository.UpdateUsers(user.getFirstName(),user.getLastName(),user.getEmail(),user.getNumtel(), u.getPassword(), id);
    }}

    GroupeRepository groupeRepository;
    Users_groupesRepository users_groupesRepository;

    public void ajoutuser(long id_g, Users u) {
        Users_groupes users_groupes = new Users_groupes();
        users_groupes.setUsers(u);
        Groupe groupe = groupeRepository.getOne(id_g);
        users_groupes.setGroupe(groupe);
        users_groupesRepository.save(users_groupes);
    }

    public List<Users> getUsersg(long id) {
        return usersRepository.findUsers(id);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUsers(long id) {
        return usersRepository.findById(id);
    }

    public Optional<Users> getSuperviseurs() {
        return usersRepository.getSuperviseurs();
    }

    public Optional<Users> getAdmins() {
        return usersRepository.getAdmins();
    }

    public List<Users> getControleurs() {
        return usersRepository.getControleurs();
    }

    public void modifPass(String password, long id) {
        usersRepository.updatePass(bCryptPasswordEncoder
                .encode(password), id);
    }

    public String forgotPassword(String email) {

        Optional<Users> userOptional = Optional
                .ofNullable(usersRepository.findByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        Users user = userOptional.get();
        user.setResetPasswordToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = usersRepository.save(user);

        return user.getResetPasswordToken();
    }

    public String resetPassword(String token, String password) {

        Optional<Users> userOptional = Optional
                .ofNullable(usersRepository.findByResetPasswordToken(token));

        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";

        }

        Users user = userOptional.get();

        user.setPassword(bCryptPasswordEncoder
                .encode(password));
        user.setResetPasswordToken(null);
        user.setTokenCreationDate(null);

        usersRepository.save(user);

        return "Your password successfully updated.";
    }

    /**
     * Generate unique token. You may add multiple parameters to create a strong
     * token.
     *
     * @return unique token
     */
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

    public void modifuserIng(String firstName, String lastName, String email, String numtel, AppUserRole appUserRole, long id) {
        usersRepository.UpdateUsersIng(firstName, lastName, email, numtel, appUserRole, id);
    }
}
