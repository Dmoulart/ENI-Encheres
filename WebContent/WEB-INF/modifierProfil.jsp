<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Mon profil</title>
</head>
<body>
<head>
    <meta charset="UTF-8">
    <title>Mon profil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="stylesheet" href="CSS/theme.css">
    <link rel="stylesheet" href="CSS/profil.css">
</head>

<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
            <ul class="Menu">
				<li class="Menu_item"><a href="./ProfilServlet" class="link">Mon profil</a></li>
			</ul>
        </nav>
    </div>
    
	<div class="Page-TitleContainer"><h1>Modification de votre profil sur ENI-Enchères</h1></div>
	
	<div class="ProfilPage">
		<div class="ProfilFormContainer">
			<form class="ProfilForm" action="./ModifierProfilServlet" method="post">
				<div class="ProfilFields">
					<div class="Page-TitleContainer"><h2>Bonjour ${utilisateurEnSession.pseudo}</h2></div>
					<label for="pseudoUtilisateur">Pseudo</label>
						<input type="text" placeholder="${utilisateurEnSession.pseudo}" id="pseudoUtilisateur" name="pseudoUtilisateur">
					<label for="nomUtilisateur">Nom</label>
						<input type="text" placeholder="${utilisateurEnSession.nom}" id="nomUtilisateur" name="nomUtilisateur">
					<label for="prenomUtilisateur">Prénom</label>
						<input type="text" placeholder="${utilisateurEnSession.prenom}" id="prenomUtilisateur" name="prenomUtilisateur">
					<label for="emailUtilisateur">Email</label>
						<input type="text" placeholder="${utilisateurEnSession.email}" id="emailUtilisateur" name="emailUtilisateur">
					<label for="telephoneUtilisateur">Téléphone</label>
						<input type="text" placeholder="${utilisateurEnSession.telephone}" id="teleponeUtilisateur" name="teleponeUtilisateur">
					<label for="rueUtilisateur">Rue</label>
						<input type="text" placeholder="${utilisateurEnSession.rue}" id="rueUtilisateur" name="rueUtilisateur">
					<label for="codePostalUtilisateur">Code Postal</label>
						<input type="text" placeholder="${utilisateurEnSession.codePostal}" id="codePostalUtilisateur" name="codePostalUtilisateur">
					<label for="villeUtilisateur">Ville</label>
						<input type="text" placeholder="${utilisateurEnSession.ville}" id="villeUtilisateur" name="villeUtilisateur">
					<label for="mdpUtilisateur">Mot de passe</label>
						<input type="text" placeholder="Votre mot de passe..." id="mdpUtilisateur" name="mdpUtilisateur">
					<label for="mdpUtilisateur">Confirmation</label>
						<input type="text" placeholder="La confirmation de votre mot de passe..." name="mdpConfUtilisateur">
				</div>
				<div class="ProfilValidation">
                	<div class="ProfilButtonContainer">
                		<p> Validation des informations pour la modification du compte :  </p>
                        <button type="submit" id="modificationProfilValider" class="modificationProfilValider">Modifier le compte</button>
                    </div>
                    <div class="ProfilButtonContainer">
                		<p> Suppression du compte :  </p>
                        <button id="supprimerProfilPopup" class="supprimerProfilPopup">Supprimer le compte</button>
                        <div id="overlayPopup" class="overlayPopup">
                        	<div id="popup" class="popup">
                        		<h2>VOULEZ VRAIMENT SUPPRIMER VOTRE COMPTE ? <span id="btnClose" class="btnClose">&times;</span></h2>
                        		<p>Cette action est irréversible !</p>
                        		<button type="submit" id="supprimerProfilValider" class="supprimerProfilValider">Supprimer le compte</button>
                        	</div>
                        </div>
                        <script type="text/javascript.js"></script>
                    </div>
                </div>  
			</form>
		</div>
	</div>

</body>
</html>