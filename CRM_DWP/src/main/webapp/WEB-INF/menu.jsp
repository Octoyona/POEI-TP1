<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/style.css" />" />
</head>
<body>
	<div class="menu">
		<h1>CRM</h1>
		<ul>
			<li id="boutonAccueil"><a href="<c:url value='/'/>">Accueil</a></li>
			<li id="boutonProduits"><a href="<c:url value='/listeProduits'/>">Produits</a></li>
			<li id="boutonPanier"><a href="<c:url value='/panier'/>">Mon panier</a></li>
		</ul>
	</div>
</body>
</html>