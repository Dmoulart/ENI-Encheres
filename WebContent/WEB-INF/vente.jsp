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
					<label for="nomArticle">Article :</label>
						<input type="text" placeholder="Nom de l'Article..." id="nomArticle" name="nomArticle" required>					
					<label for="descriptionArticle">Description :</label>
						<input type="text" placeholder="Description de l'Article..." id="descriptionArticle" name="descriptionArticle" required>	
              
               		<label for="categorie">Categorie :</label>
					<select name="categorie" id="categorie" required>
					    <option value="">--Catégorie correspondant à l'article--</option>
					    <option value="informatique">Informatique</option>
					    <option value="ammeublement">Ammeublement</option>
					    <option value="vetement">Vêtement</option>
					    <option value="sport&loisir">Sport & Loisir</option>
					</select>

					<div class="form-group">
						<label for="exampleInputFile">Photo de l'article :</label>
							<p><input type="file"  accept="image/*" name="image" id="file"  onchange="loadFile(event)" style="display: none;" required></p>
							<p><label for="file" style="cursor: pointer;">Importer une image</label></p>
							<p><img id="output" width="300" /></p>
						<script> var loadFile = function(event) { var image = document.getElementById('output'); image.src = URL.createObjectURL(event.target.files[0]); };</script>	
					</div>
					
					<label for="nomArticle">Mise à prix</label>
						<div class="form-group row">
							<div class="col-10">
								<input class="form-control" type="number" value="42" id="prixEnchere" name="prixEnchere"required>
							</div>
						</div>
						
					<label for="dateDebutArticle">Début de l'enchère</label>
						<input type="date" id="debutEnchere" name="debutEnchere" value="2021-04-01" min="2021-04-01" max="2999-12-31" required>
					<label for="dateFinArticle">Fin de l'enchère</label>	
						<input type="date" id="finEnchere" name="finEnchere" value="2021-04-01" min="2021-04-01" max="2999-12-31" required>
				
				<div class="Page-TitleContainer"><h2>Adresse de retrait</h2></div>
					<label for="nomArticle">Rue</label>
						<input type="text" placeholder="${utilisateurEnSession.rue}" id="rueRetrait" name="rueRetrait" required>					
					<label for="descriptionArticle">Code Postal</label>
						<input type="text" placeholder="${utilisateurEnSession.codePostal}" id="CodePostalRetrait" name="CodePostalRetrait" required>
					<label for="descriptionArticle">Ville</label>
						<input type="text" placeholder="${utilisateurEnSession.ville}" id="villeRetrait" name="villeRetrait" required>		
                </div>
               	<div class="InscriptionValidation">
					<div class="InscriptionButtonContainer">
						<p>Validation des informations pour la mise en vente :</p>
							<button type="submit" id="venteValider" class="venteValider">Valider</button>
					</div>
				</div>  					
			</form>
		</div>
	</div>
</body> 
</body>
</html>