/*1. Récupérer la liste de tous les produits commençant par "C"
2. Récupérer la liste de tous les produits contenant un "C"
3. Récupérer tous les clients habitant à Nantes
4. Récupérer tous les paniers d'un client
5. Récupérer le nombre de produits de chaque panier
6. Calculez le prix du panier d'un client
7. Récupérer tous les paniers contenant le produit : ( un des produits que vous avez créés)
8. Récupérer tous les clients ayant un panier
*/

--1
SELECT * FROM produit WHERE nom LIKE 'c%' OR nom LIKE 'C%';

--2
SELECT * FROM produit WHERE nom LIKE '%c%' OR nom LIKE '%C%';

--3
SELECT * FROM client INNER JOIN adresse ON client.id_adresse=adresses.id WHERE adresse.ville='Nantes'

--4
SELECT produit.nom FROM panier INNER JOIN contient ON panier.id=contient.id_panier INNER JOIN produit ON contient.id_produit=produit.id WHERE panier.id_client=1;

--5
SELECT contient.id_panier AS panier, COUNT(*) AS nb_produits FROM contient GROUP BY contient.id_panier;

--6
SELECT panier.id_client AS client, SUM(produit.prix) AS prix_total FROM produit INNER JOIN contient ON produit.id=contient.id_produit INNER JOIN panier ON contient.id_panier=panier.id WHERE panier.id_client=1;

--7
SELECT panier.id AS panier FROM panier INNER JOIN contient ON panier.id=contient.id_panier INNER JOIN produit ON produit.id=contient.id_produit WHERE produit.nom='clavier';

--8
SELECT client.nom FROM client INNER JOIN panier ON client.id=paniers.id_client;