package fr.eni.troc.dal;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface UtilisateurDal {

	public Utilisateur find (String pseudo, String motDePasse) throws BusinessException; 
	
	public void insert (Utilisateur utilisateur) throws BusinessException; 
	
	public void delete (int id) throws BusinessException; 
	
	public void update (Utilisateur utilisateur) throws BusinessException;
}
