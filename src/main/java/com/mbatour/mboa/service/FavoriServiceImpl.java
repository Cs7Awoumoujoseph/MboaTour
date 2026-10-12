package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.FavoriDTO;
import com.mbatour.mboa.entite.Favori;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.entite.Utilisateur;
import com.mbatour.mboa.exception.ElementExistException;
import com.mbatour.mboa.repository.FavoriRepository;
import com.mbatour.mboa.repository.SiteTouristiqueRepository;
import com.mbatour.mboa.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriServiceImpl implements FavoriService{

    @Autowired
    FavoriRepository favoriRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    SiteTouristiqueRepository siteRepository;

    @Override
    public FavoriDTO ajouterFavori(Long utilisateurId, Long siteId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        SiteTouristique site = siteRepository.findById(siteId)
                .orElseThrow(() -> new RuntimeException("Site introuvable"));

        Favori favori = new Favori();
        favori.setUtilisateur(utilisateur);
        favori.setSiteTouristique(site);
        favori.setDateAjout(new Date());

        Favori saved = favoriRepository.save(favori);
        return toDTO(saved);
    }

    @Override
    @Transactional
    public void supprimerFavori(Long utilisateurId, Long siteId) {
        favoriRepository.deleteByUtilisateurIdAndSiteTouristiqueId(utilisateurId, siteId);
    }

    @Override
    public List<FavoriDTO> getFavorisUtilisateur(Long utilisateurId) {
        return favoriRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private FavoriDTO toDTO(Favori favori) {
        return new FavoriDTO(
                favori.getId(),
                favori.getUtilisateur().getId(),
                favori.getSiteTouristique().getId(),
                favori.getDateAjout()
        );
    }
}
