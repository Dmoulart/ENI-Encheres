package fr.eni.troc.service;


import java.util.ArrayList;
import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.ArticleDal;
import fr.eni.troc.exception.BusinessException;


/**
 * pattern singleton
 * @author nicolas
 *
 */
public class ArticleManager {
	
		//Attribut pour représenter la couche DAL
		private ArticleDal articleDal; 
	
		// Pattern Singleton
		private static ArticleManager instance;

		private ArticleManager() {
			//Récupération de l'instance de userDAO
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
			} catch (BusinessException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addError("ERROR SELECT ALL COUCHE BLL - " + e.getMessage());
				throw be;
			}
		}

}
