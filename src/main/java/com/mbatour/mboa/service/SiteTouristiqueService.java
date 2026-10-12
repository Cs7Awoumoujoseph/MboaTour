package com.mbatour.mboa.service;


import com.mbatour.mboa.DTO.CardInfosDetailsDTO;
import com.mbatour.mboa.DTO.DestinationDTO;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.repository.TriSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface SiteTouristiqueService {
     SiteTouristique creerEtPublierSiteTouristique(SiteTouristique s, List<MultipartFile> imgs) throws IOException ;
    SiteTouristique creerEtBroullon(SiteTouristique s,List<MultipartFile> imgs) throws IOException ;
     SiteTouristique modifEtBroullon(Long id);
     SiteTouristique modifEtPublier(Long id);
    void deleteSiteTouristique(SiteTouristique s);
    void deleteRegionById(Long id);
    SiteTouristique getSiteTouristique(Long id);
    Page<SiteTouristique> getAllSiteTouristique(Pageable pageable);
    SiteTouristique modifierInfos(Long id, SiteTouristique updates);
    Page<SiteTouristique> getSitesByAdministrateur(Long administrateurId, Pageable pageable);
     Page<DestinationDTO> rechercherDTO(
            String motCle,
            String categorie,
            String region,
            TriSite tri,
            boolean asc,
            Pageable pageable
    );

    CardInfosDetailsDTO getSiteDetail(Long id);
    StatistiqueDTO getSiteStatistiqueAvis(Long id);
    SiteTouristique enregistrerImgEtResteInfo(
            SiteTouristique site,
            List<MultipartFile> imgs
    ) throws IOException ;

}
