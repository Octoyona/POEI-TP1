<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
<meta charset="ISO-8859-1">
<title>Votre produit</title>
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />
	
	<div class="card">
		<div class="card-image">
			<img
				src="https://cdn.pixabay.com/photo/2016/06/28/05/10/laptop-1483974_960_720.jpg"
				alt="Orange" />
		</div>
	
		<div class="card-body">
			<!-- Titre de l'article -->
			<div class="card-title">
				<h3><c:out value="${ produit.nom }" /></h3>
			</div>
			<div class="card-excerpt">
				<h3><c:out value="${ produit.prix }" /></h3>
			</div>
			<!-- Extrait de l'article -->
			<div class="card-excerpt">
				<p><c:out value="${ produit.description }" /></p>
			</div>

		</div>
	</div>

</body>
</html>