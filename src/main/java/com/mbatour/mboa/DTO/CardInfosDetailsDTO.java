package com.mbatour.mboa.DTO;
import com.mbatour.mboa.entite.Image;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class CardInfosDetailsDTO extends DestinationDTO{
    private String horaires ;
    private BigDecimal tarif ;
    private  String description ;
    private String latitude ;
    private String longitude ;
    private Date dateCreated ;

    private List<Image> images;

    public CardInfosDetailsDTO(
            Long id,
            String nom,
            String categorie,
            String region,
            Long nombreAvis,
            Double moyenneAvis,
            String phraseIntro,
            String horaires,
            BigDecimal tarif,
            String description,
            String latitude,
            String longitude,
            Date dateCreated,
            List<Image> img

    ) {

        super(
                id,
                nom,
                categorie,
                region,
                nombreAvis,
                moyenneAvis,
                phraseIntro,
                null
        );

        this.horaires = horaires;
        this.tarif = tarif;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateCreated = dateCreated;
        this.images = img;
    }
    public CardInfosDetailsDTO(
            Long id,
            String nom,
            String categorie,
            String region,
            Long nombreAvis,
            Double moyenneAvis,
            String phraseIntro,
            String horaires,
            BigDecimal tarif,
            String description,
            String latitude,
            String longitude,
            Date dateCreated
    ) {
        this(
                id, nom, categorie, region, nombreAvis, moyenneAvis,
                phraseIntro, horaires, tarif, description, latitude,
                longitude, dateCreated, null
        );
    }

}
