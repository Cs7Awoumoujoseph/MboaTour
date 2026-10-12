package com.mbatour.mboa.repository;

import com.mbatour.mboa.DTO.CardInfosDetailsDTO;
import com.mbatour.mboa.DTO.DestinationDTO;
import com.mbatour.mboa.DTO.StatistiqueDTO;
import com.mbatour.mboa.entite.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SiteTouristiqueRepositoryImpl implements SiteTouristiqueRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    private final SiteTouristiqueCriteria criteria ;
    private final ImageRepository imgeRepository;

    public SiteTouristiqueRepositoryImpl(SiteTouristiqueCriteria criteria,ImageRepository imageRepository) {
        this.criteria = criteria;
        this.imgeRepository = imageRepository;
    }


    @Override
    public CardInfosDetailsDTO findDetailSite(Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CardInfosDetailsDTO> query = cb.createQuery(CardInfosDetailsDTO.class);
        Root<SiteTouristique> root = query.from(SiteTouristique.class);

        Join<SiteTouristique, Categorie> categor = criteria.categorieJoin(root);
        Join<SiteTouristique, Region> reg = criteria.regionJoin(root);
        Join<SiteTouristique, Avis> avi = criteria.avisJoin(root);
        Expression<Long> nbrAvis = criteria.nombreAvis(cb,avi);
        Expression<Double> moyenne = criteria.moyenneAvis(cb,avi);


        query.select(
                cb.construct(
                        CardInfosDetailsDTO.class,
                        root.get("id"),
                        root.get("nom"),
                        categor.get("nom"),
                        reg.get("nom"),
                        nbrAvis,
                        moyenne,
                        root.get("phraseAccroche"),
                        root.get("horaires"),
                        root.get("tarif"),
                        root.get("description"),
                        root.get("latitude"),
                        root.get("longitude"),
                        root.get("dateCreation")
                )
        );
        query.where(criteria.idEgal(cb,root,id));

        query.groupBy(
                root.get("id"),
                root.get("nom"),
                categor.get("nom"),
                reg.get("nom"),
                root.get("phraseAccroche"),
                root.get("horaires"),
                root.get("tarif"),
                root.get("description"),
                root.get("latitude"),
                root.get("longitude"),
                root.get("dateCreation")
        );

        CardInfosDetailsDTO dto = em.createQuery(query).getSingleResult();
        dto.setImages(imgeRepository.findBySiteTouristiqueId(id));
        return dto;
    }

    @Override
    public StatistiqueDTO getStatistiqueAvis(Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StatistiqueDTO> query = cb.createQuery(StatistiqueDTO.class);
        Root<SiteTouristique> root1 = query.from(SiteTouristique.class);

        Join<SiteTouristique,Avis> avisa = SiteTouristiqueCriteria.avisJoin(root1);

        query.select(
                cb.construct(
                        StatistiqueDTO.class,
                        criteria.moyenneAvis(cb,avisa),
                        criteria.nombreAvis(cb,avisa),
                        criteria.nbreEtoile(cb,avisa,5),
                        criteria.nbreEtoile(cb,avisa,4),
                        criteria.nbreEtoile(cb,avisa,3),
                        criteria.nbreEtoile(cb,avisa,2),
                        criteria.nbreEtoile(cb,avisa,1)
                        )
        );
        query.where(criteria.idEgal(cb,root1,id));

        return em.createQuery(query).getSingleResult();
    }

    @Override
    public Page<DestinationDTO> findAllDestinations(
            String motCle,
            String categorie,
            String region,
            TriSite tri ,
            boolean asc ,
            Pageable pageable
    ){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DestinationDTO> query = cb.createQuery(DestinationDTO.class);
        Root<SiteTouristique> root = query.from(SiteTouristique.class);

        Join<SiteTouristique, Categorie> categor = criteria.categorieJoin(root);
        Join<SiteTouristique, Region> reg = criteria.regionJoin(root);
        Join<SiteTouristique, Avis> avi = criteria.avisJoin(root);
        Expression<Long> nbrAvis = criteria.nombreAvis(cb,avi);
        Expression<Double> moyenne = criteria.moyenneAvis(cb,avi);


        Join <SiteTouristique, Image> imag = criteria.imageJoin(root);
        Expression<Long> premierIdImage = SiteTouristiqueCriteria.premierIdImage(query,cb,root);  // était Subquery<Long>
        imag.on(
                cb.equal(
                        imag.get("idImage"),
                        premierIdImage
                )
        );


        query.select(
                cb.construct(
                        DestinationDTO.class,
                        root.get("id"),
                        root.get("nom"),
                        categor.get("nom"),
                        reg.get("nom"),
                        nbrAvis,
                        moyenne,
                        root.get("phraseAccroche"),
                        imag
                )
        );
        List<Predicate> predicates = criteria.filtre(cb,root,categor,reg,motCle,categorie,region);
        predicates.add(
                cb.equal(
                        root.get("statutPublicationSite"),
                        StatutPublicationSite.PUBLIE
                )
        );

        if(!predicates.isEmpty()){
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        query.groupBy(root.get("id"),root.get("nom"),categor.get("nom"),reg.get("nom"),root.get("phraseAccroche"),imag.get("idImage"),imag.get("name"),imag.get("type"),imag.get("image"));

        List<Order> orders = criteria.construireTrie(cb,root,nbrAvis,moyenne,tri,asc);
        query.orderBy(orders);

        // EXECUTION
        // ============================


        TypedQuery<DestinationDTO> typedQuery =
                em.createQuery(query);


        typedQuery.setFirstResult(
                (int) pageable.getOffset()
        );

        typedQuery.setMaxResults(
                pageable.getPageSize()
        );

        List<DestinationDTO> result =
                typedQuery.getResultList();


        long total =
                compterSites(
                        motCle,
                        categorie,
                        region
                );



        return new PageImpl<>(
                result,
                pageable,
                total
        );

    }



    // =====================================================
    // COUNT POUR PAGINATION
    // =====================================================

    private long compterSites(
            String motCle,
            String categorie,
            String region
    ){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<SiteTouristique> site = query.from(SiteTouristique.class);

        Join<SiteTouristique,Categorie> cat = SiteTouristiqueCriteria.categorieJoin(site);
        Join<SiteTouristique,Region> reg = SiteTouristiqueCriteria.regionJoin(site);

        List<Predicate> predicates = criteria.filtre(
                cb, site, cat, reg, motCle, categorie, region
        );

        // ⚠️ Ajout indispensable : même filtre que dans findAllDestinations
        predicates.add(
                cb.equal(
                        site.get("statutPublicationSite"),
                        StatutPublicationSite.PUBLIE
                )
        );

        if(!predicates.isEmpty()){
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        query.select(cb.countDistinct(site.get("id")));

        return em.createQuery(query).getSingleResult();
    }
}


