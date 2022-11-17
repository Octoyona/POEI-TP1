<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste Produits</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp"/>
	<div class="view">
		<c:choose>
			<c:when test="${ empty produits }">
				Aucun produit trouv�
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>Produit</th>
						<th>Description</th>
						<th>Prix</th>
						<th>Actions</th>
					</tr>
					<c:forEach items="${produits}" var="produit" varStatus="infoBoucle">
						<tr class="${infoBoucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<td><c:out value="${produit.nom}"/></td>
							<td><c:out value="${produit.description}"/></td>
							<td><c:out value="${produit.prix}"/></td>
							<td><a>Ajouter au panier</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>