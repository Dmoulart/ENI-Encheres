package fr.eni.troc.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDal{
	private static final String INSERT = "INSERT INTO Categories VALUES(null,?)";
	private static final String DELETE = "DELETE FROM Categories WHERE id=?";
	private static final String UPDATE = "UPDATE Categories SET libelle=? WHERE id=?";
	
	@Override
	public void insert(Categorie categorie) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,categorie.getLibelle());
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
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
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
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
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
