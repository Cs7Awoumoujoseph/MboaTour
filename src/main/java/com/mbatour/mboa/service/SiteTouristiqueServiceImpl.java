package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.CardInfosDetailsDTO;
import com.mbatour.mboa.DTO.DestinationDTO;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import com.mbatour.mboa.entite.*;
import com.mbatour.mboa.exception.IllegalArgException;
import com.mbatour.mboa.exception.MaxPageImageToSiteException;
import com.mbatour.mboa.exception.SiteNotFoundException;
import com.mbatour.mboa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class SiteTouristiqueServiceImpl implements SiteTouristiqueService {
    @Autowired
    private SiteTouristiqueRepository siteTouristiqueRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CategorieRepository categorieRepository;

    @Transactional
    @Override
    public SiteTouristique creerEtPublierSiteTouristique(SiteTouristique s, List<MultipartFile> imgs) throws IOException {
        s.setStatutPublicationSite(StatutPublicationSite.PUBLIE);
        SiteTouristique siteSauvegarde = siteTouristiqueRepository.save(s);
        return enregistrerImgEtResteInfo(siteSauvegarde,imgs);
    }

    @Override
    @Transactional
    public SiteTouristique creerEtBroullon(SiteTouristique s,List<MultipartFile> imgs) throws IOException {
        s.setStatutPublicationSite(StatutPublicationSite.BROUILLON);
        SiteTouristique siteSauvegarde = siteTouristiqueRepository.save(s);
        return  enregistrerImgEtResteInfo(siteSauvegarde,imgs);

    }

    @Override
    @Transactional
    public SiteTouristique modifierInfos(Long id, SiteTouristique updates) {
        SiteTouristique s = siteTouristiqueRepository.findById(id)
                .orElseThrow(() -> new SiteNotFoundException("site introuvable"));
        s.setNom(updates.getNom());
        s.setDescription(updates.getDescription());
        s.setPhraseAccroche(updates.getPhraseAccroche());
        s.setLocalisation(updates.getLocalisation());
        s.setHoraires(updates.getHoraires());
        s.setTarif(updates.getTarif());
        s.setIdealPour(updates.getIdealPour());
        s.setMeilleurePeriode(updates.getMeilleurePeriode());
        if (updates.getStatutPublicationSite() != null) {
            s.setStatutPublicationSite(updates.getStatutPublicationSite()); // NOUVEAU
        }
        if (updates.getRegion() != null) {
            s.setRegion(regionRepository.findById(updates.getRegion().getId())
                    .orElseThrow(() -> new SiteNotFoundException("région introuvable")));
        }
        if (updates.getCategorie() != null) {
            s.setCategorie(categorieRepository.findById(updates.getCategorie().getId())
                    .orElseThrow(() -> new SiteNotFoundException("catégorie introuvable")));
        }
        return siteTouristiqueRepository.save(s);
    }

    @Override
    @Transactional
    public SiteTouristique modifEtPublier(Long id) {
        SiteTouristique s = siteTouristiqueRepository.findById(id).orElseThrow(() ->
                new SiteNotFoundException("site introuvable"));
        s.setStatutPublicationSite(StatutPublicationSite.PUBLIE);
        return siteTouristiqueRepository.save(s);
    }

    @Override
    @Transactional
    public SiteTouristique modifEtBroullon(Long id) {
        SiteTouristique s = siteTouristiqueRepository.findById(id).orElseThrow(() ->
                new SiteNotFoundException("site introuvable"));
        s.setStatutPublicationSite(StatutPublicationSite.BROUILLON);
        return siteTouristiqueRepository.save(s);
    }

    @Override
    public void deleteSiteTouristique(SiteTouristique s) {
        siteTouristiqueRepository.delete(s);
    }

    @Override
    public void deleteRegionById(Long id) {
        siteTouristiqueRepository.deleteById(id);
    }

    @Override
    public SiteTouristique getSiteTouristique(Long id) {
        return siteTouristiqueRepository.findById(id).get();
    }

    @Override
    public Page<SiteTouristique> getAllSiteTouristique(Pageable pageable) {
        return siteTouristiqueRepository.findAll(pageable);
    }

    @Override
    public Page<DestinationDTO> rechercherDTO(
            String motCle,
            String categorie,
            String region,
            TriSite tri,
            boolean asc,
            Pageable pageable
    ) {
        return siteTouristiqueRepository.findAllDestinations(motCle, categorie, region, tri, asc, pageable);

    }


    @Override
    public CardInfosDetailsDTO getSiteDetail(Long id) {
        return siteTouristiqueRepository.findDetailSite(id);
    }

    @Override
    public StatistiqueDTO getSiteStatistiqueAvis(Long id) {
        return siteTouristiqueRepository.getStatistiqueAvis(id);
    }

    @Override
    public SiteTouristique enregistrerImgEtResteInfo(
            SiteTouristique site,
            List<MultipartFile> imgs
    ) throws IOException {
        if(imgs == null || imgs.isEmpty()) {
            throw new IllegalArgException("Veullez inserer un image !");
        }
        if(imgs.size() > 8) {
            throw new MaxPageImageToSiteException("Pas plus de 8 image");
        }
        for(MultipartFile img : imgs) {
            imageService.estVraiImageValide(img);
            imageService.uplaodImage(img,site.getId());
        }
        List<Image> images = imageRepository.findBySiteTouristiqueIdOrderByIdImageAsc(site.getId()); // MODIFIÉ
        Categorie categorie = categorieRepository.findById(site.getCategorie().getId()).get();
        Administrateur administrateur = administrateurRepository.findById(site.getAdministrateur().getId()).get();
        Region region = regionRepository.findById(site.getRegion().getId()).get();
        site.setImages(images);
        site.setCategorie(categorie);
        site.setAdministrateur(administrateur);
        site.setRegion(region);
        return site;
    }
    @Override
    public Page<SiteTouristique> getSitesByAdministrateur(Long administrateurId, Pageable pageable) {
        return siteTouristiqueRepository.findByAdministrateur_Id(administrateurId, pageable);
    }
}


