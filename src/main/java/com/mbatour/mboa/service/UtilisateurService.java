package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur saveUtilisateur(Utilisateur u);
    Utilisateur updateUtilisateur(Utilisateur u);
    void deleteUtilisateur(Utilisateur u);
    void deleteUtilisateurById(Long id);
    Utilisateur getUtilisateur(Long id);
    List<Utilisateur> getAllUtilisateur();
    List<UserCommentDTO> pageableUtilisateurCommentSite(Long siteId);
    List<UserCommentDTO> getAllCommentsForSite(Long siteId); // ← ajouté
    Utilisateur register(Utilisateur user);
    Utilisateur login(String email, String password);
}