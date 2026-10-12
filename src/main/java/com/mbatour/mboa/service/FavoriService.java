package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.FavoriDTO;
import com.mbatour.mboa.entite.Favori;

import java.util.List;

public interface FavoriService {
    public FavoriDTO ajouterFavori(Long utilisateurId, Long siteId) ;
        void supprimerFavori(
            Long userId,
            Long siteId
    );
    public List<FavoriDTO> getFavorisUtilisateur(Long utilisateurId) ;
}
