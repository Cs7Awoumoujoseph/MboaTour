package com.mbatour.mboa.service;

import com.mbatour.mboa.DTO.AvisCreateDTO;
import com.mbatour.mboa.DTO.AvisUpdateDTO;
import com.mbatour.mboa.DTO.AvisUserDTO;
import com.mbatour.mboa.DTO.UserCommentDTO;
import com.mbatour.mboa.entite.Avis;

import java.util.List;

public interface AvisService {
    Avis saveAvis(Avis a);
    Avis updateAvis(Avis a);
    void deleteAvis(Avis a);
    void deleteAvisById(Long id);
    Avis getAvis(Long id);
    List<Avis> getAllAvis();
    UserCommentDTO ajouterAvis(AvisCreateDTO dto);
    UserCommentDTO modifierAvis(Long avisId, AvisUpdateDTO dto);
    List<AvisUserDTO> getAvisByUser(Long utilisateurId);
}