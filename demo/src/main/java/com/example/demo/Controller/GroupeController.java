package com.example.demo.Controller;

import com.example.demo.Model.Groupe;
import com.example.demo.Model.Users;
import com.example.demo.Repository.GroupeRepository;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.ResourceNotFoundException;
import com.example.demo.Services.GroupeService;
import com.example.demo.Services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@AllArgsConstructor
public class GroupeController {
    private final GroupeService groupeService;

    //ajouter groupe
    @PostMapping("/groupe")
    public ResponseEntity<String> ajoutGroup(@RequestBody Groupe groupe) {
        groupeService.ajouterGroupe(groupe.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //modifier nom du groupe
    @PutMapping("/groupe/{id}")
    public void updateNameGroupe(@PathVariable("id") long id, @RequestBody Groupe g) {
        groupeService.modifierName(g.getName(), id);
    }

    UsersService usersService;
    GroupeRepository groupeRepository;

   //supprimer un groupe
    @DeleteMapping("/groupe/{id}")
    public String deleteGroupe(@PathVariable(value = "id") long id)
            throws ResourceNotFoundException {
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("groupe n'existe pas :: " + id));
        groupeService.updateG(id);
        groupeService.delete(groupe);

        return "groupe supprime";
    }

    //afficher la liste des groupes
    @GetMapping("/groupes")
    public List<Groupe> getAllGroupes() {

        return groupeService.getAllGroupes();

    }
    UsersRepository usersRepository;

    //afficher les groupes d'un user
    @GetMapping("/userGroupes/{id}")
    public List<Object> getGroupes(@PathVariable(value = "id") long id) {
        return groupeService.getGroupes(id);
    }

}
