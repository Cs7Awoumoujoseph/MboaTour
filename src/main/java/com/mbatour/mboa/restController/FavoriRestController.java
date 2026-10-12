package com.mbatour.mboa.restController;

import com.mbatour.mboa.DTO.FavoriDTO;
import com.mbatour.mboa.service.FavoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoris")
@CrossOrigin
public class FavoriRestController {
    @Autowired
    private FavoriService favoriService;

    @PostMapping("/ajouter")
    public ResponseEntity<FavoriDTO> ajouterFavori(
            @RequestParam Long utilisateurId,
            @RequestParam Long siteId
    ) {
        FavoriDTO favori = favoriService.ajouterFavori(utilisateurId, siteId);
        return ResponseEntity.ok(favori);
    }

    @DeleteMapping("/supprimer")
    public ResponseEntity<Void> supprimerFavori(
            @RequestParam Long utilisateurId,
            @RequestParam Long siteId
    ) {
        favoriService.supprimerFavori(utilisateurId, siteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<FavoriDTO>> getFavorisUtilisateur(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(favoriService.getFavorisUtilisateur(id));
    }
}