package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface UtilisateurDal {

	public Utilisateur find (String pseudo, String motDePasse) throws BusinessException; 
	
	
}
