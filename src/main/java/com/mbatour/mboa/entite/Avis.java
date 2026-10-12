package com.mbatour.mboa.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer note;

    @Column(length = 2000)
    private String commentaire;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublication;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private SiteTouristique siteTouristique;

}