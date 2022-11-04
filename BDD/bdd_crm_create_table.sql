CREATE TABLE produits (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255),
    decription TEXT,
    prix FLOAT UNSIGNED
);

CREATE TABLE adresses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    code_postal VARCHAR(255) NOT NULL,
    pays VARCHAR(255) NOT NULL
);

CREATE TABLE clients (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  nom varchar(255) NOT NULL,
  prenom varchar(255) NOT NULL,
  id_adresse int(11) NOT NULL,
  nom_societe varchar(255) DEFAULT NULL,
  mail VARCHAR(255) DEFAULT NULL,
  telephone varchar(10) DEFAULT NULL,
  etat int(11) DEFAULT 0,
  genre int(11) DEFAULT 0,
  CONSTRAINT fk_id_adresse_clients FOREIGN KEY (id_adresse) REFERENCES adresses(id)
);

CREATE TABLE paiements(
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero_carte VARCHAR(255) NOT NULL,
    code_confidentiel VARCHAR(4) NOT NULL,
    banque VARCHAR(255) NOT NULL,
    id_client INT NOT NULL,
    CONSTRAINT fk_id_client_paiements FOREIGN KEY (id_client) REFERENCES clients(id)
);

CREATE TABLE paniers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    CONSTRAINT fk_id_client_paniers FOREIGN KEY (id_client) REFERENCES clients(id)
);

CREATE TABLE commandes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_panier INT NOT NULL,
    id_produit INT NOT NULL,
    CONSTRAINT fk_id_panier_commandes FOREIGN KEY (id_panier) REFERENCES paniers(id),
    CONSTRAINT fk_id_produit_commandes FOREIGN KEY (id_produit) REFERENCES produits(id)
);