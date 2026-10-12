package com.mbatour.mboa.repository;

import com.mbatour.mboa.entite.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restAdm")
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

}