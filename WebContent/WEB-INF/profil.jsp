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
    <link rel="stylesheet" href="CSS/profil.css">
</head>

<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
            <ul class="Menu">
				<li class="Menu_item"><a href="./ModifierProfilServlet" class="link">Modifier mon profil</a></li>
			</ul>
        </nav>
    </div>
    
	<div class="Page-TitleContainer"><h1>Votre profil sur ENI-Enchères</h1></div>
	
	 <c:if test="${!empty errors}">
      	<c:forEach var="error" items="${errors}">
      		<div class="Alert-error">${error}</div>
      	</c:forEach>
     </c:if> 
	
	
	<div class="ProfilPage">
		<div class="ProfilFormContainer">
			<form class="ProfilForm" action="./ProfilServlet" method="post">
				<div class="ProfilFields">
					<div class="Page-TitleContainer"><h2>Bonjour ${utilisateurEnSession.prenom} ${utilisateur.nom}</h2></div>
					<label for="pseudoUtilisateur">Pseudo : ${utilisateurEnSession.pseudo}</label>
					<label for="nomUtilisateur">Nom : ${utilisateurEnSession.nom}</label>
					<label for="prenomUtilisateur">Prénom : ${utilisateurEnSession.prenom}</label>
					<label for="emailUtilisateur">Email : ${utilisateurEnSession.email}</label>
					<label for="telephoneUtilisateur">Téléphone : ${utilisateurEnSession.telephone}</label>
					<label for="rueUtilisateur">Rue : ${utilisateurEnSession.rue}</label>
					<label for="codePostalUtilisateur">Code Postal : ${utilisateurEnSession.codePostal}</label>
					<label for="villeUtilisateur">Ville : ${utilisateurEnSession.ville}</label>
					<label for="creditUtilisateur">Crédit : ${utilisateurEnSession.credit}</label>
				</div>
			</form>
		</div>
	</div>

</body>
</html>