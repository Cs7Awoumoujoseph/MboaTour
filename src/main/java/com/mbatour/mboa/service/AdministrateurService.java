package com.mbatour.mboa.service;


import com.mbatour.mboa.DTO.AdminInfoDTO;
import com.mbatour.mboa.entite.Administrateur;
import com.mbatour.mboa.entite.StatutPublicationSite;

import java.util.List;

public interface AdministrateurService {
    Administrateur saveAdministrateur(Administrateur a);
    Administrateur updateAdministrateur(Administrateur a);
    void deleteAdministrateur(Administrateur a);
    void deleteAdministrateurById(Long id);
    Administrateur getAdministrateur(Long id);
    List<Administrateur> getAllAdministrateur();
    public  AdminInfoDTO getAdminInfo(Long administrateur_id);
}
