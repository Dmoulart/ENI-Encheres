package fr.eni.troc.service;

import java.time.LocalDate;
import java.util.List;
import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.ArticleDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

/**
 * pattern singleton
 * 
 * @author nicolas
 *
 */
public class ArticleManager {

    // Attribut pour représenter la couche DAL
    private ArticleDal articleDal;

    // Pattern Singleton
    private static ArticleManager instance;

    private ArticleManager() {
	// R�cup�ration de l'instance de userDAO
	articleDal = DALFactory.getArticleDal();
    }

    public static ArticleManager getArticleManager() {
	if (instance == null) {
	    instance = new ArticleManager();
	}
	return instance;
    }

    public List<Article> selectAll() throws BusinessException {
	List<Article> articles;
	try {
	    articles = articleDal.selectAll();
	    return articles;
	} catch (DALException de) {
	    de.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError(de.getMessage());
	    throw be;
	}
    }

    
    public long creer(Article article) throws BusinessException { //Retourne la clé de l'article générée(long)
	BusinessException be = new BusinessException();
	validateNom(article.getNom(), be);
	validateDescription(article.getDescription(), be);
	validateCategorie(article.getCategorie(), be);
	validateMisePrix(article.getPrixInitial(), be);
	validateDebutEnchere(article.getDebutEncheres(), be);
	validateFinEnchere(article.getFinEncheres(),article.getDebutEncheres(), be);
	validateRue(article.getVendeur().getRue(), be);
	validateCP(article.getVendeur().getCodePostal(), be);
	validateVille(article.getVendeur().getVille(), be);

	if(be.getErrors().isEmpty()) {
	    try {
		return DALFactory.getArticleDal().insert(article);
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

    private boolean validateVille(String ville, BusinessException be) {
	if (isNullOrEmpty("Ville", ville, be))
	    return false;
	if (isTooLarge("Ville", ville, 50, be))
	    return false;
	return true;
    }
    
    private boolean validateCP(String codePostal, BusinessException be) {
	if (isNullOrEmpty("Code Postal", codePostal, be))
	    return false;
	if (isTooLarge("Code Postal", codePostal, 10, be))
	    return false;
	return true;
    }
    
    private boolean validateRue(String rue, BusinessException be) {
	if (isNullOrEmpty("Rue", rue, be))
	    return false;
	if (isTooLarge("Rue", rue, 30, be))
	    return false;
	return true;
    }


    private boolean validateFinEnchere(LocalDate finEncheres, LocalDate debutEncheres, BusinessException be) {
	if (isNull("Date debut Enchere", finEncheres, be))
	    return false;
	if(finEncheres.isBefore(LocalDate.now()))
	    be.addError(Errors.UNCOHERENT_END_DATE);
	if(debutEncheres.isAfter(finEncheres))
	    be.addError(Errors.BEGIN_DATE_AFTER_END_DATE);
	return true;
    }

    private boolean validateDebutEnchere(LocalDate debutEncheres, BusinessException be) {
	if (isNull("Date debut Enchere", debutEncheres, be))
	    return false;
	if(debutEncheres.isBefore(LocalDate.now()))
	    be.addError(Errors.UNCOHERENT_BEGIN_DATE);
	
	return true;
    }

    private boolean validateMisePrix(int prixInitial, BusinessException be) {
	if (isTooLow("Prix initial", prixInitial, be))
	    return false;
	return true;
    }

    private boolean validateCategorie(Categorie categorie, BusinessException be) {
	return true;
    }

    private boolean validateDescription(String description, BusinessException be) {
	if (isNullOrEmpty("Description", description, be))
	    return false;
	if (isTooLarge("Description", description, 300, be))
	    return false;
	return true;
    }

    private boolean validateNom(String nom, BusinessException be) {
	if (isNullOrEmpty("Nom", nom, be))
	    return false;
	if (isTooLarge("Nom", nom, 30, be))
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


    private boolean isNull(String champs, LocalDate data, BusinessException be) {
	if (data == null) {
	    be.addError(Errors.EMPTY_FIELD(champs));
	    return true;
	}
	return false;
    }
    
    private boolean isTooLow(String champs, int data, BusinessException be) {
	if (data < 1) {
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
    
    public Article selectById(int id) throws BusinessException {
	Article article;
	try {
	    article = articleDal.selectById(id);
	    return article;
	} catch (DALException de) {
	    de.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError(de.getMessage());
	    throw be;
	}
    }
    public void update(Article a) throws BusinessException{
	try {
	    articleDal.update(a);
	} catch (DALException de) {
	    de.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError(de.getMessage());
	    throw be;
	}
    }
}