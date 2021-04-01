<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<title>Title</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="CSS/general.css">
<link rel="stylesheet" href="CSS/mot_de_passe_oublie.css">
<link rel="stylesheet" href="CSS/theme.css">
</head>

<body class="Page">

	<div class="NavbarContainer">
		<nav class="Navbar">
			<div class="Logo">
				<a class="link" href="./IndexServlet">ENI-Enchères</a>
			</div>
			<ul class="Menu">
				<li class="Menu_item"><a href="#" class="link">S'inscrire</a></li>
				<li class="Menu_item"><a href="./ConnectionServlet"
					class="link">Se connecter</a></li>
			</ul>
		</nav>
	</div>

	<div class="Page-TitleContainer">
		<h1>Mot de passe oublie</h1>
	</div>


	<div class="mdpOubliePage">
		<div class="mdpOublieFormContainer">
			<form class="mdpOublieForm" action="#" method="post">
				<div class="mdpOublieFields">
					<label for="nomUtilisateur">Identifiant</label> <input type="text"
						placeholder="Votre login ou adresse mail.."
						id="identifiantUtilisateur" required name="identifiantUtilisateur">
					<label for="motDePasse">Nouveau mot de passe</label> <input
						type="text" placeholder="Veuillez créer un nouveau mot de passe"
						id="nvxMotDePasse" required name="nvxMotDePasse">
				</div>
				<div class="mdpOublieValidation">
					<div class="mdpOublieButtonContainer">
						<button class="mdpOublieButton">Confirmer</button>
					</div>
				</div>
			</form>
		</div>
	</div>





</body>
</html>