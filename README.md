# MboaTour — API backend

MboaTour est une API REST pour une plateforme de découverte des sites touristiques du Cameroun. Le backend fournit les services de données utilisés par l'application web : sites touristiques, régions, catégories, images, utilisateurs, favoris et avis.

## Dépôts du projet

Le projet est organisé en deux dépôts reliés par des liens :

- **Frontend Vue 3 + TypeScript** : [MboaTour-Frontend](https://github.com/Cs7Awoumoujoseph/MboaTour-Frontend)
- **Backend Java + Spring Boot** : [MboaTour](https://github.com/Cs7Awoumoujoseph/MboaTour) — dépôt actuel

Le frontend utilise Axios pour appeler l'API. Sa configuration HTTP se trouve dans [`src/api/axios.ts`](https://github.com/Cs7Awoumoujoseph/MboaTour-Frontend/blob/main/src/api/axios.ts).

## Technologies

- Java 17
- Spring Boot 4.0.3
- Spring Web et Spring Data JPA
- PostgreSQL
- Maven
- Lombok et ModelMapper

## Fonctionnalités et domaines métier

Le code est organisé autour des principaux domaines suivants :

- Sites touristiques et détails associés
- Régions et catégories
- Images des sites
- Utilisateurs et administrateurs
- Avis, commentaires et favoris
- Statistiques liées aux sites
- Objets DTO pour structurer les échanges avec le frontend
- Gestion centralisée de certaines exceptions

Les contrôleurs REST constituent les points d'entrée HTTP. Les routes et leurs paramètres exacts doivent être vérifiés dans les classes du dossier `restController/`.

## Prérequis

- JDK 17
- PostgreSQL
- Git
- Maven (facultatif si le wrapper Maven fourni fonctionne)

## Installation et démarrage

### 1. Cloner le dépôt

```bash
git clone https://github.com/Cs7Awoumoujoseph/MboaTour.git
cd MboaTour
```

### 2. Créer la base de données

Créez une base PostgreSQL nommée `mboaTour` (ou adaptez `DB_URL` à votre configuration).

### 3. Configurer les variables d'environnement

Le fichier [`.env.example`](https://github.com/Cs7Awoumoujoseph/MboaTour/blob/main/.env.example) répertorie les paramètres attendus. Les variables doivent être définies dans l'environnement du processus Java ; un fichier `.env` n'est pas automatiquement chargé par Spring Boot sans configuration supplémentaire.

| Variable | Valeur par défaut | Description |
|---|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/mboaTour` | URL JDBC PostgreSQL |
| `DB_USERNAME` | `postgres` | Nom de l'utilisateur PostgreSQL |
| `DB_PASSWORD` | vide | Mot de passe PostgreSQL à définir localement |
| `SHOW_SQL` | `false` | Active l'affichage des requêtes SQL |

Ne commitez jamais de mot de passe ou de secret réel. Pour la production, configurez les variables dans l'environnement ou le gestionnaire de secrets de l'hébergeur.

### 4. Lancer l'API

Sous Linux/macOS :

```bash
./mvnw spring-boot:run
```

Sous Windows PowerShell :

```powershell
.\mvnw.cmd spring-boot:run
```

Le contexte HTTP est configuré sur `/mboa`. En local, la base URL de l'API est donc généralement :

```text
http://localhost:8080/mboa
```

### 5. Compiler et lancer les tests

```bash
./mvnw test
./mvnw package
```

Sous Windows, utilisez `.\mvnw.cmd test` et `.\mvnw.cmd package`.

## Structure du projet

```text
src/main/java/com/mbatour/mboa/
├── DTO/             # Objets de transfert de données
├── config/          # Configuration, dont l'initialisation éventuelle de données
├── entite/          # Entités JPA
├── exception/       # Exceptions métier et réponses d'erreur
├── repository/      # Accès aux données
├── restController/  # Contrôleurs REST
├── security/        # Configuration de sécurité
└── service/         # Logique métier
src/main/resources/
└── application.properties
```

## Connexion avec le frontend

Le frontend correspondant est le dépôt [MboaTour-Frontend](https://github.com/Cs7Awoumoujoseph/MboaTour-Frontend). Il est configuré actuellement pour appeler `http://localhost:8080/mboa`.

La configuration CORS du backend autorise actuellement l'origine de développement `http://localhost:5173`. Avant la mise en production :

1. Ajoutez l'origine exacte du frontend déployé à la configuration CORS.
2. Configurez le frontend pour utiliser l'URL HTTPS publique de l'API.
3. Vérifiez les requêtes préflight CORS, les uploads d'images et les opérations nécessitant une authentification.
4. Ne publiez pas les identifiants de base de données ni d'autres secrets.

## Sécurité et exploitation

- Définissez `DB_PASSWORD` hors du code source.
- Vérifiez les règles d'authentification et d'autorisation avant une mise en production.
- Évitez d'exposer les outils ou routes d'administration sans contrôle d'accès.
- Vérifiez la stratégie de génération et de mise à jour du schéma de base de données avant toute migration de production.

## Dépôts associés

- Interface web : https://github.com/Cs7Awoumoujoseph/MboaTour-Frontend
- API backend : https://github.com/Cs7Awoumoujoseph/MboaTour
