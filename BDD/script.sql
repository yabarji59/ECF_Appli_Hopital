-- -------------------------------------------------------------------------------------------------|
-- CRÉATION TABLE : Patient
CREATE TABLE IF NOT EXISTS Patient (
    id_patient SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_naissance DATE NOT NULL,
    num_secu VARCHAR(9) NOT NULL,
    adresse VARCHAR(100) NOT NULL,
    cp INT NOT NULL,
    commune VARCHAR(50) NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    -- créer par défaut avec la date et l'heure actuelle du système au moment de l'AJOUT d'un nouveau patient
    dt_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- met à jour par défaut avec la date et l'heure actuelle du système au moment de la MODIFICATION d'un patient existant
    dt_modification TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- Ajout de contrainte d'unicité : un numéro de sécurité social est unique à chaque Patient
    UNIQUE (num_secu)
);
-- > ok


-- création fonction (trigger) pour l'AJOUT DE NOUVEAU PATIENT
CREATE OR REPLACE FUNCTION set_creation_date() -- déclaration de la fonction
RETURNS TRIGGER AS $creation_nouveau_patient$ -- retourne la variable trigger
BEGIN -- la fonction démarre ici
        NEW.dt_creation := CURRENT_TIMESTAMP; -- attribution de la date et l'heure actuelles à chaque nouvel enregistrement
        RETURN NEW;
END; -- la fonction s'arrête ici
$creation_nouveau_patient$ 
LANGUAGE plpgsql; -- spécifie au système de base de données le langage dans lequel la fonction est écrite afin qu'il puisse l'exécuter correctement
-- > ok

--Création du trigger en appelant la fonction 'set_creation_date()'
CREATE TRIGGER set_creation_date_trigger
BEFORE INSERT ON Patient -- avant l'insertion dans la table Patient
FOR EACH ROW -- pour chaque ligne
EXECUTE FUNCTION set_creation_date(); -- exécute la fonction pour chaque nouvelle rangées insérées
-- > ok


-- Création fonction (trigger) pour la MODIFICATION D'UN PATIENT EXISTANT
CREATE OR REPLACE FUNCTION set_modification_date()
RETURNS TRIGGER AS $maj_patient_existant$
BEGIN
	NEW.dt_modification := CURRENT_TIMESTAMP; -- attribution de la date et l'heure actuelles à chaque modification de données
	RETURN NEW;
END;
$maj_patient_existant$ 
LANGUAGE plpgsql;
-- > ok

--Création du trigger en appelant la fonction 'set_modification_date()'
CREATE TRIGGER set_modification_date_trigger
BEFORE UPDATE ON Patient
FOR EACH ROW
EXECUTE FUNCTION set_modification_date(); -- exécute la fonction pour chaque rangées modifiée
-- > ok

-- Ajout données Patient
INSERT INTO Patient (nom, prenom, date_naissance, num_secu, adresse, cp, commune, telephone, email)
VALUES
    ('Martin', 'Sophie', '1985-04-12', 'F12345678', '123 Rue de la Paix', 75001, 'Paris', '0612345678', 'sophie.martin@example.com'),
    ('Dupont', 'Pierre', '1979-08-05', 'M98765432', '456 Avenue du Soleil', 69002, 'Lyon', '0698765432', 'pierre.dupont@example.com'),
    ('Lefebvre', 'Julie', '1990-11-23', 'F87654321', '789 Boulevard des Roses', 31000, 'Toulouse', '0654321098', 'julie.lefebvre@example.com'),
    ('Dubois', 'Jean', '1982-09-17', 'M34567890', '1010 Rue des Champs', 33000, 'Bordeaux', '0678901234', 'jean.dubois@example.com'),
    ('Thomas', 'Marie', '1976-03-29', 'F56789012', '111 Avenue Victor Hugo', 13001, 'Marseille', '0623456789', 'marie.thomas@example.com'),
    ('Moreau', 'Luc', '1995-06-08', 'M23456789', '222 Rue des Lilas', 44000, 'Nantes', '0643210987', 'luc.moreau@example.com'),
    ('Garcia', 'Laura', '1988-02-14', 'F78901234', '333 Chemin de la Rivière', 69003, 'Lyon', '0665432109', 'laura.garcia@example.com'),
    ('Rodriguez', 'Antoine', '1980-10-01', 'M45678901', '444 Boulevard des Alpes', 13002, 'Marseille', '0654321098', 'antoine.rodriguez@example.com'),
    ('Martinez', 'Emma', '1973-07-19', 'F89012345', '555 Rue des Écoles', 75002, 'Paris', '0676543210', 'emma.martinez@example.com'),
    ('Lopez', 'Thomas', '1983-12-25', 'M67890123', '666 Avenue du Lac', 33001, 'Bordeaux', '0687654321', 'thomas.lopez@example.com'),
    ('Sanchez', 'Camille', '1992-05-03', 'F01234567', '777 Avenue de la Mer', 13003, 'Marseille', '0698765432', 'camille.sanchez@example.com'),
    ('Benoit', 'Nicolas', '1987-09-09', 'M90123456', '888 Rue de la Montagne', 69004, 'Lyon', '0621098765', 'nicolas.benoit@example.com'),
    ('Roux', 'Chloé', '1981-11-20', 'F45678901', '999 Chemin du Bois', 44001, 'Nantes', '0643210987', 'chloe.roux@example.com'),
    ('Fournier', 'Julien', '1974-08-16', 'M56789012', '1010 Rue des Oliviers', 13004, 'Marseille', '0654321098', 'julien.fournier@example.com'),
    ('Girard', 'Pauline', '1998-01-27', 'F12345678', '1111 Avenue de la Forêt', 69005, 'Lyon', '0698765432', 'pauline.girard@example.com'),
    ('Fontaine', 'Alexandre', '1986-04-30', 'M98765432', '1212 Boulevard du Jardin', 75003, 'Paris', '0654321098', 'alexandre.fontaine@example.com'),
    ('Caron', 'Elodie', '1984-03-11', 'F87654321', '1313 Rue des Vignes', 44002, 'Nantes', '0623456789', 'elodie.caron@example.com'),
    ('Andre', 'Guillaume', '1977-07-04', 'M34567890', '1414 Avenue des Chênes', 69006, 'Lyon', '0643210987', 'guillaume.andre@example.com'),
    ('Picard', 'Laura', '1993-09-18', 'F56789012', '1515 Chemin de la Prairie', 33002, 'Bordeaux', '0676543210', 'laura.picard@example.com'),
    ('Leclerc', 'Mathieu', '1975-02-22', 'M23456789', '1616 Rue des Rochers', 75004, 'Paris', '0687654321', 'mathieu.leclerc@example.com')
-- clause ON CONFLICT pour éviter d'insérer des n° de sécu exitants
ON CONFLICT (num_secu) DO NOTHING;


-- Afficher la table
SELECT * FROM Patient;



-- -------------------------------------------------------------------------------------------------|
-- CRÉATION TABLE : Services
CREATE TABLE IF NOT EXISTS Services (
    id_service SERIAL PRIMARY KEY,
    nom_service VARCHAR(50),
    etage VARCHAR(1)
);
-- > ok

-- Ajout données Services
INSERT INTO Services (nom_service, etage)
VALUES
    ('Urgences', 0),
    ('Service-1', 1),
    ('Service-2', 2),
    ('Service-3', 3)
;


-- afficher la table Services
SELECT * FROM Services;



-- -------------------------------------------------------------------------------------------------|
-- CRÉATION TABLE : Sejour
CREATE TABLE IF NOT EXISTS Sejour (
    id_sej SERIAL PRIMARY KEY,
    id_patient INT NOT NULL REFERENCES Patient (id_patient),
    id_service INT NOT NULL REFERENCES Services (id_service),
    id_lit INT NOT NULL REFERENCES Lit (id_lit),
    date_arrivee_patient DATE,
    date_sortie_patient DATE
);
-- > ok


-- Ajout données Sejour (pour l'exemple)
INSERT INTO Sejour (id_patient, id_service, id_lit, date_arrivee_patient, date_sortie_patient)
VALUES
    (1, 1, 3, '2024-01-18', '2024-01-18'),
    (2, 2, 12, '2023-03-20', '2023-03-27'),
    (3, 4, 35, '2023-06-29', '2023-07-02'),
    (4, 3, 22, '2024-01-25', '2024-02-01'),
    (5, 2, 17, '2023-03-18', '2023-03-25'),
    (6, 1, 6, '2023-09-22', '2023-09-23'),
    (7, 1, 5, '2023-05-07', '2023-05-08'),
    (8, 1, 1, '2024-02-14', '2024-02-16'),
    (9, 2, 16, '2023-11-10', '2023-11-10'),
    (10, 1, 6, '2023-10-30', '2023-11-02'),
    (10, 2, 11, '2023-11-02', '2023-11-09'),
    (11, 2, 11, '2023-05-01', '2023-05-10'),
    (12, 1, 8, '2024-01-01', '2024-01-02'),
    (13, 4, 36, '2023-08-23', '2023-08-26'),
    (14, 1, 5, '2024-03-04', '2024-03-08'),
    (1, 4, 31, '2023-02-11', '2023-02-12'),
    (16, 3, 27, '2024-03-01', '2024-03-03'),
    (17, 1, 3, '2023-03-01', '2023-03-08'),
    (17, 4, 32, '2023-03-09', '2023-03-12'),
    (18, 2, 12, '2023-09-28', '2023-10-03'),
    (19, 3, 25, '2024-03-06', '2024-03-16'),
    (20, 1, 2, '2023-01-29', '2023-02-05')


-- Afficher la table Sejour
SELECT * FROM Sejour;



-- -------------------------------------------------------------------------------------------------|
-- CRÉATION TABLE : Chambre
CREATE TABLE IF NOT EXISTS Chambre (
    id_chambre SERIAL PRIMARY KEY,
    num_chambre VARCHAR(4) NOT NULL,
    id_service INT NOT NULL REFERENCES Services (id_service),
    id_lit INT NOT NULL REFERENCES Lit (id_lit),
    UNIQUE (num_chambre)
);
-- > ok


-- Ajout données Chambre (pour l'exemple)
INSERT INTO Chambre (num_chambre, id_service, id_lit)
VALUES
    ('A100', 1, 1),
    ('A101', 1, 2),
    ('A102', 1, 3),
    ('A103', 1, 4),
    ('A104', 1, 5),
    ('A105', 1, 6),
    ('A106', 1, 7),
    ('A107', 1, 8),
    ('A108', 1, 9),
    ('A109', 1, 10),
    ('B200', 2, 11),
    ('B201', 2, 12),
    ('B202', 2, 13),
    ('B203', 2, 14),
    ('B204', 2, 15),
    ('B205', 2, 16),
    ('B206', 2, 17),
    ('B207', 2, 18),
    ('B208', 2, 19),
    ('B209', 2, 20),
    ('C300', 3, 21),
    ('C301', 3, 22),
    ('C302', 3, 23),
    ('C303', 3, 24),
    ('C304', 3, 25),
    ('C305', 3, 26),
    ('C306', 3, 27),
    ('C307', 3, 28),
    ('C308', 3, 29),
    ('C309', 3, 30),
    ('D400', 4, 31),
    ('D401', 4, 32),
    ('D402', 4, 33),
    ('D403', 4, 34),
    ('D404', 4, 35),
    ('D405', 4, 36),
    ('D406', 4, 37),
    ('D407', 4, 38),
    ('D408', 4, 39),
    ('D409', 4, 40)
ON CONFLICT (num_chambre) DO NOTHING;


-- afficher la table Chambre
SELECT * FROM Chambre;


-- -------------------------------------------------------------------------------------------------|
-- CRÉATION TABLE : Lit
CREATE TABLE IF NOT EXISTS Lit (
    id_lit SERIAL PRIMARY KEY,
    disponible BOOLEAN NOT NULL DEFAULT true,
    id_service INT NOT NULL REFERENCES Services (id_service)
);
-- > ok


INSERT INTO Lit (disponible, id_service)
VALUES
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 1),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 2),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 3),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4),
    (true, 4)


-- afficher la table Lit
SELECT * FROM Lit;


-- +
-- supprimer une table
-- DROP TABLE [IF EXISTS] nom_de_la_table;

-- supprimer toutes les données d'une table
-- DELETE FROM ma_table;

-- supprimer les données d'une ligne 
-- DELETE FROM ma_table WHERE id = 1;