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
	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		<form method="POST"
			action="<c:url value="/creerClient"><c:param name="id" value="${ client.id }" /></c:url>">

			<select class="form-control" id="genreClient" name="genreClient">
				<option value="0" selected hidden="hidden">Sexe</option>
			    <option value="1">Monsieur</option>
			    <option value="2">Madame</option>
			    <option value="3">Autre</option>
			</select>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="nomClient">Nom* :</label> <input class=""
					type="text" id="nomClient" name="nomClient"
					value="<c:out value="${ client.nom }" />" /> <span class="erreur">${ erreurs.nom }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="prenomClient">Prénom* :</label> <input
					class="" type="text" id="prenomClient" name="prenomClient"
					value="<c:out value="${ client.prenom }" />" /> <span
					class="erreur">${ erreurs.prenom }</span>
			</div>
			<div class="form-group row" >
				<label class="col-sm-2 col-form-label" for="telephoneClient">Téléphone :</label> <input
					class="" type="text" id="telephoneClient" name="telephoneClient"
					value="<c:out value="${ client.telephone }" />" /> <span
					class="erreur">${ erreurs.telephone }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="rueAdresse">Rue :</label> <input
					class="" type="text" id="rueAdresse" name="rueAdresse"
					value="<c:out value="${ client.adresse.rue }" />" /> <span
					class="erreur">${ erreurs.rue }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="codePostalAdresse">Code Postal :</label> <input
					class="" type="text" id="codePostalAdresse" name="codePostalAdresse"
					value="<c:out value="${ client.adresse.codePostal }" />" /> <span
					class="erreur">${ erreurs.codePostal }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="villeAdresse">Ville :</label> <input
					class="" type="text" id="villeAdresse" name="villeAdresse"
					value="<c:out value="${ client.adresse.ville }" />" /> <span
					class="erreur">${ erreurs.ville }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="paysAdresse">Pays :</label> <input
					class="" type="text" id="paysAdresse" name="paysAdresse"
					value="<c:out value="${ client.adresse.pays }" />" /> <span
					class="erreur">${ erreurs.pays }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="mailClient">Mail* :</label> <input
					class="" type="text" id="mailClient" name="mailClient"
					value="<c:out value="${ client.mail }" />" /> <span class="erreur">${ erreurs.mail }</span>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="nomSociete">Société :</label> <input
					class="" type="text" id="nomSociete" name="nomSociete"
					value="<c:out value="${ client.nomsociete }" />" /> <span
					class="erreur">${ erreurs.nomSociete }</span>
			</div>

			<input type="submit" value="Envoyer" class="sansLabel" /> <input
				type="reset" value="Remettre à zéro" class="sansLabel" />
		</form>
		<div>
			<a href="<c:url value="/"></c:url>">Accueil </a>
		</div>
	</div>

</body>
</html>