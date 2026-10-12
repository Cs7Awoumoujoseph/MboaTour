package com.mbatour.mboa.restController;

import com.mbatour.mboa.entite.Image;
import com.mbatour.mboa.entite.SiteTouristique;
import com.mbatour.mboa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/img")
@CrossOrigin
public class ImageRestController {
    @Autowired
    private ImageService imageService;

    // UPLOAD D'UNE IMAGE // =========================
    @PostMapping(value = "/uplaodImageSite/{idSite}" )
    public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
                                   @PathVariable("idSite") Long idSite)
            throws IOException {
        return imageService.uplaodImage(file,idSite);
    }

    @PutMapping("/{id}/principal")
    public ResponseEntity<Image> setPrincipal(@PathVariable Long id) throws IOException {
        Image img = imageService.setPrincipal(id);
        return ResponseEntity.ok(img);
    }

    @RequestMapping(value = "/imgSite/{idSite}" ,
            method = RequestMethod.GET)
    public List<Image> getImagesSite(@PathVariable("idSite") Long idSite)
            throws IOException {
        return imageService.getImagesParSite(idSite);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageDetails( @PathVariable Long id ) throws IOException {
        Image image = imageService.getImageDetails(id);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id ) throws IOException {
        return imageService.getImage(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage( @PathVariable Long id ) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/img")
    public ResponseEntity<Image> modifImg(
            @PathVariable Long id,
            @RequestBody MultipartFile image) throws IOException {
        Image img = imageService.modifImage(id,image);
        return ResponseEntity.ok(img);
    }



}
