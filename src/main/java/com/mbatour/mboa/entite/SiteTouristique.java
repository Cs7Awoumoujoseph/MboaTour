package com.mbatour.mboa.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SiteTouristique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(length = 255)
    private String idealPour;

    @Column(length = 255)
    private String meilleurePeriode; // correspond à "bestPeriod" côté front

    @Column(length = 1000)
    private String phraseAccroche;

    @Column(length = 8000)
    private String description;

    private String localisation;

    private String horaires;

    private BigDecimal tarif;

    private String latitude ;

    private String longitude ;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_publication", nullable = false)
    private StatutPublicationSite statutPublicationSite ;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)    private Date dateCreation;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "region_id",nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "categorie_id",nullable = false)
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "administrateur_id")
    private Administrateur administrateur;

    @JsonIgnore
    @OneToMany(mappedBy = "siteTouristique" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Avis> avis = new ArrayList<>();

    @OneToMany(mappedBy = "siteTouristique",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Image> images;

    @JsonIgnore
    @OneToMany(
            mappedBy = "siteTouristique",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Favori> favoris = new ArrayList<>();


}