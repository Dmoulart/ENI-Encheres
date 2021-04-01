<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Mise en vente</title>
</head>
<body>
<head>
    <meta charset="UTF-8">
    <title>Vente</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/theme.css">
    <link rel="stylesheet" href="CSS/vente.css">
     <link rel="stylesheet" href="CSS/inscription.css">
</head>
<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
        </nav>
    </div>
    
	<div class="Page-TitleContainer"><h1>Mise en vente sur ENI-Enchères</h1></div>
	
	<div class="InscriptionPage">
		<div class="InscriptionFormContainer">
			<form class="InscriptionForm" action="./VenteServlet" method="post">
				<div class="InscriptionFields">
					<label for="nomArticle">Article</label>
						<input type="text" placeholder="Nom de l'Article..." id="nomArticle" name="nomArticle" required>					
					<label for="descriptionArticle">Description</label>
						<input type="text" placeholder="Description de l'Article..." id="descriptionArticle" name="descriptionArticle" required>	
					<form class="form-inline">
						<label class="mr-sm-2" for="inlineFormCustomSelect">Catégorie</label>
							<select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="categorieEnchere" name="categorieEnchere">
								<option selected>Choix de la catégorie...</option>
								<option value="1">Informatique</option>
								<option value="2">Ameublement</option>
								<option value="3">Vêtement</option>
								<option value="3">Sport et Loisir</option>
							</select>
					</form>
					<div class="form-group">
						<label for="exampleInputFile">Photo de l'article</label>
						<input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
						<small id="photoEnchere" class="form-text text-muted">Une seule photo par article s'il vous plait, merci.</small>
						<img src="file" class="img-thumbnail">
					</div>
					
					<label for="nomArticle">Mise à prix</label>
						<div class="form-group row">
							<div class="col-10">
								<input class="form-control" type="number" value="42" id="prixEnchere" name="prixEnchere"required>
							</div>
						</div>
					<label for="nomArticle">Début de l'enchère</label>
					<div class="form-group row">
						<div class="col-10">
							<input class="form-control" type="date" value="2021-04-01" id="debutEnchere" name="debutEnchere" required>
						</div>
						</div>
					<label for="nomArticle">Fin de l'enchère</label>	
					<div class="form-group row">
						<div class="col-10">
							<input class="form-control" type="date" value="2021-04-01" id="finEnchere" name="finEnchere" required>
						</div>
					</div>
				<div class="Page-TitleContainer"><h2>Adresse de retrait</h2></div>
                
					<label for="nomArticle">Rue</label>
						<input type="text" placeholder="DEFAULT RUE UTILISATEUR..." id="rueRetrait" name="rueRetrait" required>					
					<label for="descriptionArticle">Code Postal</label>
						<input type="text" placeholder="DEFAULT CODE POSTAL UTILISATEUR..." id="CodePostalRetrait" name="CodePostalRetrait" required>
					<label for="descriptionArticle">Ville</label>
						<input type="text" placeholder="DEFAULT VILLE UTILISATEUR..." id="villeRetrait" name="villeRetrait" required>		
                </div>  					
			</form>
		</div>
	</div>
</body> 
</body>
</html>