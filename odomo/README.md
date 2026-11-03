# Odomo : station météo et chauffage

Projet d'IUT (SAE), réalisé seul, en Java.

Station météo personnelle couplée à une chaudière, affichée en mode texte dans la console. Le programme stocke et met à jour les données de capteurs, affiche un histogramme des relevés et gère le chauffage.

**Compétences travaillées :** Java, JUnit, algorithmique, traitement de données.

## Résultat
<img width="754" height="403" alt="image" src="https://github.com/user-attachments/assets/65d2d406-0a1e-452d-88d0-e9d687f9e865" />

## Lancer le projet

Prérequis : JDK [VERSION_JDK] ou plus récent.

Le projet a été créé avec NetBeans, mais il se lance avec n'importe quel IDE Java.

### Avec un IDE

- **IntelliJ IDEA** : *File → New → Project from Existing Sources*, choisir ce dossier, puis clic droit sur `src` → *Mark Directory as → Sources Root*. Ouvrir la classe `[CLASSE_MAIN_ODOMO]` et cliquer sur la flèche verte.
- **VS Code** : installer l'extension *Extension Pack for Java*, ouvrir ce dossier, ouvrir la classe `[CLASSE_MAIN_ODOMO]` et cliquer sur *Run*.
- **Eclipse** : *File → New → Java Project*, décocher *Use default location*, choisir ce dossier, puis lancer la classe `[CLASSE_MAIN_ODOMO]`.
- **NetBeans** : *File → Open Project*, choisir ce dossier, puis *Run Project*.

### En ligne de commande

Depuis ce dossier :

```
mkdir out
javac -d out $(find src -name "*.java")
java -cp out [CLASSE_MAIN_ODOMO]
```

Sous Windows (PowerShell), remplacer la deuxième ligne par :

```
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

## Structure

- `src` : code source
- `test` : tests unitaires JUnit
- `nbproject`, `build.xml` : configuration NetBeans





