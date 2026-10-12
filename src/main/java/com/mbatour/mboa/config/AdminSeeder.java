package com.mbatour.mboa.security;

import com.mbatour.mboa.entite.Categorie;
import com.mbatour.mboa.entite.Region;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class AdminSeeder implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            org.springframework.data.rest.core.config.RepositoryRestConfiguration config,
            CorsRegistry cors) {

        // Expose le champ "id" dans les réponses JSON de Spring Data REST
        // (caché par défaut) pour Region et Categorie
        config.exposeIdsFor(Region.class, Categorie.class);

        cors.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH",
                        "OPTIONS"
                )
                .allowedHeaders("*");
    }
}