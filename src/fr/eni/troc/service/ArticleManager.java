package fr.eni.troc.service;

import java.util.List;
import fr.eni.troc.bo.Article;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.ArticleDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;

/**
 * pattern singleton
 * 
 * @author nicolas
 *
 */
public class ArticleManager {

    // Attribut pour repr�senter la couche DAL
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
