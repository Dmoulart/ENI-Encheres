<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <link rel="stylesheet" href="CSS/modifier_profil.css">
       
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
	
	 <c:if test="${!empty errors}">
      	<c:forEach var="error" items="${errors}">
      		<div class="Alert-error">${error}</div>
      	</c:forEach>
     </c:if> 
	
	<div class="ProfilPage">
		<div class="ProfilFormContainer">
			<form class="ProfilForm Card" action="./ModifierProfilServlet" method="post">
				<div class="ProfilFields">
					
					<label for="pseudoUtilisateur">Pseudo</label>
						<input type="text" value="${utilisateurEnSession.pseudo}" id="pseudoUtilisateur" name="pseudoUtilisateur">
					<label for="nomUtilisateur">Nom</label>
						<input type="text" value="${utilisateurEnSession.nom}" id="nomUtilisateur" name="nomUtilisateur">
					<label for="prenomUtilisateur">Prénom</label>
						<input type="text" value="${utilisateurEnSession.prenom}" id="prenomUtilisateur" name="prenomUtilisateur">
					<label for="emailUtilisateur">Email</label>
						<input type="text" value="${utilisateurEnSession.email}" id="emailUtilisateur" name="emailUtilisateur">
					<label for="telephoneUtilisateur">Téléphone</label>
						<input type="text" value="${utilisateurEnSession.telephone}" id="telephoneUtilisateur" name="telephoneUtilisateur">
					<label for="rueUtilisateur">Rue</label>
						<input type="text" value="${utilisateurEnSession.rue}" id="rueUtilisateur" name="rueUtilisateur">
					<label for="codePostalUtilisateur">Code Postal</label>
						<input type="text" value="${utilisateurEnSession.codePostal}" id="codePostalUtilisateur" name="codePostalUtilisateur">
					<label for="villeUtilisateur">Ville</label>
						<input type="text" value="${utilisateurEnSession.ville}" id="villeUtilisateur" name="villeUtilisateur">
					<label for="mdpUtilisateur">Nouveau mot de passe</label>
						<input type="password" value="${utilisateurEnSession.motDePasse}" id="mdpUtilisateur" name="mdpUtilisateur">
					<label for="mdpUtilisateur">Confirmation du mot de passe</label>
						<input type="password" value="${utilisateurEnSession.motDePasse}" id="mdpConfUtilisateur" name="mdpConfUtilisateur">
				</div>
              	<div class="ProfilButtonContainer">
                   	<button type="submit" id="modificationProfilValider" class="ModifierProfilButton">Modifier le compte</button>
               	</div>
            </form> 
		</div>    

	</div>
		<div class="form-popup" id="myForm">
		  <form  class="ProfilFormPopup" action="./SupprimerProfilServlet" method="get">
		    <h3>Confirmer la suppression du compte : </h3>
			<button type="submit" class="PopupProfilButton">Supprimer</button>
		    <button type="button" class="PopupProfilButton" onclick="closeForm()">Annuler</button>
		  </form>
		</div>
	<div class="ProfilButtonContainer">	
		<button class="open-button" onclick="openForm()">Supprimer le compte</button>
	</div>
		<div class="form-popup" id="myForm">
			<form action="/action_page.php" class="form-container">
			</form>
		</div>
        <script>
			function openForm() {
			  document.getElementById("myForm").style.display = "block";
			}	
			function closeForm() {
			  document.getElementById("myForm").style.display = "none";
			}
		</script>    

</body>
</html>