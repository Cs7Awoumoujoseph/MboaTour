# MboaTour — API backend

MboaTour est une API REST pour une plateforme de découverte des sites touristiques du Cameroun. Le backend gère notamment les destinations, régions, catégories, images, utilisateurs, favoris et avis.

## Technologies

- Java 17
- Spring Boot 4.0.3
- Spring Web et Spring Data JPA
- PostgreSQL
- Maven
- Lombok et ModelMapper

## Prérequis

- JDK 17 ou version compatible
- PostgreSQL
- Maven (facultatif si le wrapper Maven fonctionne)

## Configuration

Créez une base PostgreSQL nommée `mboaTour`, puis configurez les variables d’environnement suivantes selon votre environnement :

| Variable | Valeur par défaut | Description |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/mboaTour` | URL JDBC de PostgreSQL |
| `DB_USERNAME` | `postgres` | Utilisateur PostgreSQL |
| `DB_PASSWORD` | *(vide)* | Mot de passe PostgreSQL — à définir localement |
| `SHOW_SQL` | `false` | Affichage des requêtes SQL |

Aucun mot de passe réel ne doit être ajouté au dépôt. Pour les environnements de production, utilisez des secrets gérés par votre plateforme d’hébergement.

## Lancer l’application

Depuis la racine du projet :

```bash
./mvnw spring-boot:run
```

Sous Windows :

```powershell
.\mvnw.cmd spring-boot:run
```

Le contexte serveur est configuré sur `/mboa`. L’adresse locale de base est donc généralement `http://localhost:8080/mboa`. Les routes précises sont définies dans les contrôleurs REST du projet.

Pour compiler et lancer les tests :

```bash
./mvnw test
```

## Structure

```text
src/main/java/com/mbatour/mboa/
├── DTO/             # Objets de transfert de données
├── entite/          # Entités JPA
├── exception/       # Gestion des erreurs
├── repository/      # Accès aux données
├── restController/  # Points d’entrée REST
├── security/        # Configuration associée
└── service/         # Logique métier
src/main/resources/
└── application.properties
```

## Configuration CORS

La configuration fournie autorise actuellement l’origine de développement `http://localhost:5173`. Adaptez les origines autorisées avant tout déploiement.

## Sécurité

- Ne commitez jamais de mots de passe, jetons ou secrets.
- Configurez `DB_PASSWORD` dans l’environnement local ou dans le gestionnaire de secrets de votre hébergeur.
- Les mécanismes d’authentification et de stockage des mots de passe doivent être audités avant une mise en production.

## État du projet

Ce dépôt contient le backend Java/Spring Boot. Aucun frontend n’est inclus dans cette archive.
