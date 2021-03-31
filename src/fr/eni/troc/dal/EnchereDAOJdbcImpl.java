package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import fr.eni.troc.bo.Enchere;
import fr.eni.troc.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDal{
	private static final String INSERT = "INSERT INTO Encheres VALUES(null,?,?,?,?)";
	private static final String DELETE = "DELETE FROM Encheres WHERE id=?";
	private static final String UPDATE = "UPDATE Encheres SET date=?,montant=?,id_article=?,id_utilisateur=? WHERE id=?";

	@Override
	public void insert(Enchere item) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT);
			pstmt.setDate(1, Date.valueOf(item.getDate()));
			pstmt.setInt(2, item.getMontant());
			pstmt.setInt(3, item.getArticle().getId());
			pstmt.setInt(4, item.getEmetteur().getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS INSERT ENCHERE EN DAO");
			throw be;
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS DELETE ENCHERE EN DAO");
			throw be;
		}
	}

	@Override
	public void update(Enchere enchere) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setDate(1, Date.valueOf(enchere.getDate()));
			pstmt.setInt(2, enchere.getMontant());
			pstmt.setInt(3, enchere.getArticle().getId());
			pstmt.setInt(4, enchere.getEmetteur().getId());
			pstmt.setInt(5, enchere.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS UPDATE ENCHERE EN DAO");
			throw be;
		}
	}

}
