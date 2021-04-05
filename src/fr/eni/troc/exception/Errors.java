package fr.eni.troc.exception;

public class Errors {
    
 /* ______      _   _____                        
    |  _  \    | | |  ___|                       
    | | | |__ _| | | |__ _ __ _ __ ___  _ __ ___ 
    | | | / _` | | |  __| '__| '__/ _ \| '__/ __|
    | |/ / (_| | | | |__| |  | | | (_) | |  \__ \
    |___/ \__,_|_| \____/_|  |_|  \___/|_|  |___/*/
                                                 
                                                 
    final static public String INSERT = "Echec de l'insertion des données";
    
    final static public String UPDATE = "Echec de la mise à jour des données";
    
    final static public String DELETE = "Echec de la suppression des données";
    
    final static public String SELECT_ALL = "Echec de la selection de l'ensemble des donn�es de la table";
    
    final static public String SELECT_BY_ID = "Echec de la selection par l'id d'un enregistrement de la table";
    
    final static public String SELECT_BY_VENDEUR = "Echec de la selection d'un article par son vendeur";
    
    final static public String NO_DATA_FOUND = "Aucune donnée correspondant à ces critères de selection n'a été trouvée";
    
    final static public String SELECT_BY_UTILISATEUR = "Echec de la selection par l'utilisateur";
    
    final static public String SELECT_BY_ARTICLE = "Echec de la selection par l'article";
    
    final static public String SELECT_BY_EMAIL = "Echec de la selection par l'email";
    
    final static public String SELECT_BY_ID_AS_VENDEUR = "Echec de la selection d'un utilisateur en tant que vendeur par l'ID";
    
    final static public String SEARCH_DUPLICATES = "Echec de la recherche de données dupliquées";

    
    
   /*______ _ _   _____                        
    | ___ \ | | |  ___|                       
    | |_/ / | | | |__ _ __ _ __ ___  _ __ ___ 
    | ___ \ | | |  __| '__| '__/ _ \| '__/ __|
    | |_/ / | | | |__| |  | | | (_) | |  \__ \
    \____/|_|_| \____/_|  |_|  \___/|_|  |___/*/
                                              
                                              
    
    final static public String EMPTY_FIELD(String champs) {
	return "Le champs " + champs + " est obligatoire";
    }

    final static public String TOO_LARGE_VALUE(String champs, int limite) {
	return "Le champs " + champs + " est limité à " + limite + " caractères";
    }

    final static public String PSEUDO_NOT_UNIQUE = "Votre identifiant est déjà pris par un autre utilisateur.";

    final static public String LOGIN_NOT_ALPHANUMERIC = "Votre identifiant ne peut contenir que des caractères alphanumériques.";

    final static public String UNVALID_EMAIL = "Votre email n'est pas valide.";

    final static public String EMAIL_NOT_UNIQUE = "Votre email est déjà pris par un autre utilisateur.";
    
    final static public String LOGIN_NO_MATCH = "Impossible de vous connecter, êtes-vous sûr d'avoir saisi les bonnes informations ?";

    final static public String UNVALID_PHONE_NUMBER = "Votre numéro de téléphone n'est pas valide.";

    final static public String UNVALID_POSTAL_CODE = "Votre code postal n'est pas valide.";

    final static public String ADMINISTRATOR_DENIED_AUTH = "Vous n'être pas autorisé à être administrateur";

    final static public String TOO_MUCH_CREDIT = "Vous avez trop de crédit";

    final static public String FAILED_PASSWORD_VALIDATION = "Les mots de passe ne correspondent pas";
    
    final static public String NOT_ENOUGHT_CREDIT = "Vous n'avez pas assez de crédit.";

    public static final String AMOUNT_TOO_LOW = "Le montant de l'enchère est inférieur au prix de l'article.";

}
