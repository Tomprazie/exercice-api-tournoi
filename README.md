## Projet API REST

Réalisation d'une application exposant une API REST pour gérer le classement de joueurs lors d'un tournoi.
Les joueurs sont triés en fonction du nombre de points de chacun: du joueur ayant le plus de points à celui qui en a le moins.
L'API devra permettre :<br>

·           d'ajouter un joueur (son pseudo)<br>
·           de mettre à jour le nombre de points du joueur<br>
·           de récupérer les données d'un joueur (pseudo, nombre de points et classement dans le tournoi)<br>
·           de retourner les joueurs triés en fonction de leur nombre de points<br>
·           de supprimer tous les joueurs à la fin du tournoi<br>




## Installation et lancement

### Prérequis

- Docker
- JDK 11+
- Gradle

### Etapes

```bash
# Télécharger la dernière image PostgreSQL
docker pull postgres:latest

# Exécuter le conteneur PostgreSQL en arrière-plan
docker run -d --name POSTGRES -e POSTGRES_PASSWORD=password -p 5432:5432 postgres

# Créer et peupler la base de données
docker exec -i POSTGRES psql -U postgres -d playerdb < console.sql

# Lancer l'application
./gradlew run
```


## Exemple d'utilisation de l'API


### Ajouter un joueur
- **URL:** `/players/add`
- **Méthode:** `POST`
- **Corps:**
  ```json
  {
    "pseudo": "Charlie"
  }
  ```

### Mettre à jour le nombre de points d'un joueur
- **URL:** `/players/Alice/points`
- **Méthode:** `PUT`
- **Corps:**
  ```
  99
  ```

### Récupérer les données d'un joueur
- **URL:** `/players/Alice`
- **Méthode:** `GET`
- **Réponse:**
  ```json
  [
    {
    "player": {
        "pseudo": "Alice",
        "points": 100
    },
    "ranking": 2
    }
  ]
    ```

### Récupérer les joueurs triés en fonction de leur nombre de points
- **URL:** `/players/ranking`
- **Méthode:** `GET`
- **Réponse:**
  ```json
  [
    {
        "player": {
            "pseudo": "Bob",
            "points": 200
        },
        "ranking": 1
    },
    {
        "player": {
            "pseudo": "Alice",
            "points": 100
        },
        "ranking": 2
    }
  ]
    ```
  
### Supprimer tous les joueurs
- **URL:** `/players`
- **Méthode:** `DELETE`


## Points d'amélioration de l'application

1. **Tests unitaires**: Ajouter des tests unitaires supplémentaires pour couvrir tous les cas d'utilisation possibles et garantir la fiabilité du code.

2. **Gestion des erreurs**: Ajouter une gestion des erreurs plus robuste pour les différentes opérations de l'API, comme la validation des entrées et la gestion des exceptions.

3. **Sécurité**: Implémenter des mécanismes de sécurité pour empêcher les attaques par injection SQL.








