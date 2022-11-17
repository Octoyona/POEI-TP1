<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Paiement</title>
</head>
<body>
	
	<c:import url="/WEB-INF/menu.jsp" />
	
	<div class="view">

	<!-- Il faut qu'on récupère contient  -->
	<!-- On n'a pas encore mis en place la gestion d'erreur pour le paiement -->
	<!-- Afficher le prix total -->
	
		<form method="post" action="<c:url value="/paiement"><c:param name="idPanier" value="${ panier.id }" /></c:url>">
			
			<!-- Il manque  -->
			<!-- Infos du client -->
			<!-- Prix à payer -->
			
			<div>
				<label class="form-label" for="numeroCarte">Numéro de carte : </label>
				<input class="" type="text" id="numeroCarte" name="numeroCarte" value="<c:out value="${ paiement.numero_carte }" />" />
				<span class="erreur">${ erreurs['numeroCartePaiement'] }</span>
			</div>
			<div>
				<label class="form-label" for="codeConfidentiel">Code confidentiel : </label>
				<input class="" type="text" id="codeConfidentiel" name="codeConfidentiel" value="<c:out value="${ paiement.code_confidentiel }" />" />
				<span class="erreur">${ erreurs['codeConfidentielPaiement'] }</span>
			</div>
			<div>
				<label class="form-label" for="banque">Banque : </label>
				<input class="" type="text" id="banque" name="banque" value="<c:out value="${ paiement.banque }" />" />
				<span class="erreur">${ erreurs['banquePaiement'] }</span>
			</div>
			
			<input type="submit" value="Envoyer" class="sansLabel" /> 
			<input type="reset" value="Remettre à zéro" class="sansLabel" />
				
		</form>
		<div>
			<a href="<c:url value="/"></c:url>">Accueil </a>
		</div>
	</div>
	
</body>
</html>