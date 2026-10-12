package com.mbatour.mboa.service;

import com.mbatour.mboa.entite.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image uplaodImage(MultipartFile file, Long id_site) throws IOException;
    Image getImageDetails(Long id) throws IOException;
    ResponseEntity<byte[]> getImage(Long id) throws IOException;
    void deleteImage(Long id);
    List<Image> getImagesParSite(Long prodId);
    public boolean estVraiImageValide(MultipartFile fichier);
    public Image modifImage(Long id,MultipartFile fichier) throws IOException;
    Image setPrincipal(Long imageId) throws IOException;
}
