<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail du panier client</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">

		
		<c:choose>
			<c:when test="${ empty panier }">
				<p>Aucun produit dans votre panier! N'hésitez pas à en rajouter ;)...</p>
					<a href="<c:url value="/listeProduits" />"><button>Ajouter un produit</button></a>
			</c:when>
			<c:otherwise>	
				<table>
					<thead>
						<tr>
								<th>Produit</th>
								<th>Description</th>
								<th>Prix</th>
								<th></th>
						</tr>
					</thead>
					<tbody>
								<c:forEach items="${panier.produits}" var="panier">
			
								<td><c:out value="${ panier.produit.nom}" /></td>
								<td><c:out value="${ panier.produit.description}" /></td>
								<td><c:out value="${ panier.produit.prix}" /></td>
								<td>
									<a href="<c:url value="/listeProduits"><c:param name="id" value="${ produit.id}" /></c:url>">Voir le détail du produit</a>	
																|
									<a href="<c:url value="/modifierProduit"><c:param name="id" value="${ produit.id}" /></c:url>">Modifier la quantité</a>
									|
									<a href="<c:url value="/supprimerProduit"><c:param name="id" value="${ produit.id}" /></c:url>">Supprimer du panier</a>
								</td>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<form method="POST"
			action="<c:url value="/paiement"><c:param name="id" value="${ panier.id }" /></c:url>">
			<input type="submit" value="Payer" class="sansLabel" /> <input>
			
		</form>

	</div>
</body>
</html>