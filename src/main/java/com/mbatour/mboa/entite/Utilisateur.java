package com.mbatour.mboa.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class  Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;

  @Column(unique = true)
  private String email;

  private  String motDePasse;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(updatable = false)  private Date dateInscription;

  @JsonIgnore
  @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
  private List<Avis> avis = new ArrayList<>() ;

  @Enumerated(EnumType.STRING)
  private UserType type;


  @JsonIgnore
  @OneToMany(
          mappedBy = "utilisateur",
          cascade = CascadeType.ALL
  )
  private List<Favori> favoris = new ArrayList<>();

}
