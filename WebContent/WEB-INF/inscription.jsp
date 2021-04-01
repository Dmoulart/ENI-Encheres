<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Inscription</title>
</head>
<body>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/theme.css">
    <link rel="stylesheet" href="CSS/inscription.css">
</head>

<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
        </nav>
    </div>
    
	<div class="Page-TitleContainer"><h1>Inscription sur ENI-Enchères</h1></div>
	
	<div class="InscriptionPage">
		<div class="InscriptionFormContainer">
			<form class="InscriptionForm" action="./InscriptionServlet" method="post">
				<div class="InscriptionFields">
					<label for="pseudoUtilisateur">Pseudo</label>
						<input type="text" placeholder="Votre pseudonyme..." id="pseudoUtilisateur" name="pseudoUtilisateur" required>
					<label for="nomUtilisateur">Nom</label>
						<input type="text" placeholder="Votre nom..." id="nomUtilisateur" name="nomUtilisateur" required>
					<label for="prenomUtilisateur">Prénom</label>
						<input type="text" placeholder="Votre prénom..." id="prenomUtilisateur" name="prenomUtilisateur" required>
					<label for="emailUtilisateur">Email</label>
						<input type="text" placeholder="Votre email..." id="emailUtilisateur" name="emailUtilisateur" required>
					<label for="telephoneUtilisateur">Téléphone</label>
						<input type="text" placeholder="Votre téléphone..." id="teleponeUtilisateur" name="teleponeUtilisateur" required>
					<label for="rueUtilisateur">Rue</label>
						<input type="text" placeholder="Votre adresse..." id="rueUtilisateur" name="rueUtilisateur" required>
					<label for="codePostalUtilisateur">Code Postal</label>
						<input type="text" placeholder="Votre code postal..." id="codePostalUtilisateur" name="codePostalUtilisateur"required>
					<label for="villeUtilisateur">Ville</label>
						<input type="text" placeholder="Votre ville..." id="villeUtilisateur" name="villeUtilisateur" required>
					<label for="mdpUtilisateur">Mot de passe</label>
						<input type="text" placeholder="Votre mot de passe..." id="mdpUtilisateur" name="mdpUtilisateur" required>
					<label for="mdpUtilisateur">Confirmation</label>
						<input type="text" placeholder="La confirmation de votre mot de passe..." name="mdpConfUtilisateur" required>
				</div>
                <div class="InscriptionValidation">
                    <div class="InscriptionButtonContainer">
                    	<p> Validation des informations pour la création du compte :  </p>
                        <button type="submit" id="inscriptionValider" class="InscriptionButton">Créer votre compte</button>
                    </div>
                </div>  					
			</form>
		</div>
	</div> 
</body>     
</body>
</html>