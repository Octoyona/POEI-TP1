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


			<div>
				<label class="form-label" for="nomClient"> :</label> <input class=""
					type="text" id="nomClient" name="nomClient"
					value="<c:out value="${ client.nom }" />" /> <span class="erreur">${ erreurs['nomClient'] }</span>
			</div>
			<div>
				<label class="form-label" for="prenomClient"> :</label> <input
					class="" type="text" id="prenomClient" name="prenomClient"
					value="<c:out value="${ client.prenom }" />" /> <span
					class="erreur">${ erreurs['prenomClient'] }</span>
			</div>
			<div>
				<label class="form-label" for="telephoneClient"> :</label> <input
					class="" type="text" id="telephoneClient" name="telephoneClient"
					value="<c:out value="${ client.telephone }" />" /> <span
					class="erreur">${ erreurs['telephoneClient'] }</span>
			</div>
			<div>
				<label class="form-label" for="select">Choisir une adresse</label> <select
					class="form-select" name="select" id="select">
					<c:forEach items="${ adresses }" var="adresse">
						<option ${ adresse.id == client.id_adresse ? "selected" : "" }
							value=${ adresse.id }>${adresse.rue}$</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label class="form-label" for="mailClient"> :</label> <input
					class="" type="text" id="mailClient" name="mailClient"
					value="<c:out value="${ client.mail }" />" /> <span class="erreur">${ erreurs['emailClient'] }</span>
			</div>
			<div>
				<label class="form-label" for="nomSociete"> :</label> <input
					class="" type="text" id="nomSociete" name="nomSociete"
					value="<c:out value="${ client.nomsociete }" />" /> <span
					class="erreur">${ erreurs['nomSociete'] }</span>
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