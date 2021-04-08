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
    
	<div class="Page-TitleContainer"><h1>Mon profil</h1></div>
	
	 <c:if test="${!empty errors}">
      	<c:forEach var="error" items="${errors}">
      		<div class="Alert-error">${error}</div>
      	</c:forEach>
     </c:if> 
	
	
	<div class="ProfilPage">
		<div class="ProfilFormContainer Card">
			<form class="ProfilForm" action="./ProfilServlet" method="post">
				<div class="ProfilFields">
					<div class="ProfilField">
						<span class="TypeInfo"> Pseudo  </span>  <span class="PorfilInfo">${utilisateurEnSession.pseudo}</span>
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">Nom  </span>  <span class="PorfilInfo"> ${utilisateurEnSession.nom}</span>
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">Prénom  </span> <span class="PorfilInfo">${utilisateurEnSession.prenom}</span>
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">Email  </span> <span class="PorfilInfo">${utilisateurEnSession.email}</span> 
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">éléphone   </span> <span class="PorfilInfo">${utilisateurEnSession.telephone}</span> 
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">Rue  </span> <span class="PorfilInfo"> ${utilisateurEnSession.rue}</span> 
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo">Code Postal  </span> <span class="PorfilInfo"> ${utilisateurEnSession.codePostal}</span> 
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo"> Ville  </span> <span class="PorfilInfo"> ${utilisateurEnSession.ville}</span> 
					</div> 
					<div class="ProfilField">
						<span class="TypeInfo"> Crédit  </span>  <span class="PorfilInfo">${utilisateurEnSession.credit}</span> 
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>