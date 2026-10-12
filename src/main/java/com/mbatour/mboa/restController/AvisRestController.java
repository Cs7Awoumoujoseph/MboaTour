package com.mbatour.mboa.restController;

import com.mbatour.mboa.DTO.AvisCreateDTO;
import com.mbatour.mboa.DTO.AvisUpdateDTO;
import com.mbatour.mboa.DTO.AvisUserDTO;
import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avis")
@CrossOrigin
public class AvisRestController {

    @Autowired
    private AvisService avisService;

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterAvis(@RequestBody AvisCreateDTO dto) {
        try {
            UserCommentDTO result = avisService.ajouterAvis(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifierAvis(@PathVariable Long id, @RequestBody AvisUpdateDTO dto) {
        try {
            UserCommentDTO result = avisService.modifierAvis(id, dto);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAvis(@PathVariable Long id) {
        avisService.deleteAvisById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{id}")
    public List<AvisUserDTO> getAvisUtilisateur(@PathVariable Long id) {
        return avisService.getAvisByUser(id);
    }

}