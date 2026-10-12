package com.mbatour.mboa.repository;

import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.Utilisateur;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "restUti")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    boolean existsByEmail(String email);
    Optional<Utilisateur> findByEmailAndMotDePasse(String email, String password);

    @Query(
            value = """
SELECT new com.mbatour.mboa.DTO.UserCommentDTO(
       a.id,
       u.id,
       u.nom,
       a.commentaire,
       a.note,
       a.datePublication
)
FROM Avis a
JOIN a.utilisateur u
WHERE a.siteTouristique.id = :siteId
ORDER BY a.datePublication DESC
"""
    )
    List<UserCommentDTO> findTop3Comment(@Param("siteId") Long siteId, Pageable pageable);

    @Query(
            value = """
SELECT new com.mbatour.mboa.DTO.UserCommentDTO(
       a.id,
       u.id,
       u.nom,
       a.commentaire,
       a.note,
       a.datePublication
)
FROM Avis a
JOIN a.utilisateur u
WHERE a.siteTouristique.id = :siteId
ORDER BY a.datePublication DESC
"""
    )
    List<UserCommentDTO> findAllCommentsBySite(@Param("siteId") Long siteId);
}