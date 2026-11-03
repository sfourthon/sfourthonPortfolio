# Labyrinthe : jeu d'exploration en Java / JavaFX

Projet d'IUT (SAE), réalisé seul en 2 semaines.

Univers multi-étages construit sur des interfaces fournies, avec contrôles clavier en temps réel et éclairage dynamique selon la distance.

**Compétences travaillées :** JavaFX, interfaces, classes abstraites, polymorphisme, `equals`/`hashCode`, `List`, `Map`, `Set`.

## Résultat
<img width="818" height="860" alt="image" src="https://github.com/user-attachments/assets/fc3edfcd-3f33-478a-ab08-7b74d1ceada0" />

## Lancer le projet

Prérequis : un JDK 11 ou plus récent (testé avec les versions 11 et 17 recommandées) et [Maven](https://maven.apache.org/). JavaFX et les autres bibliothèques sont téléchargés automatiquement par Maven au premier lancement. Les Mac à puce Apple (M1, M2...) ne sont pas pris en charge, à cause de la version de JavaFX utilisée.

Le programme doit être lancé **depuis ce dossier** : il charge les images (`icons`) et les étages (`etages`) avec des chemins relatifs.

### En ligne de commande

```
git clone https://github.com/sfourthon/sfourthonPortfolio.git
cd sfourthonPortfolio/labyrinthe
mvn clean javafx:run
```

### Avec un IDE

Le projet utilise Maven : tous les IDE le reconnaissent. La classe principale est `application.MainJavaFX`.

- **NetBeans** : *File → Open Project*, choisir ce dossier, puis *Run Project*.
- **IntelliJ IDEA** : *File → Open*, choisir `pom.xml`, puis *Open as Project*. Attendre la fin de l'import Maven, ouvrir `application.MainJavaFX` et cliquer sur la flèche verte. Dans *Run → Edit Configurations*, vérifier que *Working directory* est bien ce dossier.
- **VS Code** : installer *Extension Pack for Java*, ouvrir ce dossier, ouvrir `application.MainJavaFX` et cliquer sur *Run*.
- **Eclipse** : *File → Import → Existing Maven Projects*, choisir ce dossier, puis lancer `application.MainJavaFX`.

## Commandes du jeu

- Flèches du clavier : déplacer le héros
- `M` : monter un escalier
- `D` : descendre un escalier

## Structure

- `src` : code source
- `etages` : fichiers de description des étages
- `icons` : images du jeu
- `pom.xml` : configuration Maven (JavaFX, JUnit, JGraphT)
