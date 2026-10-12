package com.mbatour.mboa.restController;

import com.mbatour.mboa.DTO.CardInfosDetailsDTO;
import com.mbatour.mboa.DTO.DestinationDTO;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.repository.TriSite;
import com.mbatour.mboa.service.SiteTouristiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/site")
@CrossOrigin
public class SiteTouristiqueRestController {
    @Autowired
    private SiteTouristiqueService siteTouristiqueService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping
    public Page<SiteTouristique> findAllSite(Pageable pageable) {
        return siteTouristiqueService.getAllSiteTouristique(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteTouristique> modifierInfos(@PathVariable Long id, @RequestBody SiteTouristique updates) {
        return ResponseEntity.ok(siteTouristiqueService.modifierInfos(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        siteTouristiqueService.deleteRegionById(id); // supprime le site (nom de méthode trompeur)
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/{adminId}")
    public Page<SiteTouristique> findSitesByAdmin(@PathVariable Long adminId, Pageable pageable) {
        return siteTouristiqueService.getSitesByAdministrateur(adminId, pageable);
    }
@GetMapping("/destination")
public Page<DestinationDTO> geTDestinations(
        @RequestParam(required = false) String motCle,
        @RequestParam(required = false) String categorie,
        @RequestParam(required = false) String region,
        @RequestParam(defaultValue = "MOYENNE") TriSite tri,
        @RequestParam(defaultValue = "false") boolean asc,
        Pageable pageable
) {

    return siteTouristiqueService.rechercherDTO(
            motCle,
            categorie,
            region,
            tri,
            asc,
            pageable
    );
}


    @GetMapping("/{id}")
    public SiteTouristique findOne(@PathVariable("id") Long id) {
        return siteTouristiqueService.getSiteTouristique(id);
    }

    @GetMapping("/{id}/details")
    public CardInfosDetailsDTO  getSiteTouristiqueDetails(@PathVariable Long id) {
        return siteTouristiqueService.getSiteDetail(id);
    }

    @GetMapping("/{id}/stat")
    public StatistiqueDTO getSiteStatistiqueAvisDetails(@PathVariable Long id) {
        return siteTouristiqueService.getSiteStatistiqueAvis(id);

    }

    @PostMapping(value = "/brouillon",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<SiteTouristique> creerBroullon(@RequestPart("site") String siteJson,
                                           @RequestPart(value = "images",required = false)List<MultipartFile> images) throws IOException {
       SiteTouristique siteTouristique = objectMapper.readValue(siteJson, SiteTouristique.class);
       SiteTouristique site = siteTouristiqueService.creerEtBroullon(siteTouristique,images);
        return ResponseEntity.ok(siteTouristiqueService.getSiteTouristique(site.getId()));
    }
    @PostMapping(value = "/publier", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SiteTouristique> creerEtPublier(@RequestPart("site") String siteJson,
                                            @RequestPart(value = "images",required = false)List<MultipartFile> images) throws IOException {
        SiteTouristique siteTouristique = objectMapper.readValue(siteJson, SiteTouristique.class);
        SiteTouristique site = siteTouristiqueService.creerEtPublierSiteTouristique(siteTouristique,images);
        return ResponseEntity.ok(siteTouristiqueService.getSiteTouristique(site.getId()));
    }

    @PutMapping("/{id}/modifPublier")
    public ResponseEntity<?> modifPublier(@PathVariable Long id){
        return ResponseEntity.ok(siteTouristiqueService.modifEtPublier(id));
    }

    @PutMapping("/{id}/modifEtBrouillon")
    public ResponseEntity<?> modifBrouillon(@PathVariable Long id){
        return ResponseEntity.ok(siteTouristiqueService.modifEtBroullon(id));
    }
}
