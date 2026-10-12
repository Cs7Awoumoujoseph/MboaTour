package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restReg")
public interface RegionRepository extends JpaRepository<Region, Long> {}