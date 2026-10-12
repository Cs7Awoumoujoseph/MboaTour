package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.AdminInfoDTO;
import com.mbatour.mboa.entite.Administrateur;
import com.mbatour.mboa.entite.StatutPublicationSite;
import com.mbatour.mboa.exception.SiteNotFoundException;
import com.mbatour.mboa.repository.AdministrateurRepository;
import com.mbatour.mboa.repository.SiteTouristiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private SiteTouristiqueRepository siteTouristiqueRepository;

    @Override
    public Administrateur saveAdministrateur(Administrateur a) {
        return administrateurRepository.save(a);
    }

    @Override
    public Administrateur updateAdministrateur(Administrateur a) {
        return administrateurRepository.save(a);
    }

    @Override
    public void deleteAdministrateur(Administrateur a) {
         administrateurRepository.delete(a);
    }

    @Override
    public void deleteAdministrateurById(Long id) {
        administrateurRepository.deleteById(id);
    }

    @Override
    public Administrateur getAdministrateur(Long id) {
        return administrateurRepository.findById(id).get();
    }

    @Override
    public List<Administrateur> getAllAdministrateur() {
        return administrateurRepository.findAll();
    }



    @Override
    public  AdminInfoDTO getAdminInfo(Long administrateur_id){
        Administrateur a = administrateurRepository.findById(administrateur_id).orElseThrow(()-> new SiteNotFoundException("site not found"));
        AdminInfoDTO dto = new AdminInfoDTO();
        dto.setAdministrateur_id(a.getId());
        dto.setNbreTotalSite( siteTouristiqueRepository.countByAdministrateur_Id(administrateur_id));
        dto.setNbrSiteBrouillon(siteTouristiqueRepository.countByAdministrateur_IdAndStatutPublicationSite(administrateur_id,StatutPublicationSite.BROUILLON));
        dto.setNbrSitePublie(siteTouristiqueRepository.countByAdministrateur_IdAndStatutPublicationSite(administrateur_id,StatutPublicationSite.PUBLIE));

        return dto;
    }


}
