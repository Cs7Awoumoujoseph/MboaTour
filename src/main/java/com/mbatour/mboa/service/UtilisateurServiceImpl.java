package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.UserType;
import com.mbatour.mboa.entite.Utilisateur;
import com.mbatour.mboa.exception.EmailExistException;
import com.mbatour.mboa.exception.InvalidCredentialsException;
import com.mbatour.mboa.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur saveUtilisateur(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public void deleteUtilisateur(Utilisateur u) {
        utilisateurRepository.delete(u);
    }

    @Override
    public void deleteUtilisateurById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public List<UserCommentDTO> pageableUtilisateurCommentSite(Long siteId) {
        Pageable pageable1 = PageRequest.of(0, 3);
        return utilisateurRepository.findTop3Comment(siteId, pageable1);
    }

    @Override
    public List<UserCommentDTO> getAllCommentsForSite(Long siteId) {
        return utilisateurRepository.findAllCommentsBySite(siteId);
    }

    @Override
    public Utilisateur register(Utilisateur user) {
        if (utilisateurRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistException("Cet email est déjà utilisé.");
        }
        if (user.getType() == null) {
            user.setType(UserType.VISITEUR);
        }
        return utilisateurRepository.save(user);
    }

    @Override
    public Utilisateur login(String email, String password) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, password)
                .orElseThrow(() -> new InvalidCredentialsException("Email ou mot de passe incorrect."));
    }
}