package fr.eni.troc.service;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Retrait;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.RetraitDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class RetraitManager {
    private RetraitDal retraitDal;

    // Pattern Singleton
    private static RetraitManager instance;

    private RetraitManager() {
	retraitDal = DALFactory.getRetraitDal();
    }

    public static RetraitManager getRetraitManager() {
	if (instance == null) {
	    instance = new RetraitManager();
	}
	return instance;
    }

    public void insert(Retrait retrait) throws BusinessException {
	BusinessException be = new BusinessException();
	
	try {
	    isNullOrEmpty("article", retrait.getArticle(), be);
	    validateRue(retrait.getRue(),be);
	    validateCodePostal(retrait.getCodePostal(),be);
	    validateVille(retrait.getVille(),be);
	    retraitDal.insert(retrait);
	} catch (DALException e) {
	    e.printStackTrace();
	    be.addError(e.getMessage());
	    throw be;
	}
    }
    
    public Retrait selectByIdArticle(int idArticle) throws BusinessException {
   	BusinessException be = new BusinessException();
   	
   	try {   	    
   	   return retraitDal.selectByIdArticle(idArticle);
   	} catch (DALException e) {
   	    e.printStackTrace();
   	    be.addError(e.getMessage());
   	    throw be;
   	}
    }
    
    private boolean validateRue(String rue, BusinessException be) {
	if (isNullOrEmpty("Rue", rue, be))
	    return false;
	if (isTooLarge("Rue", rue, 30, be))
	    return false;
	return true;
    }

    private boolean validateCodePostal(String codePostal, BusinessException be) {
	if (isNullOrEmpty("Code postal", codePostal, be))
	    return false;
	if (!codePostal.matches("^(([0-8][0-9])|(9[0-5])|(2[ab]))[0-9]{3}$")) {
	    be.addError(Errors.UNVALID_POSTAL_CODE);
	}
	return true;
    }

    private boolean validateVille(String ville, BusinessException be) {
	if (isNullOrEmpty("Ville", ville, be))
	    return false;
	if (isTooLarge("Ville", ville, 30, be))
	    return false;
	return true;
    }
    
    private boolean isNullOrEmpty(String champs, String data, BusinessException be) {
	if (data == null || data.isEmpty()) {
	    be.addError(Errors.EMPTY_FIELD(champs));
	    return true;
	}
	return false;
    }
    private boolean isNullOrEmpty(String champs, Object data, BusinessException be) {
	if (data == null ) {
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
