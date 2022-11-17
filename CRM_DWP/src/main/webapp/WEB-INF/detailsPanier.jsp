<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail du panier client</title>
=======
<title>D�tail du panier client</title>
>>>>>>> origin/marina
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">

		
		<c:choose>
			<c:when test="${ empty panier }">
				<p>Aucun produit dans votre panier! N'hésitez pas à en rajouter ;)...</p>
=======
				<p>Aucun produit dans votre panier! N'h�sitez pas � en rajouter ;)...</p>
>>>>>>> origin/marina
					<a href="<c:url value="/listeProduits" />"><button>Ajouter un produit</button></a>
			</c:when>
			<c:otherwise>	
				<table class="table table-bordered">
					<thead>
						<tr>
								<th>Produit</th>
								<th>Description</th>
								<th>Prix</th>
								<th>Quantité</th>
=======
								<th>Quantit�</th>
>>>>>>> origin/marina
								<th>Total</th>
								<th>Details produit</th>
								<th>Modifier quantité</th>
								<th>Supprimer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${panier.contients}" var="contient" varStatus="infoBoucle">
							<tr class="${infoBoucle.index % 2 == 0 ? 'pair' : 'impair'}">
								<td><c:out value="${ contient.produit.nom}" /></td>
								<td><c:out value="${ contient.produit.description}" /></td>
								<td><c:out value="${ contient.produit.prix}" /></td>
								<td><c:out value="${ contient.quantite}" /></td>
								<td><c:out value="${ contient.prixTotal}" /></td>
								<td>
<<<<<<< HEAD
<<<<<<< HEAD
									<a href="<c:url value="/listeProduits"><c:param name="id" value="${ contient.produit.id}" /></c:url>">Voir le dï¿½tail du produit</a>	
								</td>
								<td>
									<a href="<c:url value="/modifierProduit"><c:param name="id" value="${ contient.produit.id}" /></c:url>">Modifier la quantitï¿½</a>
								</td>
								<td>
=======
									<a href="<c:url value="/detailsProduit"><c:param name="id" value="${ contient.produit.id}" /></c:url>">Voir le d�tail du produit</a>	

									<a href="<c:url value="/modifierProduit"><c:param name="id" value="${ contient.produit.id}" /></c:url>">Modifier la quantit�</a>

>>>>>>> 01112f8adf73f94f807b2a2e5fe498ddea3778cc
									<a href="<c:url value="/supprimerProduit"><c:param name="id" value="${ contient.produit.id}" /></c:url>">Supprimer du panier</a>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<th colspan="4"></th>
    						<th><c:out value="${ panier.prixTotal}" /></th>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<form method="POST"
			action="<c:url value="/paiementPanier"><c:param name="id" value="${ panier.id }" /></c:url>">
			<input type="submit" value="Payer" class="sansLabel" /> <input>
			
		</form>

	</div>
</body>
</html>