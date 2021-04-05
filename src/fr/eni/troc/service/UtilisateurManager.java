package fr.eni.troc.service;

import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.UtilisateurDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

/**
 * pattern singleton
 * 
 * @author nicolas
 *
 */
public class UtilisateurManager {

    private UtilisateurDal utilisateurDal;

    // Pattern Singleton
    private static UtilisateurManager instance;

    private UtilisateurManager() {
	utilisateurDal = DALFactory.getUtilisateurDal();
    }

    public static UtilisateurManager getUtilisateurManager() {
	if (instance == null) {
	    instance = new UtilisateurManager();
	}
	return instance;
    }
    
    public Utilisateur selectById(int id) throws BusinessException {
   	// Appelle de la couche DAL
   	try {
   	    Utilisateur u = utilisateurDal.selectById(id);
   	    if (u != null) {
   		return u;

   	    } else {
   		BusinessException be = new BusinessException();
   		be.addError("Erreur de selectbyid");
   		throw be;
   	    }
   	} catch (DALException de) {
   	    BusinessException be = new BusinessException();
   	    de.printStackTrace();
   	    be = new BusinessException();
   	    be.addError(de.getMessage());
   	    be.getErrors().forEach(e -> System.out.println(e));
   	    throw be;
   	}
       }

    public Utilisateur validateConnection(String pseudo, String motDePasse) throws BusinessException {
	try {
	    // pas besoin de vérifier si l'utilisateur est null car si tel est le cas une
	    // exception est levée dans la dal
	    Utilisateur u = utilisateurDal.find(pseudo.trim(), motDePasse);

	    return u;
	} catch (DALException de) {
	    BusinessException be = new BusinessException();
	    de.printStackTrace();
	    be.addError(Errors.LOGIN_NO_MATCH);
	    be.getErrors().forEach(e -> System.out.println(e));
	    throw be;
	}
    }

    public Utilisateur validateConnectionWithEmail(String email, String motDePasse) throws BusinessException {

	// Appelle de la couche DAL
	try {
	    // pas besoin de vérifier si l'utilisateur est null car si tel est le cas une
	    // exception est levée dans la dal
	    Utilisateur u = utilisateurDal.selectByEmail(email, motDePasse);

	    return u;
	} catch (DALException de) {
	    BusinessException be = new BusinessException();
	    de.printStackTrace();
	    be.addError(Errors.LOGIN_NO_MATCH);
	    be.getErrors().forEach(e -> System.out.println(e));
	    throw be;
	}

    }

    /**
     * Permet de creer un nouveau mot de passe utilisateur
     * 
     * @return
     * 
     * @throws BusinessException
     */
    /*
     * public void creerNvxMdp(Utilisateur utilisateur) throws BusinessException {
     * 
     * // Validation des données par rapport au métier BusinessException be = new
     * BusinessException(); boolean isValidPassword =
     * validatePassword(utilisateur.getMotDePasse(), be);
     * 
     * if(isValidPassword) {
     * 
     * //Appelle de la couche DAL
     * DALFactory.getUtilisateurDal().updateMDP(utilisateur); } }
     */

    /**
     * Permet de creer un nouvel utilisateur si le pseudo et mot de passe valident
     * les conditions requises dans les methodes validatePseudo et validatePassword
     * 
     * @param utilisateur
     * @throws BusinessException
     */
    public void creer(Utilisateur utilisateur, String confiMotDePasse) throws BusinessException {
	// Validation des données par rapport au métier
	BusinessException be = new BusinessException();
	validatePseudo(utilisateur.getPseudo(), be);
	validateNom(utilisateur.getNom(), be);
	validatePrenom(utilisateur.getPrenom(), be);
	validateEmail(utilisateur.getEmail(), be);
	validateTelephone(utilisateur.getTelephone(), be);
	validateRue(utilisateur.getRue(), be);
	validateCodePostal(utilisateur.getCodePostal(), be);
	validateVille(utilisateur.getVille(), be);
	validatePassword(utilisateur.getMotDePasse(), be);
	validateConfiPassword(utilisateur.getMotDePasse(), confiMotDePasse, be);

	if (be.getErrors().isEmpty()) {
	    // Appelle de la couche DAL
	    try {
		DALFactory.getUtilisateurDal().insert(utilisateur);
	    } catch (DALException de) {
		de.printStackTrace();
		be = new BusinessException();
		be.addError(de.getMessage());
		throw be;
	    }
	} else {
	    be.getErrors().forEach(e -> System.out.println(e));
	    throw be;
	}
    }

    public void delete(int id) throws BusinessException {
	// Appelle de la couche DAL - pas de vérifications particulieres
	try {
	    DALFactory.getUtilisateurDal().delete(id);
	} catch (DALException de) {
	    de.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError(de.getMessage());
	    throw be;
	}

    }

    public void update(Utilisateur utilisateur) throws BusinessException {
	// Appelle de la couche DAL - pas de vérifications particulieres
	try {
	    DALFactory.getUtilisateurDal().update(utilisateur);
	} catch (DALException de) {
	    de.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError(de.getMessage());
	    throw be;
	}
    }

    private boolean validatePseudo(String pseudo, BusinessException be) {
	if (isNull("Pseudonyme", pseudo, be))
	    return false;

	if (isTooLarge("Pseudonyme", pseudo, 30, be))
	    return false;

	// Vérification alphanumerique
	if (!pseudo.matches("^[a-zA-Z0-9]+$")) {
	    be.addError(Errors.LOGIN_NOT_ALPHANUMERIC);
	    return false;
	}

	try {
	    if (utilisateurDal.hasDuplicates("pseudo", pseudo)) {
		be.addError(Errors.PSEUDO_NOT_UNIQUE);
		return false;
	    }
	} catch (DALException de) {
	    be.addError(de.getMessage());
	}

	return true;
    }

    private boolean validateNom(String nom, BusinessException be) {
	if (isNull("Nom", nom, be))
	    return false;
	if (isTooLarge("Nom", nom, 30, be))
	    return false;
	return true;
    }

    private boolean validatePrenom(String prenom, BusinessException be) {
	if (isNull("Prenom", prenom, be))
	    return false;
	if (isTooLarge("Prenom", prenom, 30, be))
	    return false;
	return true;
    }

    private boolean validateEmail(String email, BusinessException be) {
	if (isNull("Email", email, be))
	    return false;

	if (!email
		.matches("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})")) {
	    be.addError(Errors.UNVALID_EMAIL);
	}

	try {
	    if (utilisateurDal.hasDuplicates("email",email)) {
		be.addError(Errors.EMAIL_NOT_UNIQUE);
		return false;
	    }
	} catch (DALException de) {
	    be.addError(de.getMessage());
	}

	return true;
    }

    private boolean validateTelephone(String telephone, BusinessException be) {
	if (isNull("Telephone", telephone, be))
	    return false;
	if (!telephone.matches("(0|\\\\+33|0033)[1-9][0-9]{8}")) {
	    be.addError(Errors.UNVALID_PHONE_NUMBER);
	}

	return true;
    }

    private boolean validateRue(String rue, BusinessException be) {
	if (isNull("Rue", rue, be))
	    return false;
	if (isTooLarge("Rue", rue, 30, be))
	    return false;
	return true;
    }

    private boolean validateCodePostal(String codePostal, BusinessException be) {
	if (isNull("Code postal", codePostal, be))
	    return false;
	if (!codePostal.matches("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$")) {
	    be.addError(Errors.UNVALID_POSTAL_CODE);
	}
	return true;
    }

    private boolean validateVille(String ville, BusinessException be) {
	if (isNull("Ville", ville, be))
	    return false;
	if (isTooLarge("Ville", ville, 30, be))
	    return false;
	return true;
    }

    private boolean validatePassword(String motDePasse, BusinessException be) {
	if (isNull("Mot de passe", motDePasse, be))
	    return false;
	if (isTooLarge("Mot de passe", motDePasse, 30, be))
	    return false;
	return true;
    }

    private boolean validateConfiPassword(String motDePasse, String confiMotDePasse, BusinessException be) {
	if (isNull("Mot de passe", motDePasse, be))
	    return false;
	if (!motDePasse.equals(confiMotDePasse)) {
	    be.addError(Errors.FAILED_PASSWORD_VALIDATION);
	}
	return true;
    }

    private boolean isNull(String champs, String data, BusinessException be) {
	if (data == null) {
	    be.addError(Errors.EMPTY_FIELD(champs));
	    return true;
	}
	return false;
    }

    private boolean isTooLarge(String champs, String data, int limite, BusinessException be) {
	if (data.length() > limite) {
	    be.addError(Errors.TOO_LARGE_VALUE(champs, limite));
	    return true;
	}
	return false;
    }
}