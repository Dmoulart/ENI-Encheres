<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<title>ENI-ENCHERES Article</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="CSS/general.css">
<link rel="stylesheet" href="CSS/article.css">
<link rel="stylesheet" href="CSS/theme.css">
</head>

<body class="Page">

	<div class="NavbarContainer">
		<nav class="Navbar">
			<div class="Logo">
				<a class="link" href="./IndexServlet">ENI-Enchères</a>
			</div>
		</nav>
	</div>
	<h1>Détail vente</h1>
	<div class="ArticleContainer">


		<img src="./article-img-test.jpg" class="Article_image">

		<div class="Article-InformationsContainer">

			<div class="Article_name">Nom : ${article.nom}</div>
			<div class="Article_properties description">Description :
				${article.description}</div>
			<div class="Article_properties categorie">Catégorie :
				${article.categorie.libelle}</div>
			<div class="Article_properties prix">Mise à prix :
				${article.prixInitial}</div>
				<div class="Article_properties prix">Prix de vente :
				${article.prixVente}</div>
			<div class="Article_properties finEnchere">Fin de l'enchère :
				${article.finEncheres.toString()}</div>
			<div class="Article_properties retrait">Retrait :
				${article.vendeur.rue}</div>
			<div class="Article_properties vendeur">Vendeur:
				${article.vendeur.nom}</div>
			<form action="./ArticleServlet" method="POST">
				<div class="EncherirContainer">
				
					<c:if test="${!empty errors}">
						<c:forEach var="error" items="${errors}">
							<div class="Alert-error">${error}</div>
						</c:forEach>
					</c:if>
					
					Ma proposition : <input type="number" id="montantEnchere" name="montantEnchere"
						value="${article.prixVente +1 }" min="${article.prixVente +1}"
						max="${2147483647}" required> <input
						type="hidden" name="articleId" id="articleId" value="${article.id}">
					<button type="submit" name="encherir">Enchérir</button>
				</div>
			</form>
		</div>
	</div>
	<footer></footer>
</body>
</html>