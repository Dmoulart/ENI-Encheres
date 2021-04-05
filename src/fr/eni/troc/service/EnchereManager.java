package fr.eni.troc.service;

import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.EnchereDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class EnchereManager {

    // Attribut pour repr�senter la couche DAL
    private static EnchereDal enchereDal;

    // Pattern Singleton
    private static EnchereManager instance;

    private EnchereManager() {
	// R�cup�ration de l'instance de enchereDAO
	enchereDal = DALFactory.getEnchereDal();
    }

    public static EnchereManager getEnchereManager() {
	if (instance == null) {
	    instance = new EnchereManager();
	}
	return instance;
    }

    public void insert(Enchere e) throws BusinessException {
	if (e.getMontant() > e.getEmetteur().getCredit() && e.getEmetteur().getCredit() <= 0) {
	    throw new BusinessException(Errors.NOT_ENOUGHT_CREDIT);
	} 

	if (e.getArticle().getPrixVente() >= e.getMontant()) {
	    throw new BusinessException(Errors.AMOUNT_TOO_LOW);
	}
	
	try {
	    enchereDal.insert(e);
	} catch (DALException e1) {
	    throw new BusinessException(e1.getMessage());
	}
    }

}
