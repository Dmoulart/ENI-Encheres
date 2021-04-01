<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Page d'Accueil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/theme.css">
</head>

<body class ="Page">

    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
            <ul class="Menu">
            	<li class="Menu_item"><a class="link" href="./VenteServlet">Vente</a></li>
            	<li class="Menu_item"><a class="link" href="./EnchereServlet">Enchères</a></li>
            </ul>
            <ul class="Menu">
                <li class="Menu_item"><a href="./InscriptionServlet" class="link">S'inscrire</a></li>
                <li class="Menu_item"><a href="./ConnectionServlet" class="link">Se connecter</a></li>
            </ul>
        </nav>
    </div>

    <div class="Page-TitleContainer"><h1>Liste des enchères</h1></div>

    <form class="SearchContainer" method="POST" action="./IndexServlet">
        <div class ="Search_paramsContainer">
            <div class="Search_filter" id="filters">Filtres : </div>
            <div class="Search_barContainer">
                <input type="text" class="Search_bar"name="searchContent" value="${motsRecherches}">
                <div class="Search_categoryContainer">
                    <label>Catégorie</label>
	                    <select class="Search_select-category" name="selectCategorie">
	                    	<option id="Toutes" <c:if test="${empty categorieSelectionnee}">selected</c:if>>Toutes</option>
	                    		<c:forEach var="categorie" items="${categories}">
										<option 
											id="${categorie.libelle}" 
											<c:if test="${categorieSelectionnee eq categorie.libelle}">
											selected</c:if>>
											 ${categorie.libelle}
										 </option>
								</c:forEach>
	                    </select>
                    </div>
                </div>
            </div>
        </div>
        <div class="Search_buttonContainer">
            <button type="submit" class="Search_button">Rechercher</button>
        </div>
    </form>

    <section class="Articles-Section">
		<c:forEach var="article" items="${articles}">
			<div class="ArticleContainer">
	            <img src="" class="Article_image">
	            <div class="Article_informationsContainer">
	                <div class="Article_name">${article.nom}</div>
	                <div class="Article_properties prix">Prix : ${article.prixInitial}</div>
	                <div class="Article_properties finEnchere">Fin de l'enchère : ${article.finEncheres.toString()}</div>
	                <div class="Article_properties retrait">Retrait : ${article.vendeur.rue}</div>
	                <div class="Article_properties vendeur">Vendeur:  ${article.vendeur.nom}</div>
	            </div>
	        </div>
		</c:forEach>
	</section>
</body>
</html>