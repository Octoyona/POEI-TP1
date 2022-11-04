INSERT INTO produits(nom, decription,prix) VALUES 
('ecran', "ceci est un ecran pour ordinateur", 259.99),
('souris', "ceci est une souris pour ordinateur", 42),
('clavier', "ceci est un clavier pour ordinateur", 82.50),
('casque', "ceci est un casque audio", 159.90),
('imprimante', "ceci est une imprimante", 180);

INSERT INTO adresses(rue, ville, code_postal, pays) VALUES 
('4 rue charles de gaulle', 'Rennes', '35000', 'France'),
('16 rue du Père Lebret', 'Rennes', '35000', 'France'),
('10 rue chouquet', 'Le Havre', '76620', 'France'),
('Bieringen', 'Schöntal', '74214', 'Allemagne'),
('limpiezas hadas', 'Burgos', '09001', 'Espagne');

INSERT INTO clients(nom, prenom, id_adresse, nom_societe, mail, telephone, genre) VALUES
('Arquevaux', 'Pierre', 1, 'Infotel', 'pierre@infotel.com', '0606060606', 0),
('Aribard', 'Antoine', 2, 'M2I', 'antoine@m2i.fr', '0707070707', 0),
('Cantarano', 'Alexandra', 3, 'Infotel', 'alexandra@infotel.com', '0606060606', 1),
('Quernec', 'Alyzee', 4, 'M2I', 'alyzee@m2i.fr', '0601060106', 1),
('Deheack', 'Marina', 5, 'Infotel', 'marina@infotel.com', '0606060606', 1);

INSERT INTO paniers(id_client) VALUES
(4),
(2),
(1),
(5),
(3);

INSERT INTO contient(id_panier, id_produit) VALUES
(2,1),
(2,2),
(2,3),
(3,4),
(3,5),
(4,1),
(1,1),
(1,2),
(5,4),
(5,5);

INSERT INTO paiements (numero_carte, code_confidentiel, banque, id_client) VALUES
('156461321', '1234', 'CMB', 1),
('454534343', '4532', 'Caisse d epargne', 2),
('453373532', '7324', 'banque populaire', 3),
('732123753', '3434', 'CMB', 4),
('137453473', '7321', 'Caisse d epargne', 5);