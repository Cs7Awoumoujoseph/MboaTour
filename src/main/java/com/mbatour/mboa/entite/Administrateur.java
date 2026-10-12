package com.mbatour.mboa.entite;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Administrateur extends Utilisateur {

    private String matricule;

    private String fonction;

    private String telephone;

    @ElementCollection
    private List<String> documentsJustificatifs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "administrateur" , cascade = CascadeType.ALL)
    private List<SiteTouristique> sites = new ArrayList<>();

    public Administrateur() {
        this.setType(UserType.ADMIN); // valeur automatique à la création
    }

}