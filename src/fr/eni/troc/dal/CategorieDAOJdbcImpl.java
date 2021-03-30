package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDal{

	@Override
	public void insert(Categorie item) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO Categories VALUES(null,?)");
			pstmt.setString(1,item.getLibelle());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS INSERT CATEGORIE EN DAO");
			throw be;
		}
	}

	@Override
	public void delete(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM Categories WHERE id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS DELETE CATEGORIE EN DAO");
			throw be;
		}
	}

	@Override
	public void update(Categorie categorie) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE Categories SET libelle=? WHERE id=?");
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS UPDATE CATEGORIE EN DAO");
			throw be;
		}		
	}
}
