package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restCat")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {}