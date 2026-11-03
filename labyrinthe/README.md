# Labyrinthe : jeu d'exploration en Java / JavaFX

Projet d'IUT (SAE), réalisé seul en 2 semaines.

Univers multi-étages construit sur des interfaces fournies, avec contrôles clavier en temps réel et éclairage dynamique selon la distance.

**Compétences travaillées :** JavaFX, interfaces, classes abstraites, polymorphisme, `equals`/`hashCode`, `List`, `Map`, `Set`.

## Résultat
<img width="818" height="860" alt="image" src="https://github.com/user-attachments/assets/fc3edfcd-3f33-478a-ab08-7b74d1ceada0" />

## Lancer le projet

Prérequis : JDK [VERSION_JDK] ou plus récent. JavaFX est téléchargé automatiquement par Maven, il n'y a rien d'autre à installer.

### Avec un IDE (le plus simple)

Le projet utilise Maven, donc tous les IDE le reconnaissent :

- **IntelliJ IDEA** : *File → Open*, choisir le fichier `pom.xml`, puis *Open as Project*. Attendre la fin du téléchargement des dépendances, ouvrir la classe `[CLASSE_MAIN_LABYRINTHE]` et cliquer sur la flèche verte.
- **VS Code** : installer l'extension *Extension Pack for Java*, ouvrir ce dossier, puis lancer la classe `[CLASSE_MAIN_LABYRINTHE]`.
- **Eclipse** : *File → Import → Existing Maven Projects*, choisir ce dossier, puis lancer la classe `[CLASSE_MAIN_LABYRINTHE]`.
- **NetBeans** : *File → Open Project*, choisir ce dossier, puis *Run Project*.

### En ligne de commande

Il faut Maven installé. Depuis ce dossier :

```
mvn clean javafx:run
```

## Structure

- `src` : code source
- `etages` : fichiers de description des étages
- `icons` : images du jeu
- `pom.xml` : configuration Maven (dépendances, JavaFX)
