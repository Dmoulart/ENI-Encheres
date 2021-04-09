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
    <link rel="stylesheet" href="CSS/admin.css">
</head>

<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
        </nav>
    </div>
	<div class="ProfilPage">
	<div class="Page-TitleContainer"><h2>Profil du vendeur ${utilisateur.prenom} ${utilisateur.nom}</h2></div>
		<div class="ProfilFormContainer">
		
			<div class="ProfilFields">
			<c:choose>
				<c:when test="${utilisateurEnSession!=null && utilisateurEnSession.administrateur==true}">  
					<label for="pseudoUtilisateur">Pseudo : ${utilisateur.pseudo}</label>
					<label for="nomUtilisateur">Nom : ${utilisateur.nom}</label>
					<label for="prenomUtilisateur">Prénom : ${utilisateur.prenom}</label>
					<label for="emailUtilisateur">Email : ${utilisateur.email}</label>
					<label for="telephoneUtilisateur">Téléphone : ${utilisateur.telephone}</label>
					<label for="rueUtilisateur">Rue : ${utilisateur.rue}</label>
					<label for="codePostalUtilisateur">Code Postal : ${utilisateur.codePostal}</label>
					<label for="villeUtilisateur">Ville : ${utilisateur.ville}</label>
					<a href="${pageContext.request.contextPath}/AdminServletSuppr?utilisateurId=${utilisateur.id}" class="lienAdmin">
						<button type="submit" id="suppresionProfilAdmin" class="SupprimerAdminButton">Supprimer le compte</button>
					</a>
				</div>
		        </c:when>
		        <c:otherwise>  
					<label for="pseudoUtilisateur">Pseudo : ${utilisateur.pseudo}</label>
					<label for="nomUtilisateur">Nom : ${utilisateur.nom}</label>
					<label for="prenomUtilisateur">Prénom : ${utilisateur.prenom}</label>
					<label for="emailUtilisateur">Email : ${utilisateur.email}</label>
					<label for="telephoneUtilisateur">Téléphone : ${utilisateur.telephone}</label>
					<label for="rueUtilisateur">Rue : ${utilisateur.rue}</label>
					<label for="codePostalUtilisateur">Code Postal : ${utilisateur.codePostal}</label>
					<label for="villeUtilisateur">Ville : ${utilisateur.ville}</label>
		        </c:otherwise>
		    </c:choose>
			</div>
		</div>
	</div>

</body>
</html>