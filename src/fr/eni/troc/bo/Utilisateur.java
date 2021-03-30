package fr.eni.troc.bo;

import java.util.List;

public class Utilisateur {

	int id;
	String pseudo;
	String nom;
	String prenom;
	String email;
	String telephone; 
	String rue;
	String codePostal;
	String ville;
	String motDePasse;
	int credit;
	boolean administrateur;
	List <Enchere> encheres; 
	List<Article> articlesEnVentes; 
	List <Article> articlesAcquis; 
	
}
