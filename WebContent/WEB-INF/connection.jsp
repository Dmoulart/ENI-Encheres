<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>ENI-ENCHERES Connection</title>
</head>
<body>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400;500;600;700&family=Quicksand:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="CSS/general.css">
    <link rel="stylesheet" href="CSS/connection.css">
    <link rel="stylesheet" href="CSS/theme.css">
</head>

<body class ="Page">
    <div class="NavbarContainer">
        <nav class="Navbar">
            <div class="Logo"><a class="link" href="./IndexServlet">ENI-Enchères</a></div>
        </nav>
    </div>
    
      <c:if test="${!empty errors}">
      	<c:forEach var="error" items="${errors}">
      		<div class="Alert-error">${error}</div>
      	</c:forEach>
     </c:if> 
     
    <div class="ConnexionPage">
        <div class="ConnexionFormContainer">
            <form class="ConnexionForm" action="./ConnectionServlet" method="post">
                <div class="ConnexionFields">
                    <label for="nomUtilisateur">Identifiant</label>
                    <input type="text" placeholder="Votre login ou adresse mail.." id="identifiantUtilisateur" required name="identifiantUtilisateur">
                    <label for="motDePasse">Mot de passe</label>
                    <input type="text" placeholder="Votre mot de passe..." id="motDePasse" required name= "motDePasse">
                </div>
                <div class="ConnexionValidation">
                    <div class="ConnexionButtonContainer">
                        <button class="ConnexionButton">Connexion</button>
                    </div>
                    <div class="ConnexionOptions">
                        <div class="CheckRememberConnexion">
                            <input type="checkbox" class="option" id="rememberPassword">
                            <label for="rememberPassword">Se souvenir de moi</label>
                        </div>
                        <a class="option" href="./MotDePasseOublieServlet">Mot de passe oublié</a>
                    </div>
                </div>
            </form>
            <form action="./InscriptionServlet" class="SubscribeContainer">
            <button class="Subscribe" id="creerCompte">Créer un compte</button></form>
        </div>
    </div>
</body>
</html>