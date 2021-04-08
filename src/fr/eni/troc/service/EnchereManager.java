package fr.eni.troc.service;

import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.EnchereDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class EnchereManager {

    // Attribut pour représenter la couche DAL
    private static EnchereDal enchereDal;

    // Pattern Singleton
    private static EnchereManager instance;

    private EnchereManager() {
	// Récupération de l'instance de enchereDAO
	enchereDal = DALFactory.getEnchereDal();
    }

    public static EnchereManager getEnchereManager() {
	if (instance == null) {
	    instance = new EnchereManager();
	}
	return instance;
    }

    public void insert(Enchere e, Article a, int debitEncherisseur) throws BusinessException {
	
	int prixVentePrecedent;
	System.out.println("article : " + a.toString());
	System.out.println("----------------------------------------------------");
	List<Enchere> encheresArticle = a.getEncheres();
	for (int i = 0; i < a.getEncheres().size(); i++) {
	    System.out.println("enchere numero " +i+" : " +a.getEncheres().get(i).toString());
	}
	if(a.getEncheres().size() > 1) {
	    prixVentePrecedent = encheresArticle.get(encheresArticle.size()-2).getMontant();    
	}
	else {
	    prixVentePrecedent = a.getPrixInitial(); // ou prixInitial
	}

	if (debitEncherisseur > e.getEmetteur().getCredit() || e.getEmetteur().getCredit() <= 0) {
	    BusinessException be = new BusinessException(Errors.NOT_ENOUGHT_CREDIT);
	    be.addError(Errors.NOT_ENOUGHT_CREDIT);
	    throw be;
	}

	if (prixVentePrecedent >= e.getMontant()) {
	    BusinessException be = new BusinessException(Errors.AMOUNT_TOO_LOW);
	    be.addError(Errors.AMOUNT_TOO_LOW);
	    throw be;
	}

	try {
	    enchereDal.insert(e);
	} catch (DALException e1) {
	    BusinessException be = new BusinessException(e1.getMessage());
	    be.addError(e1.getMessage());
	    throw be;
	}
    }
    public List<Enchere> selectByUtilisateur(Utilisateur u) throws BusinessException {
	try {
	    return enchereDal.selectByUtilisateur(u);
	} catch (DALException e1) {
	    BusinessException be = new BusinessException(e1.getMessage());
	    be.addError(e1.getMessage());
	    throw be;
	}
	
    }
    
    public void delete(final int id) throws DALException, BusinessException{
	try {
	enchereDal.delete(id);
	} catch (DALException e1) {
	    
	BusinessException be = new BusinessException(e1.getMessage());
	be.addError(e1.getMessage());
	throw be;
    }
    }
}
