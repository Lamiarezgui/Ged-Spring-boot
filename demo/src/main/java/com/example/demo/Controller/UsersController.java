package com.example.demo.Controller;

import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.GroupeRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.Repository.VersionRepository;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.Services.UsersService;
import com.example.demo.mail.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    //modifier les donnees personnels du user
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUsers(@PathVariable("id") long id, @RequestBody Users user) {
        try {
            usersService.modifuser(user, id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("utilisateur modifie"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("utilisateur non modifie"));
        }
    }

    //modifier image
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR','ROLE_ADMINISTRATEUR')")
    @PutMapping("/userImage/{id}")
    public void updateImg(@RequestParam("image") MultipartFile file, @PathVariable("id") long id) {
        usersService.updateImg(file, id);
    }

    //modifier user de la part de l"ingenieur
    @PreAuthorize("hasAnyRole('ROLE_INGENIEUR')")
    @PutMapping("/IngUpdate/{id}")
    public ResponseEntity<String> ModifUserIng(@PathVariable("id") long id, @RequestBody Users user) {
        try {
            usersService.modifuserIng(user.getFirstName(), user.getLastName(), user.getEmail(), user.getNumtel(), user.getAppUserRole(), id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("utilisateur modifie"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("utilisateur non modifie"));
        }
    }

    //ajouter utilisateur dans le groupe
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR')")
    @PostMapping("/groupe/user/{id_g}")
    public ResponseEntity<String> ajouterUser(@PathVariable("id_g") long id_g, @RequestBody Users user) {

        try {
            usersService.ajoutuser(id_g, user);


            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("utilisateur est ajouté au groupe"));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("utilisateur n'est pas ajouté au groupe"));
        }
    }

    private String buildEmail1(String firstName, String lastName, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "      <tbody><tr>\n" +

                "    <tr>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + firstName + " " + lastName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> vous avez été ajoutés à un nouveau groupe </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "<p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + " \">Voir votre nouveau groupe </a> </p><p>Have a good Day</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    //afficher la liste de tous les utilisateurs
    @PreAuthorize("hasAnyRole('ROLE_INGENIEUR')")
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    //afficher la liste des administrateurs
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("/users/administrateurs")
    public Optional<Users> getAdmins() {
        return usersService.getAdmins();
    }



    //afficher la liste des controleurs
    @PreAuthorize("hasAnyRole('ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("/users/all")
    public List<Users> getControleurs() {
        return usersService.getControleurs();
    }


    //afficher les donnees d'un utilisateur
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("/user/{id}")
    public Optional<Users> getUsers(@PathVariable(value = "id") long id) {
        Optional<Users> u = usersService.getUsers(id);
        return u;
    }

    UsersRepository usersRepository;

    FichierRepository fichierRepository;
    VersionRepository versionRepository;

    //supprimer un utilisateur
    @PreAuthorize("hasAnyRole('ROLE_INGENIEUR')")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Users u = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("utilisateur n'existe pas :: " + id));
        usersService.update(id);
        usersService.del(u);

        return "utilisateur supprime";
    }

    GroupeRepository groupeRepository;

    //afficher les users d'un groupe
    @PreAuthorize("hasAnyRole('ROLE_CONTROLEUR','ROLE_SUPERVISEUR','ROLE_INGENIEUR')")
    @GetMapping("/groupeUsers/{id}")
    public List<Users> getGroupes(@PathVariable(value = "id") long id) {
        return usersService.getUsersg(id);
    }

    //modifier password
    @PutMapping("user/modifpass/{id}")
    public ResponseEntity<String> modifPass(@PathVariable("id") long id, @RequestBody Users user) {
        try {
            usersService.modifPass(user.getPassword(), id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("mot de passe modifie"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("mot de passe non modifie"));
        }
    }


    private final EmailSender emailSender;

    //reset password
    @PostMapping(value = "/forgot-password")
    public String forgotPassword(@Valid @RequestBody Users user) {
        Users users = usersRepository.findByEmail(user.getEmail());
        String response = usersService.forgotPassword(user.getEmail());

        if (!response.startsWith("Invalid")) {
            String link = "http://localhost:3000/reset?token=" + response;


            emailSender.sendu(
                    users.getEmail(),
                    buildEmail(users.getEmail(), link));
        }
        return response;
    }


    @PutMapping("/reset-password")
    public String resetPassword(@RequestBody Users user, @RequestParam("token") String token) {

        return usersService.resetPassword(token, user.getPassword());
    }

    private String buildEmail(String email, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "      <tbody><tr>\n" +

                "    <tr>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + email + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> vous avez demander de réinitialiser votre mot de passe </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "<p style=\\\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + " \">cliquer ici </a> </p><p>Merci </p><p>Have a good Day</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
