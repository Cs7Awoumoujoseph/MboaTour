package com.mbatour.mboa.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage ;
    private String name ;
    private String type ;
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    private boolean principal = false; // NOUVEAU

    @ManyToOne
    @JoinColumn(name = "siteTouristique_id")
    @JsonIgnore
    private SiteTouristique siteTouristique;

}
