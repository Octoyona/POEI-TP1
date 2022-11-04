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
SELECT * FROM produits WHERE nom LIKE 'c%' OR nom LIKE 'C%';

--2
SELECT * FROM produits WHERE nom LIKE '%c%' OR nom LIKE '%C%';

--3
SELECT * FROM clients INNER JOIN adresses ON clients.id_adresse=adresses.id WHERE adresses.ville='Nantes'

--4
SELECT produits.nom FROM paniers INNER JOIN contient ON paniers.id=contient.id_panier INNER JOIN produits ON contient.id_produit=produits.id WHERE paniers.id_client=1;

--5
SELECT contient.id_panier AS panier, COUNT(*) AS nb_produits FROM contient GROUP BY contient.id_panier;

--6
SELECT paniers.id_client AS client, SUM(produits.prix) AS prix_total FROM produits INNER JOIN contient ON produits.id=contient.id_produit INNER JOIN paniers ON contient.id_panier=paniers.id WHERE paniers.id_client=1;

--7
SELECT paniers.id AS panier FROM paniers INNER JOIN contient ON paniers.id=contient.id_panier INNER JOIN produits ON produits.id=contient.id_produit WHERE produits.nom='clavier';

--8
SELECT clients.nom FROM clients INNER JOIN paniers ON clients.id=paniers.id_client;