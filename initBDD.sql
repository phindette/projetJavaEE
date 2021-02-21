use javaee;
CREATE TABLE Groupe(
    idGroupe INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100)
);

CREATE table Etudiant(
    idEtudiant INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    nbAbsence int,
    idGroupe int,
    FOREIGN KEY (idGroupe) REFERENCES Groupe(idGroupe)
);

CREATE table Note(
    idNote INT PRIMARY KEY NOT NULL,
    notation FLOAT,
    idEtudiant int,
    FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant)
);

CREATE table Absence(
    idAbsence INT PRIMARY KEY NOT NULL,
    debut DATE,
    fin DATE,
    idEtudiant int,
    FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant)
);

CREATE table Enseignant(
    idEnseignant INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    mot_de_passe VARCHAR(100)
);

CREATE table Module(
    idModule INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100),
    coef INT,
    idEnseignant int,
    FOREIGN KEY (idEnseignant) REFERENCES Enseignant(idEnseignant)
);



CREATE table Etudiant_Module(
    idEtudiant INT,
    idModule INT,
    PRIMARY KEY(idEtudiant,idModule),
    FOREIGN KEY (idEtudiant) REFERENCES Etudiant(idEtudiant),
    FOREIGN KEY (idModule) REFERENCES Module(idModule)
);

INSERT INTO groupe VALUES (1,'Groupe 1');
INSERT INTO groupe VALUES (2,"Groupe 2");

INSERT INTO etudiant VALUES (1,"DOMESTICO","Theophane",3,1);
INSERT INTO etudiant VALUES (2,"Pouchan","frederic",0,1);
INSERT INTO etudiant VALUES (3,"Moxitov","Thomas",0,2);
INSERT INTO etudiant VALUES (4,"Youri","Damien",2,2);

INSERT INTO Note VALUES (1,14.5,1);
INSERT INTO Note VALUES (2,16,1);

INSERT INTO Absence VALUES (1,'2020-12-07','2020-12-08',4);
INSERT INTO Absence VALUES (2,'2020-11-07','2020-11-08',4);
INSERT INTO Absence VALUES (3,'2020-12-07','2020-12-08',1);

INSERT INTO Enseignant VALUES(1,'Blanchon','Herve','test');
INSERT INTO Enseignant VALUES(2,'TestPRENOM','TestNOM','test');

INSERT INTO Module VALUES(1,'XML',15,1);
INSERT INTO Module VALUES(2,'Symfony',30,2);
INSERT INTO Module VALUES(3,'JavaEE',100,2);

INSERT INTO Etudiant_Module VALUES(1,1);
INSERT INTO Etudiant_Module VALUES(4,2);
INSERT INTO Etudiant_Module VALUES(1,2);
INSERT INTO Etudiant_Module VALUES(4,3);
INSERT INTO Etudiant_Module VALUES(3,3);