<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%-- CDN Bootstrap --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<%-- fichier style.css --%>
<link type="text/css" rel="stylesheet" href="<c:url value="./inc/style.css"/>"/>
<title>Nos produits</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp" />
	<div class="view">
		<c:choose>
			<c:when test="${ empty produits }">
				Aucun produit trouvé
			</c:when>
			<c:otherwise>
				<table class="table table-bordered">
					<tr>
						<th>Produit</th>
						<th>Description</th>
						<th>Prix</th>
						<th>Détails</th>
						<th>Actions</th>
					</tr>
					<c:forEach items="${produits}" var="produit" varStatus="infoBoucle">
						<tr class="${infoBoucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<td><c:out value="${produit.nom}" /></td>
							<td><c:out value="${produit.description}" /></td>
							<td><c:out value="${produit.prix}" /></td>
							<td><a href="
							<c:url value="detailsProduit"><c:param name="id" value="${produit.id}" /></c:url>
							">Voir détail</a></td>
							<td><a href="
							<c:url value="ajouterProduit?idClient=1"><c:param name="id" value="${produit.id}" /></c:url> 
							">Ajouter au panier</a></td>																	<!-- id provisoire !!! -->
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>