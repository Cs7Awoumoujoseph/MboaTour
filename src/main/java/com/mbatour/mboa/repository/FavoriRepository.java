package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.Favori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "restFav")
public interface FavoriRepository extends JpaRepository<Favori, Long> {
    boolean existsByUtilisateurIdAndSiteTouristiqueId(
            Long utilisateurId,
            Long siteId
    );


    void deleteByUtilisateurIdAndSiteTouristiqueId(
            Long utilisateurId,
            Long siteId
    );


    List<Favori> findByUtilisateurId(Long utilisateurId);
}
