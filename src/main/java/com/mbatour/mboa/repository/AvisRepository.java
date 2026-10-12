package com.mbatour.mboa.repository;

import com.mbatour.mboa.DTO.AvisUserDTO;
import com.mbatour.mboa.entite.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "restAvi")
public interface AvisRepository extends JpaRepository<Avis, Long> {

    boolean existsByUtilisateur_IdAndSiteTouristique_Id(Long utilisateurId, Long siteId);

    @Query("""
SELECT new com.mbatour.mboa.DTO.AvisUserDTO(
       a.id,
       a.siteTouristique.id,
       a.note,
       a.commentaire,
       a.datePublication
)
FROM Avis a
WHERE a.utilisateur.id = :utilisateurId
ORDER BY a.datePublication DESC
""")
    List<AvisUserDTO> findByUtilisateurId(@Param("utilisateurId") Long utilisateurId);
}