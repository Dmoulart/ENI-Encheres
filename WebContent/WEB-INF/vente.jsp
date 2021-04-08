<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="CSS/general.css">
<link rel="stylesheet" href="CSS/theme.css">
<link rel="stylesheet" href="CSS/vente.css">
</head>
<body class="Page">

	<div class="NavbarContainer">
		<nav class="Navbar">
			<div class="Logo">
				<a class="link" href="./IndexServlet">ENI-Enchères</a>
			</div>
		</nav>
	</div>

	<div class="VentePage">
		<div class="VenteIntroPage">
			<div class="VenteIntroFormContainer">
				<div class="Page-TitleContainer">
					<h1>Nouvelle vente</h1>
				</div>
			</div>
		</div>

		<div class="VenteFormContainer Card">

			<form class="VenteForm" action="./VenteServlet" method="post">

				<div class="VenteFields">
					<c:if test="${!empty errors}">
						<c:forEach var="error" items="${errors}">
							<div class="Alert-error">${error}</div>
						</c:forEach>
					</c:if>
					<div class="VenteField">
						<label for="nomArticle">Article :</label> <input type="text"
							placeholder="Nom de l'Article..." id="nomArticle"
							name="nomArticle" required maxlength="30">
					</div>

					<div class="VenteField">
						<label for="descriptionArticle">Description :</label> <input
							type="text" placeholder="Description de l'Article..."
							id="descriptionArticle" name="descriptionArticle" maxlength="300"
							required>
					</div>

					<div class=" VenteField ">
						<label for="categorie">Categorie :</label> <select
							name="categorie" id="categorie" required>
							<option value=""></option>
							<option value=1>Informatique</option>
							<option value=2>Ammeublement</option>
							<option value=3>Vêtement</option>
							<option value=4>Sport & Loisir</option>
						</select>
					</div>
					<div class=" VenteField ">
						<label>Image :</label> <img id="output" width="300" />
						<div class="ButtonContainer " style="width: 55%;">
							<button type="button" class="UploadImageButton">
								<label for="file" style="cursor: pointer;">Uploader</label>
							</button>
							<input type="file" accept="image/*" name="image" id="file"
								onchange="loadFile(event)" style="visibility: hidden;" required>

							<script>
								var loadFile = function(event) {
									var image = document
											.getElementById('output');
									image.src = URL
											.createObjectURL(event.target.files[0]);
								};
							</script>
						</div>
					</div>


					<div class="VenteField">
						<label for="nomArticle">Mise à prix</label> <input class=""
							type="number" value="30" id="prixEnchere" name="prixEnchere"
							required>
					</div>

					<div class="VenteField">
						<label for="dateDebutArticle">Début de l'enchère</label> <input
							type="date" id="debutEnchere" name="debutEnchere"
							value="${java.time.LocalDate.now()}"
							min="${java.time.LocalDate.now()}" max="2999-12-31" required>
					</div>

					<div class="VenteField">
						<label for="dateFinArticle">Fin de l'enchère</label> <input
							type="date" id="finEnchere" name="finEnchere"
							value="${java.time.LocalDate.now()+1}"
							min="${java.time.LocalDate.now()+1}" max="2999-12-31" required>
					</div>


					<h2>Adresse de retrait</h2>

					<div class="VenteField">
						<label for="nomArticle">Rue</label> <input type="text"
							value="${utilisateurEnSession.rue}" id="rueRetrait"
							name="rueRetrait" maxlength="30" required>
					</div>
					<div class="VenteField">
						<label for="descriptionArticle">Code Postal</label> <input
							type="text" value="${utilisateurEnSession.codePostal}"
							id="CodePostalRetrait" name="CodePostalRetrait" maxlength="15"
							required>
					</div>
					<div class="VenteField">
						<label for="descriptionArticle">Ville</label> <input type="text"
							value="${utilisateurEnSession.ville}" maxlength="30"
							id="villeRetrait" name="villeRetrait" required>
					</div>

				</div>

				<div class="ButtonContainer ">

					<button type="submit" id="venteValider" class="VenteValider">Mise
						en vente</button>
				</div>

			</form>
		</div>
	</div>
</body>
</body>
</html>