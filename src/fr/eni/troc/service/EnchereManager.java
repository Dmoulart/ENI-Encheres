package fr.eni.troc.service;

import java.time.LocalDate;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.EnchereDal;
import fr.eni.troc.exception.BusinessException;

public class EnchereManager {
	
	//Attribut pour représenter la couche DAL
	private static EnchereDal enchereDal;
	
	// Pattern Singleton
	private static EnchereManager instance;
	
	private EnchereManager() {
		//Récupération de l'instance de enchereDAO
		enchereDal = DALFactory.getEnchereDal();
	}
	
	public static EnchereManager getEnchereManager() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
}
