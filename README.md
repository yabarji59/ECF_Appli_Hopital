# **ECF_App_Hopital** 🏥

(sprint 1)

**Description :**\
_Il s’agit d’une application de gestion des séjours de patients dans un hôpital, permettant l'ajout, la modification et le suivi des patients et de leurs séjours. Le système assure la gestion des services hospitaliers, des lits et des chambres, avec la possibilité d'admettre et de libérer les patients, ainsi que d'affecter les lits selon les besoins._

## **-----------| MERISE |**

_Le système a pour objectif de gérer les patients hospitalisés depuis leur admission jusqu'à leur sortie:_
_Les entités principales comprennent l'administrateur, les patients, les séjours, les services, les lits et les chambres._

_Les règles métier incluent la gestion des admissions et des sorties, ainsi que l'affectation des patients aux services et aux lits, et la libération de la chambre (donc du lit) à la sortie d'un patient._

_Les fonctionnalités principales sont la gestion des patients (ajout et modification), la gestion des séjours (arrivée et sortie) et l'affectation des patients aux services et aux lits._

_Chaque entité est caractérisée par des attributs spécifiques, tels que l'identifiant, le nom, la date, etc qui définissent leurs propriétés et relations_

**En détail :**

**Entités principales:**

- Patient: Individu pris en charge par l'hôpital,
- Séjour: Période d'hospitalisation d'un patient,
- Service: Unité de soins de l'hôpital,
- Lit: Unité d'hébergement d'un patient dans un service,
- Chambre: Unité d'hébergement d'un ou plusieurs lits

**Règles métier principales:**

- Un nouveau patient peut être ajouter,
- Un patient existant peut être modifier,
- Un patient peut avoir plusieurs séjours,
- L'arrivée d'un patient dans un service peut être prise en compte,
- Un patient peut être envoyé un service,
- Un service possède plusieurs chambres,
- Une chambre possède un seul lit,
- Un lit est affecté à un seul patient
- La sortie d'un patient peut être prise en compte,
- Au départ du patient, la chambre (et donc le lit) est libéré.

**Fonctionnalités principales:**

- Gestion des patients (ajout, modification),
- Gestion des séjours (admission, sortie),
- Affectation des patients aux services et aux lits,
- Libérer la chambre à la sortie d'un patient.

### Dictionnaire de données

![Dico-données](./Merise/Dictionnaire_de_donnees.png)

### MCD

![MCD](./Merise/MCD.png)

### MPD

![MPD](./Merise/MPD.png)

### MLD

![MLD](./Merise/MLD.png)

---

## **-----------| UML |**

### Règles de gestion :

**Acteur :**

C'est l'Administrateur qui s’occupe de la gestion des patients, des séjours et des lits dans les quatres services de l’hopital

**Conditions :**

- L’hôpital contient quatre services : Service-1, Service-2, Service-3 et Urgences
- Chaque Service est composé de 10 chambres
- Et chaque chambre contient 1 seul lit
- Un patient doit exister dans la base de données pour que son arrivée soit prise en compte et ainsi être affecter à un lit dans le service concerné

**Action :**
On peut…

- Ajouter un nouveau patient : les données clients seront stockées dans la base de données
- Modifier un patient existant : il faut accéder à la base de données pour vérifier l’existence d’un patient pour modifier ses informations
- Créer un séjour
- Prendre en compte l’arrivée d’un patient dans un service : affiche et permet de vérifier la disponibilité des lits par services
- Affecter un lit à un patient
- Prendre en compte la sortie d’un patient dans un service : libère automatiquement le lit occupé

### Diagramme use case

![Diag.usecase](./Uml/ECF_App_Hopital-Use%20case.drawio.png)

### Diagramme de séquence

![Diag.séquence](./Uml/ECF_App_Hopital-Diagramme%20de%20séquence.drawio.png)

### Diagramme de classes

![Diag.classes](./Uml/ECF_App_Hopital-Diagramme%20de%20classes.drawio.png)

---

## **-----------| Container |**

J'ai créé un environnement Docker avec PostgreSQL et pgAdmin. J'ai configuré PostgreSQL avec un utilisateur, un mot de passe et une base de données.

J'ai également configuré pgAdmin avec un e-mail et un mot de passe par défaut. J'ai créé des volumes pour persister les données de PostgreSQL.

En ajoutant un nouveau serveur dans pgAdmin, j'ai mis en place un environnement complet pour le développement et la gestion de mon application.

---

## **-----------| Base de données |**

**Ma base de données est composée de 5 Tables :**\
`Patient`,
`Services`,
`Lit`,
`Chambre`,
et `Sejour`

**La Table `Patient`** stocke les données des patients. Avec une contrainte d’unicité sur l’attribut `num_secu` afin que chaque n° de sécurité social soit propre à un patient et qu’il n’y est pas de doublons.

Des déclencheurs (`triggers`) sont configurés sur les attributs `dt_creation` et `dt_modification` de la Table `Patient`. Lors de l'ajout d'un nouveau patient, la date et l'heure de création sont automatiquement enregistrées, et lors de la modification d'un patient existant par un administrateur, la date et l'heure de modification sont mises à jour.

**La Table `Services`** comprend les quatre services de l’Hôpital, avec l’étage où se situe le service (`etage`) pour rendre le système plus précis et le nom du service (`nom_service`)
**Remarque :** J’ai modifié le nom de la Table : Service → `Services`. **Car 'Service' est un mot réservé par PostgreSQL**

Ensuite, **la Table `Lit`** permet de connaître la disponibilité des lits. Elle est liée à la Table Services (#`id_service`) afin de situé les lits disponible ou non dans les différents `Services`.

**La Table `Chambre`**, elle, établit la relation entre les lits (`id_lit`) et les services (`id_service`), avec une contrainte d'unicité sur le numéro de chambre pour garantir son caractère unique.

Ainsi, cela permets de savoir quel lit est disponible, quel est le numéro de la chambre où se situe le lit et dans quel services. Et un lit pourra être affecté à un patient qui dispose d’un séjour.

Enfin, **la Table `Sejour`** permet à l’administrateur de prendre en compte les arrivées et sorties de patient et d’associer un patient à un `Sejour` (`id_patient`).

Lorsque l’arrivée d’un `Patient` est pris en compte, un Sejour est créer, et il sera dirigé vers le Service approprié (`id_service`).

Le système vérifie la disponibilité des lits dans le Service concerné (`id_lit`) et affecte un lit au patient.
Lorsque le patient quitte le service, son séjour se termine automatiquement, libérant ainsi le lit et rendant la chambre disponible à nouveau.

**Conclusion :**
Ces tables permettent de gérer efficacement les données des patients, des services hospitaliers, des lits, des chambres et des séjours, assurant ainsi un suivi précis des activités et des ressources de l'hôpital.

---

## **-----------| API |**

_J'ai initié un projet Maven en Java en utilisant Spring Initializr. J'ai structuré mon code en créant les trois couches principales d'une application Java : `business`, `persistence` et `presentation`._

Dans le répertoire src/main/java/co/simplon/ECF_App_Hopital, j'ai organisé mes classes comme suit :

- Dans le dossier business, j'ai créé les sous-dossiers convert, dto et service.
- Dans le dossier persistence, j'ai créé les sous-dossiers entity et repository.
- Dans le dossier presentation, j'ai créé le dossier controller, qui contient le dossier patient.

Dans le dossier entity/, j'ai conçu la classe Patient.java pour représenter mes données métier, en y incluant les méthodes d'accès (getters/setters). Ensuite, dans le dossier business/dto/, j'ai ajouté le fichier PatientDTO.java pour définir une représentation de données transférables.

Par la suite,

Dans le fichier PatientConvert.java du dossier business/convert, j'ai implémenté des méthodes permettant de réaliser des conversions entre les objets DTO et les entités, ainsi que des conversions de ces objets en listes, facilitant ainsi le traitement et la manipulation des données dans l'application.

Ensuite, toujours dans la couche Business, service/ j’ai créé deux fichiers :

- PatientService.java qui permet de créer plus tâches à accomplir pour gérer les patients : ajouter un nouveau patient, afficher la liste des patients, afficher un patient grâce à son ‘id’ et modifier les informations d’un patient existant.
- PatientServiceImpl.java permet de concrétiser ces tâches implémentant les instructions dans dans chacune des quatre méthodes présente.

On planifie d’abord ce que le programme doit faire avant de coder.

Enfin dans la couche Presentation, j’ai créer la classe controller et défini ses méthodes pour gérer les requêtes liées aux patients.
