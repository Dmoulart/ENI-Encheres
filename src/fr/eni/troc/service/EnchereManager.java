package fr.eni.troc.service;

import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.EnchereDal;

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

}
