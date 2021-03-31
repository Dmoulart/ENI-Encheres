package fr.eni.troc.dal;

import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface UtilisateurDal {

	public Utilisateur find (String pseudo, String motDePasse) throws BusinessException; 
	
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException; 
	
	public void deleteUtilisateur(int id) throws BusinessException; 
	
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;

}
