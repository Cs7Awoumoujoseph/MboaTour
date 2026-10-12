package com.mbatour.mboa.repository;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import com.mbatour.mboa.entite.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SiteTouristiqueCriteria {
    public static Join<SiteTouristique, Categorie> categorieJoin(Root<SiteTouristique> root) {
        return root.join("categorie", JoinType.LEFT);
    }
    public static Join<SiteTouristique, Region> regionJoin(Root<SiteTouristique> root) {
        return root.join("region", JoinType.LEFT);
    }
    public static Join<SiteTouristique, Image> imageJoin(Root<SiteTouristique> root) {
        return root.join("images", JoinType.LEFT);
    }
            public static Join<SiteTouristique, Avis> avisJoin(Root<SiteTouristique> root) {
                return root.join("avis", JoinType.LEFT);
            }
    public static Expression<Long> nombreAvis(CriteriaBuilder cb ,Join<SiteTouristique, Avis> avis) {
        return cb.count(avis);
    }

    public static Expression<Double> moyenneAvis(CriteriaBuilder cb ,Join<SiteTouristique, Avis> avis) {
        return cb.coalesce(cb.avg(avis.get("note")),0.0);
    }

    public static Expression<Long> nbreEtoile(CriteriaBuilder cb ,Join<SiteTouristique,Avis> avi,int note) {
        return cb.sum(
                cb.<Long>selectCase()
                        .when(cb.equal(avi.get("note"),note),1L)
                        .otherwise(0L)
        );
    }
    public static Expression<Long> premierIdImage(CriteriaQuery<?> query, CriteriaBuilder cb, Root<SiteTouristique> site) {
        // Sous-requête : id de l'image marquée comme "principale" pour ce site
        Subquery<Long> subPrincipale = query.subquery(Long.class);
        Root<Image> imgP = subPrincipale.from(Image.class);
        subPrincipale.select(imgP.get("idImage"));
        subPrincipale.where(
                cb.equal(imgP.get("siteTouristique"), site),
                cb.isTrue(imgP.get("principal"))
        );

        // Sous-requête de secours : la première image uploadée (si aucune "principale" n'est définie)
        Subquery<Long> subPremiere = query.subquery(Long.class);
        Root<Image> imgM = subPremiere.from(Image.class);
        subPremiere.select(cb.min(imgM.get("idImage")));
        subPremiere.where(cb.equal(imgM.get("siteTouristique"), site));

        return cb.coalesce(subPrincipale, subPremiere);
    }
    public static Predicate idEgal(
            CriteriaBuilder cb ,
            Root<SiteTouristique> root,
            Long id){
        return cb.equal(root.get("id"), id);
    }

    public static List<Predicate> filtre(
            CriteriaBuilder cb ,
            Root<SiteTouristique> root,
            Join<SiteTouristique, Categorie> categori,
            Join<SiteTouristique,Region> regio ,
            String motCle ,
            String categorie,
            String region

    ) {


            List<Predicate> predicates = new ArrayList<>();

            if (motCle != null && !motCle.isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nom")) ,'%'+ motCle.toLowerCase()+'%'
                        )
                );
            }

            if (categorie != null && !categorie.isBlank()) {
                predicates.add(
                        cb.equal(
                                categori.get("nom"),
                                categorie
                        )
                );
            }



            if (region != null && !region.isBlank()) {
                predicates.add(
                        cb.equal(
                                regio.get("nom"),
                                region
                        )
                );
            }

            return predicates ;

    }


    // =====================================================
    // GESTION DU TRI
    // =====================================================

    public List<Order> construireTrie(
            CriteriaBuilder cb,
            Root<SiteTouristique> site,
            Expression<Long> nombreAvis,
            Expression<Double> moyenne,
            TriSite tri,
            boolean asc
    ) {


        List<Order> orders =
                new ArrayList<>();


        if(tri == null){

            orders.add(
                    cb.desc(moyenne)
            );

            return orders;
        }



        switch(tri){


            case POPULARITE -> {


                if(asc){

                    orders.add(
                            cb.asc(nombreAvis)
                    );

                    orders.add(
                            cb.asc(moyenne)
                    );


                }else{


                    orders.add(
                            cb.desc(nombreAvis)
                    );


                    orders.add(
                            cb.desc(moyenne)
                    );

                }

            }



            case MOYENNE -> {


                orders.add(
                        asc ?
                                cb.asc(moyenne)
                                :
                                cb.desc(moyenne)
                );

            }



            case NOM -> {


                orders.add(

                        asc ?

                                cb.asc(
                                        site.get("nom")
                                )

                                :

                                cb.desc(
                                        site.get("nom")
                                )

                );

            }

        }


        return orders;

    }




}



