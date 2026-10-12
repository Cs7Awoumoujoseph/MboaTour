package com.mbatour.mboa.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favori {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Temporal(TemporalType.TIMESTAMP)
        private Date dateAjout;

        @ManyToOne
        @JoinColumn(name = "utilisateur_id")
        private Utilisateur utilisateur;

        @ManyToOne
        @JoinColumn(name = "site_touristique_id")
        private SiteTouristique siteTouristique;
    }

