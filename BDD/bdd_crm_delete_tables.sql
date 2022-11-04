ALTER TABLE contient DROP FOREIGN KEY IF EXISTS fk_panier_contient;
ALTER TABLE contient DROP FOREIGN KEY IF EXISTS fk_produit_contient;
ALTER TABLE paniers DROP FOREIGN KEY IF EXISTS fk_client_paniers;
ALTER TABLE paiements DROP FOREIGN KEY IF EXISTS fk_client_paiements;
ALTER TABLE clients DROP FOREIGN KEY IF EXISTS fk_adresse_clients;

DROP TABLE contient;
DROP TABLE paniers;
DROP TABLE paiements;
DROP TABLE clients;
DROP TABLE produits;
DROP TABLE adresses;