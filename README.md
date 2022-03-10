## Langage JAVA
## RENTMANAGER - Application de gestion de location de véhicules
### MUNIER Elsa

___
### Exercices atteints:
Les fonctionnalités suivantes ont été développées :

+ Avoir la possibilité de créer un Client.
+ Pouvoir lister tous les Clients.
+ Avoir la possibilité de créer un Véhicule.
+ Pouvoir lister tous les Véhicules.
+ Pouvoir supprimer un Client.
+ Pouvoir supprimer un Véhicule.
+ Créer une Réservation.
+ Lister toutes les Réservations.
+ Lister toutes les Réservations associées à un Client donné.
+ Lister toutes les Réservations associées à un Véhicule donné.
+ Supprimer une Réservation.
+ Pouvoir modifier un Client.
+ Pouvoir modifier un Véhicule.
+ Pouvoir modifier une Réservation

Les contraintes suivantes ont été développées :
- Les champs nom, prénom, email et date de naissance sont obligatoires à la création d'un client
- La format de la date et la syntaxe de l'email sont vérifiés
- Un client n'ayant pas 18ans ne peut pas être créé
- Un client ayant une adresse mail déjà prise ne peut pas être créé
- Le nom et le prénom d'un client doivent faire au moins 3 caractères
- Une voiture ne peut pas être réservée 2 fois le même jour
- Une voiture ne peut pas être réservée plus de 7 jours de suite par le même utilisateur
- Si un client ou un véhicule est supprimé, alors les réservations associées sont supprimées
- Une voiture doit avoir un constructeur, son nombre de place doit être compris entre 2 et 9

Maven et Spring sont mis en place
___
### Problèmes rencontrés:
 
Voici les différents problèmes que j'ai rencontré et comment je les ai résolu :
- D'abord, j'ai décidé, en cohérence avec la base de donnée fournie, de ne pas rajouter de modèle pour un véhicule.
- Ensuite, pour éditer ou supprimer un client, un véhicule ou une réservation, j'ai décidé de mettre la liste avec les détails de chaque objet pour éviter les confusions.
- Enfin, j'ai choisi de changer les boutons mis initialement dans les jsp de l'édition et la suppression des objets clients, réservations et véhicules car pour faire passer l'Id des objets, il aurait fallu le passer via le href ce qui n'est pas sécuritaire. J'ai préféré des boutons comme celui du ajouter et que l'utilisateur sélectionne directement l'objet parmi une liste.