package com.mbatour.mboa.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "region" , cascade = CascadeType.ALL)
    private List<SiteTouristique> sites = new ArrayList<>();

}