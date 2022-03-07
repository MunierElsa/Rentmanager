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
 
Choix de pas mettre modèle
Choix de mettre des boutons en haut des listes et pas passé par les boutons faits car avec href c'est dangereux
Choix de mettre des listes