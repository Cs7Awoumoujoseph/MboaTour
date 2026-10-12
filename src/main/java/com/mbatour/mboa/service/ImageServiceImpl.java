package com.mbatour.mboa.service;

import com.mbatour.mboa.entite.Image;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.exception.IllegalArgException;
import com.mbatour.mboa.exception.MaxPageImageToSiteException;
import com.mbatour.mboa.exception.SiteNotFoundException;
import com.mbatour.mboa.repository.ImageRepository;
import com.mbatour.mboa.repository.SiteTouristiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    SiteTouristiqueRepository siteTouristiqueRepository;

    private static final long MAX_IMAGE_SIZE = 5*1024*1024; //5M0



    @Override
    public Image uplaodImage(MultipartFile file,Long id_site) throws IOException {
        SiteTouristique site = siteTouristiqueRepository
                .findById(id_site)
                .orElseThrow(() ->
                        new SiteNotFoundException("site introuvable")
                );

        long nombreImages =
                imageRepository.countBySiteTouristiqueId(id_site);

        if(!estVraiImageValide(file)){
            throw new IllegalArgException("Image invalide");
        }
        if (nombreImages >= 8) {
            throw  new MaxPageImageToSiteException("Images maximales atteints");

        }



        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .siteTouristique(site)
                .build()
        )
                ;
    }
    @Override
    public Image getImageDetails(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository.findById (id);
        return Image.builder()
                .idImage(dbImage.get().getIdImage())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage()).build() ;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException{
        final Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> getImagesParSite(Long prodId) {
        SiteTouristique s = siteTouristiqueRepository.findById(prodId).get();
        return s.getImages();

    }

    @Override
    public Image modifImage(Long id,MultipartFile fichier) throws IOException{
        Image image = imageRepository.findById(id).orElseThrow(()->
                new RuntimeException("Image introuvable")

        );
        image.setImage(fichier.getBytes());
        image.setName(fichier.getOriginalFilename());
        image.setType(fichier.getContentType());
        return imageRepository.save(image);
    }

    @Override
    public boolean estVraiImageValide(MultipartFile fichier){
        if(fichier == null || fichier.isEmpty()){
            return false;
        }
        if(fichier.getSize() > MAX_IMAGE_SIZE){
            throw new IllegalArgException("image ne doit pas depasser 5Mo");
        }
        String contentype = fichier.getContentType();
        if(contentype == null || !contentype.startsWith("image/")){
           throw  new IllegalArgException("Le fichier doit etre une image");
        }
        try{
            return ImageIO.read(fichier.getInputStream()) != null;
        }catch(IOException e){
            throw new IllegalArgException("image invalide");
        }
    }

    @Override
    public Image setPrincipal(Long imageId) throws IOException {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image introuvable"));

        Long siteId = image.getSiteTouristique().getId();
        List<Image> imagesDuSite = imageRepository.findBySiteTouristiqueId(siteId);

        for (Image img : imagesDuSite) {
            img.setPrincipal(img.getIdImage().equals(imageId));
            imageRepository.save(img);
        }

        return imageRepository.findById(imageId).get();
    }

}
