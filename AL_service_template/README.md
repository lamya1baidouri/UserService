# Doctor Management Service

Ce projet est un service RESTful Spring Boot dédié à la gestion des utilisateurs, permettant l'authentification des médecins et des patients, l'enregistrement de nouveaux utilisateurs, et la mise à jour des informations des médecins. Swagger est intégré pour documenter et tester les endpoints de manière conviviale.

---

## Table des matières

- [Prérequis](#prérequis)
- [Installation et Configuration](#installation-et-configuration)
- [Démarrage de l'Application](#démarrage-de-lapplication)
- [Accès à la Documentation Swagger](#accès-à-la-documentation-swagger)
- [Utilisation de l'API](#utilisation-de-lapi)
- [Endpoints Principaux](#endpoints-principaux)
- [Sécurité et Authentification](#sécurité-et-authentification)
- [Exemples de Requêtes](#exemples-de-requêtes)
- [Exécution des Tests](#exécution-des-tests)
- [Technologies Utilisées](#technologies-utilisées)


---

## Prérequis

- **Java** 17 ou supérieur
- **Maven** 3.6 ou supérieur
- **MongoDB** pour le stockage des données
- **Spring Boot** 3.3.4

---

## Installation et Configuration

1. **Clonez le dépôt** :

    ```bash
    git clone https://github.com/lamya1baidouri/UserService
    cd AL_service_template
    ```

2. **Installez les dépendances et compilez le projet** :

    ```bash
    ./mvnw clean install
    ```

---

## Démarrage de l'Application

Pour lancer l’application, exécutez la commande suivante dans le répertoire racine du projet :

```bash
./mvnw spring-boot:run
  ```

## Accès à la Documentation Swagger
Swagger est configuré pour documenter et tester les endpoints de l’API. Pour accéder à l’interface Swagger, démarrez l'application puis rendez-vous à l’adresse suivante :

http://localhost:8080/swagger-ui/index.html

Cette interface permet de visualiser les endpoints disponibles, leurs paramètres et de tester chaque requête directement depuis le navigateur.



____
## Utilisation de l'API

### Endpoints Principaux

Voici les principaux endpoints exposés par le service :

Voici les principaux endpoints exposés par le service :

| Méthode | Endpoint                                     | Description                                     |
|---------|----------------------------------------------|-------------------------------------------------|
POST   | `/api/users/authenticate/doctor`            | Authentifier un médecin et obtenir un token JWT |
| POST    | `/api/users/authenticate/patient`    | Authentifier un patient et obtenir un token JWT |
|POST | `/api/users/register/doctor`   | Enregistrer un nouveau médecin        |
|PUT  | `/api/users/update/doctor/{userId}`  | Mettre à jour les informations d'un médecin existant

-----------


## Sécurité et Authentification
Le service utilise l'authentification basée sur JWT (JSON Web Tokens). Les endpoints de gestion des utilisateurs nécessitent une authentification appropriée.

Configuration des Rôles
DOCTOR : Accès aux opérations de gestion des médecins.
PATIENT : Accès limité aux opérations réservées aux patients.
Création d'un Utilisateur de Développement
Lors du premier démarrage, un mot de passe généré automatiquement est affiché dans les logs de démarrage pour le développement. Ce mot de passe est temporaire :
```bash
    Using generated security password: <mot_de_passe>
 ```

## Exemples de Requêtes
## Transférer un Patient vers un Autre Médecin
####  Endpoint : POST /api/users/authenticate/doctor

Requête :

```bash
{
    "email": "doctor@example.com",
    "password": "password123"
}

 ```
Réponse : 200 OK
```bash
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
 ```
## Enregistrer un Nouveau Médecin
####  Endpoint :  POST /api/users/register/doctor
Requête :

```bash
{
   "email": "newdoctor@example.com",
    "password": "securePassword",
    "name": "Dr. Jane Doe",
    "specialty": "Cardiology"
}


 ```
Réponse : 201 Created
## Exécution des Tests

Pour exécuter les tests unitaires, utilisez la commande suivante :

```bash
./mvnw test
 ``` 
Les tests couvrent les fonctionnalités principales du service, y compris les contrôles de sécurité et les actions de transfert de patients et d'assignation d'infirmières.
## Technologies Utilisées

- **Spring Boot** : Framework pour la création d'applications Java, facilitant le développement rapide et la configuration simplifiée.
- **Spring Security** : Gestion de la sécurité et de l'authentification, assurant la protection des applications contre les accès non autorisés.
- **MongoDB** : Base de données NoSQL utilisée pour le stockage des données, offrant une grande flexibilité et scalabilité.
- **Swagger** : Documentation interactive pour les APIs REST, permettant de visualiser et tester les endpoints facilement.
- **Lombok** : Génération de code simplifié pour les getters/setters, réduisant le boilerplate et améliorant la lisibilité du code.
- **JUnit** : Framework de tests unitaires, assurant la fiabilité et la qualité du code grâce à des tests automatisés.
- **JWT (JSON Web Tokens)** : Standard pour la création de tokens d'accès sécurisés.
   

