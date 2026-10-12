package com.mbatour.mboa.repository;

import com.mbatour.mboa.DTO.CardInfosDetailsDTO;
import com.mbatour.mboa.DTO.DestinationDTO;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SiteTouristiqueRepositoryCustom {
    CardInfosDetailsDTO findDetailSite(Long id);
    StatistiqueDTO getStatistiqueAvis(Long id);
    Page<DestinationDTO> findAllDestinations(
            String motCle,
            String categorie,
            String region,
            TriSite tri ,
            boolean asc ,
            Pageable pageable
    );
}
