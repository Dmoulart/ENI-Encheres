package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDal{
	
	public static final String FIND = "SELECT pseudo, prenom, nom FROM utilisateurs WHERE pseudo=? AND mot_de_passe=?";
	public static final String INSERT = "INSERT INTO utilisateurs (id, pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)\r\n" + 
			"VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	public static final String DELETE = "DELETE FROM utilisateurs WHERE id= ?"; 
	public static final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=? WHERE id=?"; 
	
	/**
	 * Methode pour trouver un utilisateur dans la BDD
	 * @author nicolas
	 *
	 */
	@Override
	 public Utilisateur find(String pseudo, String motDePasse) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(FIND);
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
	public void insert (Utilisateur utilisateur) throws BusinessException{
	
		try (Connection cnx = ConnectionProvider.getConnection()) {
		PreparedStatement insert = cnx.prepareStatement(INSERT);
		
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


	/**
	 *Methode qui supprime un utilisateur en BDD
	 *@param id
	 *@author nicolas
	 */
	@Override
	public void delete (int id) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement delete = cnx.prepareStatement(DELETE);
			
			delete.setInt(1, id);
		
			delete.executeUpdate();
			
			
			} catch (SQLException e){
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addError("ERROR DB - " + e.getMessage());
				throw be;
			}
		
	}


	@Override
	public void update (Utilisateur utilisateur) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement update = cnx.prepareStatement(UPDATE);
			
			
			update.setString(1, utilisateur.getPseudo());
			update.setString(2, utilisateur.getNom());
			update.setString(3, utilisateur.getPrenom());
			update.setString(4, utilisateur.getEmail());
			update.setString(5, utilisateur.getTelephone());
			update.setString(6, utilisateur.getRue());
			update.setString(7, utilisateur.getCodePostal());
			update.setString(8, utilisateur.getVille());
			update.setInt(9, utilisateur.getId());
			
			update.executeUpdate();
			
			
			} catch (SQLException e){
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.addError("ERROR DB - " + e.getMessage());
				throw be;
			}
	
		
	}
	
}
	
	
	
	
