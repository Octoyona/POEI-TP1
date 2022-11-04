ALTER TABLE contient DROP FOREIGN KEY IF EXISTS fk_id_panier_contient;
ALTER TABLE contient DROP FOREIGN KEY IF EXISTS fk_id_produit_contient;
ALTER TABLE paniers DROP FOREIGN KEY IF EXISTS fk_id_client_paniers;
ALTER TABLE paiements DROP FOREIGN KEY IF EXISTS fk_id_client_paiements;
ALTER TABLE clients DROP FOREIGN KEY IF EXISTS fk_id_adresse_clients;

TRUNCATE TABLE contient;
TRUNCATE TABLE paniers;
TRUNCATE TABLE produits;
TRUNCATE TABLE clients;
TRUNCATE TABLE adresses;
TRUNCATE TABLE paiements;

ALTER TABLE contient ADD CONSTRAINT fk_id_panier_contient FOREIGN KEY (id_panier) REFERENCES paniers(id);
ALTER TABLE contient ADD CONSTRAINT fk_id_produit_contient FOREIGN KEY (id_produit) REFERENCES produits(id);
ALTER TABLE paniers ADD CONSTRAINT fk_id_client_paniers FOREIGN KEY (id_client) REFERENCES clients(id);
ALTER TABLE paiements ADD CONSTRAINT fk_id_client_paiements FOREIGN KEY (id_client) REFERENCES clients(id);
ALTER TABLE clients ADD CONSTRAINT fk_id_adresse_clients FOREIGN KEY (id_adresse) REFERENCES adresses(id);
