package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.AvisCreateDTO;
import com.mbatour.mboa.DTO.AvisUpdateDTO;
import com.mbatour.mboa.DTO.AvisUserDTO;
import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.Avis;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.entite.Utilisateur;
import com.mbatour.mboa.repository.AvisRepository;
import com.mbatour.mboa.repository.SiteTouristiqueRepository;
import com.mbatour.mboa.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AvisServiceImpl implements AvisService {
    @Autowired
    private AvisRepository avisRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private SiteTouristiqueRepository siteTouristiqueRepository;

    @Override
    public Avis saveAvis(Avis a) {
        return avisRepository.save(a);
    }

    @Override
    public Avis updateAvis(Avis a) {
        return avisRepository.save(a);
    }

    @Override
    public void deleteAvis(Avis a) {
        avisRepository.delete(a);
    }

    @Override
    public void deleteAvisById(Long id) {
        avisRepository.deleteById(id);
    }

    @Override
    public Avis getAvis(Long id) {
        return avisRepository.findById(id).get();
    }

    @Override
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    @Override
    @Transactional
    public UserCommentDTO ajouterAvis(AvisCreateDTO dto) {
        if (avisRepository.existsByUtilisateur_IdAndSiteTouristique_Id(dto.getUtilisateurId(), dto.getSiteId())) {
            throw new RuntimeException("Vous avez déjà publié un avis pour ce site.");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        SiteTouristique site = siteTouristiqueRepository.findById(dto.getSiteId())
                .orElseThrow(() -> new RuntimeException("Site introuvable"));

        Avis avis = new Avis();
        avis.setUtilisateur(utilisateur);
        avis.setSiteTouristique(site);
        avis.setNote(dto.getNote());
        avis.setCommentaire(dto.getCommentaire());
        avis.setDatePublication(new Date());

        Avis saved = avisRepository.save(avis);

        return new UserCommentDTO(
                saved.getId(),
                utilisateur.getId(),
                utilisateur.getNom(),
                saved.getCommentaire(),
                saved.getNote(),
                saved.getDatePublication()
        );
    }

    @Override
    @Transactional
    public UserCommentDTO modifierAvis(Long avisId, AvisUpdateDTO dto) {
        Avis avis = avisRepository.findById(avisId)
                .orElseThrow(() -> new RuntimeException("Avis introuvable"));

        avis.setNote(dto.getNote());
        avis.setCommentaire(dto.getCommentaire());

        Avis saved = avisRepository.save(avis);

        return new UserCommentDTO(
                saved.getId(),
                saved.getUtilisateur().getId(),
                saved.getUtilisateur().getNom(),
                saved.getCommentaire(),
                saved.getNote(),
                saved.getDatePublication()
        );
    }

    @Override
    public List<AvisUserDTO> getAvisByUser(Long utilisateurId) {
        return avisRepository.findByUtilisateurId(utilisateurId);
    }
}