<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
                <li class="Menu_item"><a href="#" class="link">S'inscrire</a></li>
                <li class="Menu_item"><a href="./ConnectionServlet" class="link">Se connecter</a></li>
            </ul>
        </nav>
    </div>

    <div class="Page-TitleContainer"><h1>Liste des enchères</h1></div>

    <form class="SearchContainer">
        <div class ="Search_paramsContainer">
            <div class="Search_filter">Filtres : </div>
            <div class="Search_barContainer">
                <input type="text" class="Search_bar">
                <div class="Search_categoryContainer">
                    <label>Catégorie</label>
                    <select class="Search_select-category">
                        <option id="informatique">Informatique</option>
                        <option id="ameublement">Ameublement</option>
                        <option id="vetements">Vêtements</option>
                        <option id="sport&loisirs">Sports&Loisirs</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="Search_buttonContainer">
            <button type="submit" class="Search_button">Rechercher</button>
        </div>
    </form>

    <section class="Articles-Section">

        <div class="ArticleContainer">
            <img src="article-img-test.jpg" class="Article_image">
            <div class="Article_informationsContainer">
                <div class="Article_name">PC Gamer pour travailler</div>
                <div class="Article_properties prix">Prix : 210</div>
                <div class="Article_properties finEnchere">Fin de l'enchère : 10/08/2018</div>
                <div class="Article_properties retrait">Retrait : 10 allée des Alouettes 44800 Saint Herblain</div>
                <div class="Article_properties vendeur">Vendeur: jojo44</div>
            </div>
        </div>
        <div class="ArticleContainer">
            <img src="article-img-test.jpg" class="Article_image">
            <div class="Article_informationsContainer">
                <div class="Article_name">PC Gamer pour travailler</div>
                <div class="Article_properties prix">Prix : 210</div>
                <div class="Article_properties finEnchere">Fin de l'enchère : 10/08/2018</div>
                <div class="Article_properties retrait">Retrait : 10 allée des Alouettes 44800 Saint Herblain</div>
                <div class="Article_properties vendeur">Vendeur: jojo44</div>
            </div>
        </div>
        <div class="ArticleContainer">
            <img src="article-img-test.jpg" class="Article_image">
            <div class="Article_informationsContainer">
                <div class="Article_name">PC Gamer pour travailler</div>
                <div class="Article_properties prix">Prix : 210</div>
                <div class="Article_properties finEnchere">Fin de l'enchère : 10/08/2018</div>
                <div class="Article_properties retrait">Retrait : 10 allée des Alouettes 44800 Saint Herblain</div>
                <div class="Article_properties vendeur">Vendeur: jojo44</div>
            </div>
        </div>

    </section>

</body>
</html>