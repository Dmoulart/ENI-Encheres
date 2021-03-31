<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <div class="ConnexionPage">
        <div class="ConnexionFormContainer">
            <form class="ConnexionForm">
                <div class="ConnexionFields">
                    <label for="nomUtilisateur">Identifiant</label>
                    <input type="text" placeholder="Votre login ou adresse mail.." id="nomUtilisateur" required>
                    <label for="motDePasse">Mot de passe</label>
                    <input type="text" placeholder="Votre mot de passe..." id="motDePasse" required>
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
                        <a class="option" href="#">Mot de passe oublié</a>
                    </div>
                </div>
            </form>
            <button class="Subscribe" id="creerCompte">Créer un compte</button>
        </div>
    </div>

</body>
</html>
</body>
</html>