package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "restImg")
public interface ImageRepository extends CrudRepository<Image, Long> {

    List<Image> findBySiteTouristiqueIdOrderByIdImageAsc(Long siteId); // NOUVEAU
    List<Image> findBySiteTouristiqueId(Long siteId);
    long countBySiteTouristiqueId(Long siteId);
}
