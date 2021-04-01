package fr.eni.troc.service;


import java.util.ArrayList;
import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.ArticleDal;
import fr.eni.troc.dal.CategorieDal;
import fr.eni.troc.exception.BusinessException;


/**
 * pattern singleton
 * @author nicolas
 *
 */
public class CategorieManager {
	
		//Attribut pour représenter la couche DAL
		private CategorieDal categorieDal; 
	
		// Pattern Singleton
		private static CategorieManager instance;

		private CategorieManager() {
			//Récupération de l'instance de userDAO
			categorieDal = DALFactory.getCategorieDal();
		}

		public static CategorieManager getCategorieManager() {
			if (instance == null) {
				instance = new CategorieManager();
			}
			return instance;
		}
		
		
		public List<Categorie> selectAll() throws BusinessException {
			List<Categorie> categories;
			try {
				categories = categorieDal.selectAll();
				return categories;
			} catch (BusinessException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addError("ERROR SELECT ALL COUCHE BLL - " + e.getMessage());
				throw be;
			}
		}

}
