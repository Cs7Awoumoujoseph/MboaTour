package com.mbatour.mboa.restController;


import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.Utilisateur;
import com.mbatour.mboa.service.UtilisateurService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UtilisateurRestController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> findAll() {
        return utilisateurService.getAllUtilisateur();
    }

    @GetMapping("/{id}")
    public Utilisateur findOne(@PathVariable("id") Long id) {
        return utilisateurService.getUtilisateur(id);
    }

    @GetMapping("site/{id}/comment")
    public List<UserCommentDTO> findBySiteId(@PathVariable("id") Long siteId) {
        return utilisateurService.pageableUtilisateurCommentSite(siteId);
    }


    @GetMapping("site/{id}/comments")
    public List<UserCommentDTO> findAllBySiteId(@PathVariable("id") Long siteId) {
        return utilisateurService.getAllCommentsForSite(siteId);
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestBody Map<String, String> credentials) {
        Utilisateur user = utilisateurService.login(credentials.get("email"), credentials.get("password"));
        return ResponseEntity.ok(user);
    }
}
