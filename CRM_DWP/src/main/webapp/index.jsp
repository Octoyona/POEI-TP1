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
<title>CRM</title>
</head>
<body>
	<c:import url="/WEB-INF/menu.jsp"/>
	
	<h1 class="title">Bienvenue sur groupe2.com</h1>
	<h2>Vous cherchez à vous équiper pour devenir un super développeur? </h2>
	<h2>Vous êtes au bon endroit</h2>
	<button type="button" class="btn btn-outline-success" id="boutonProduits"><a href="<c:url value='/listeProduits'/>">Produits</a></button>
	<div>
			<img
				src="https://cdn.pixabay.com/photo/2022/01/04/00/49/good-job-6913940__340.png"
				alt="" />
	</div>
	<button type="button" class="btn btn-outline-success" id="s'inscrire"><a href="<c:url value='/creerClient'/>">S'inscrire</a></button>
</body>
</html>