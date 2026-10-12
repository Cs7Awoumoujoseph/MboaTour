package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.entite.StatutPublicationSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(path = "siteT")
public interface SiteTouristiqueRepository extends JpaRepository<SiteTouristique, Long> , SiteTouristiqueRepositoryCustom {
    long countByAdministrateur_IdAndStatutPublicationSite(
            Long administrateurId,
            StatutPublicationSite statutPublicationSite
    );
    Page<SiteTouristique> findByAdministrateur_Id(Long administrateurId, Pageable pageable);
    long countByAdministrateur_Id(Long administrateurId);
}