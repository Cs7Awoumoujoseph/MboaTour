package com.mbatour.mboa.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatistiqueDTO {
    private Double moyenneAvis;
    private Long nbreAvis;
    private Long cinqEtoile;
    private Long quatreEtoile;
    private Long troiseEtoile;
    private Long deuxEtoile;
    private Long uneEtoile;
}
