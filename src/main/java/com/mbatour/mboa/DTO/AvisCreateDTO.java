package com.mbatour.mboa.DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvisCreateDTO {
    private Long utilisateurId;
    private Long siteId;
    private Integer note;
    private String commentaire;
}