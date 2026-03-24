AnimeApp - TP3

Thème de l’application
L’application AnimeApp permet de gérer une liste d’animes
L’utilisateur peut ajouter un anime avec un titre et une description
Supprimer un anime
Marquer un anime comme vu ou non vu
Filtrer les animes vus
Activer le mode sombre

Base de données utilisée
Le projet utilise Room qui est une base de données locale Android
Les données sont stockées localement sur l’appareil
La structure est basée sur AnimeEntity pour les données AnimeDao pour les requêtes et AppDatabase pour la configuration

Comment lancer le projet
Ouvrir le projet dans Android Studio
Synchroniser Gradle
Lancer un émulateur ou connecter un appareil Android
Cliquer sur Run

Architecture du projet
La partie Data contient AnimeEntity qui représente les données AnimeDao qui contient les opérations CRUD et AppDatabase qui configure Room
Le Repository AnimeRepository sert d’intermédiaire entre le DAO et le ViewModel
Le ViewModel AnimeViewModel gère les données pour l’interface avec StateFlow
L’interface utilisateur est dans MainScreen avec Jetpack Compose

DataStore
Le projet utilise DataStore pour sauvegarder les préférences
La valeur stockée est dark_mode qui est un Boolean
true signifie que le mode sombre est activé
false signifie que le mode clair est activé

Tests
Les tests unitaires se trouvent dans app/src/test/java/com/louismartin/animeapp/
Pour lancer les tests il faut faire un clic droit sur AnimeRepositoryTest puis cliquer sur Run
Les tests vérifient l’insertion la mise à jour et la suppression d’un anime

Technologies utilisées
Kotlin Jetpack Compose Room ViewModel StateFlow DataStore JUnit

Fonctionnalités principales
CRUD complet
Filtrage des animes vus
Persistance du thème sombre
Tests unitaires fonctionnels