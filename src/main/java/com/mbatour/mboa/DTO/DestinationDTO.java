package com.mbatour.mboa.DTO;

import com.mbatour.mboa.entite.Image;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DestinationDTO {
        private long id;
        private String name;
        private String category;
        private String location;
        private Long nbreAvis;
        private Double moyenneAvis;
        private String phraseIntro;
        private Image image;
}
