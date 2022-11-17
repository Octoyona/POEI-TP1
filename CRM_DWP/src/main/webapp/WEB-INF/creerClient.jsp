<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/inc/style.css" />" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:import url="/WEB-INF/menu.jsp" />

	<div class="view">
		<form method="POST"
			action="<c:url value="/creerClient"><c:param name="id" value="${ client.id }" /></c:url>">

			<select id="genreClient" name="genreClient">
				<option value="0" selected hidden="hidden">Sexe</option>
			    <option value="1">Monsieur</option>
			    <option value="2">Madame</option>
			    <option value="3">Autre</option>
			</select>
			<div>
				<label class="form-label" for="nomClient">Nom* :</label> <input class=""
					type="text" id="nomClient" name="nomClient"
					value="<c:out value="${ client.nom }" />" /> <span class="erreur">${ erreurs.nomClient }</span>
			</div>
			<div>
				<label class="form-label" for="prenomClient">Prénom* :</label> <input
					class="" type="text" id="prenomClient" name="prenomClient"
					value="<c:out value="${ client.prenom }" />" /> <span
					class="erreur">${ erreurs.prenomClient }</span>
			</div>
			<div>
				<label class="form-label" for="telephoneClient">Téléphone :</label> <input
					class="" type="text" id="telephoneClient" name="telephoneClient"
					value="<c:out value="${ client.telephone }" />" /> <span
					class="erreur">${ erreurs.telephoneClient }</span>
			</div>
			<div>
				<label class="form-label" for="rueAdresse">Rue :</label> <input
					class="" type="text" id="rueAdresse" name="rueAdresse"
					value="<c:out value="${ client.adresse.rue }" />" /> <span
					class="erreur">${ erreurs.rueAdresse }</span>
			</div>
			<div>
				<label class="form-label" for="codePostalAdresse">Code Postal :</label> <input
					class="" type="text" id="codePostalAdresse" name="codePostalAdresse"
					value="<c:out value="${ client.adresse.code_postal }" />" /> <span
					class="erreur">${ erreurs.codePostalAdresse }</span>
			</div>
			<div>
				<label class="form-label" for="villeAdresse">Ville :</label> <input
					class="" type="text" id="villeAdresse" name="villeAdresse"
					value="<c:out value="${ client.adresse.ville }" />" /> <span
					class="erreur">${ erreurs.villeAdresse }</span>
			</div>
			<div>
				<label class="form-label" for="paysAdresse">Pays :</label> <input
					class="" type="text" id="paysAdresse" name="paysAdresse"
					value="<c:out value="${ client.adresse.pays }" />" /> <span
					class="erreur">${ erreurs.paysAdresse }</span>
			</div>
			<div>
				<label class="form-label" for="mailClient">Mail* :</label> <input
					class="" type="text" id="mailClient" name="mailClient"
					value="<c:out value="${ client.mail }" />" /> <span class="erreur">${ erreurs.mailClient }</span>
			</div>
			<div>
				<label class="form-label" for="nomSociete">Société :</label> <input
					class="" type="text" id="nomSociete" name="nomSociete"
					value="<c:out value="${ client.nom_societe }" />" /> <span
					class="erreur">${ erreurs.nom_societeClient }</span>
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