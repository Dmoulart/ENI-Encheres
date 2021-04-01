package fr.eni.troc.dal;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface UtilisateurDal {

	public Utilisateur find (String pseudo, String motDePasse) throws BusinessException; 
	
	public Utilisateur selectByEmail (String email, String motDePasse) throws BusinessException; 
	
	public void insert (Utilisateur utilisateur) throws BusinessException; 
	
	public void delete (int id) throws BusinessException; 
	
	public void update (Utilisateur utilisateur) throws BusinessException;

	public Utilisateur selectById(int id) throws BusinessException;
	
	public Utilisateur selectByIdAsVendeur(int id) throws BusinessException;

	public Utilisateur selectByIdAsEmetteur(int id) throws BusinessException;

	//public void updateMDP(Utilisateur utilisateur) throws BusinessException;

}
