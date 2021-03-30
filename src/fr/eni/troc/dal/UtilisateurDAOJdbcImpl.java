package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDal{
	
	public static final String CONNECTION = "SELECT pseudo, prenom, nom FROM utilisateurs WHERE pseudo=? AND mot_de_passe=?";
	public static final String CREATION_UTILISATEUR = "INSERT INTO utilisateurs (id_utilisateur, pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)\r\n" + 
			"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	
	/**
	 * Methode pour trouver un utilisateur dans la BDD
	 * @author nicolas
	 *
	 */
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
			BusinessException be = new BusinessException();
			be.addError("ERROR DB - " + e.getMessage());
			throw be;
			}
		
	}

	
	/**
	 * Methode pour creer un nouvel utilisateur en BDD
	 * @author nicolas
	 *
	 */
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException{
	
		try (Connection cnx = ConnectionProvider.getConnection()) {
		PreparedStatement insert = cnx.prepareStatement(CREATION_UTILISATEUR);
		
		insert.setString(1, utilisateur.getPseudo());
		insert.setString(2, utilisateur.getNom());
		insert.setString(3, utilisateur.getPrenom());
		insert.setString(4, utilisateur.getEmail());
		insert.setString(5, utilisateur.getTelephone());
		insert.setString(6, utilisateur.getRue());
		insert.setString(7, utilisateur.getCodePostal());
		insert.setString(8, utilisateur.getVille());
		insert.setString(9, utilisateur.getMotDePasse());
		insert.setInt(10, 100);
		insert.setInt(11, 0); 
		
		insert.executeUpdate();
		
		
		} catch (SQLException e){
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERROR DB - " + e.getMessage());
			throw be;
		}
	}
	
}
	
	
	
	
