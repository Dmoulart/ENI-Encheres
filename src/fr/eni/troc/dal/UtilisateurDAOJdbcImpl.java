package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDal{
	
	public static final String CONNECTION = "select pseudo, prenom, nom from utilisateurs where pseudo=? and mot_de_passe=?";
	
	@Override
	 public Utilisateur find(String pseudo, String motDePasse) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CONNECTION);
			pstmt.setString(1,pseudo);
			pstmt.setString(2,motDePasse);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Utilisateur u = new Utilisateur();
				u.setPseudo(rs.getString("pseudo"));
				u.setNom(rs.getString("nom"));
				u.setPrenom(rs.getString("prenom"));
			
				return u;
				
			}else {
				//Utilisateur non trouvé
				BusinessException be = new BusinessException();
				be.addError("Pseudo ou Mot de passe inconnu");
				throw be;
			}			
		
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

}
