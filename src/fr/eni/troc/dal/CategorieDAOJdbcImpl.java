package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDal {

    private static final String SELECT_ALL = "SELECT * FROM Categories;";

    private static final String SELECT_BY_ID = "SELECT * FROM Categories WHERE id = ?;";

    private static final String INSERT = "INSERT INTO Categories VALUES(null,?)";

    private static final String DELETE = "DELETE FROM Categories WHERE id=?";

    private static final String UPDATE = "UPDATE Categories SET libelle=? WHERE id=?";

    @Override
    public void insert(Categorie categorie) throws BusinessException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	    pstmt.setString(1, categorie.getLibelle());
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
	try (Connection cnx = ConnectionProvider.getConnection()) {
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
	try (Connection cnx = ConnectionProvider.getConnection()) {
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

    @Override
    public List<Categorie> selectAll() throws BusinessException {
	List<Categorie> categories = new ArrayList<>();
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    Statement stmt = cnx.createStatement();
	    ResultSet rs = stmt.executeQuery(SELECT_ALL);
	    while (rs.next()) {
		categories.add(new Categorie(rs.getInt("id"), rs.getString("libelle")));
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError("ERROR DB - " + e.getMessage());
	    throw be;
	}
	return categories;
    }

    @Override
    public Categorie selectById(int id) throws BusinessException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
		return new Categorie(rs.getInt("id"), rs.getString("libelle"));

	    } else {
		// Utilisateur non trouv�
		BusinessException be = new BusinessException();
		be.addError("Cat�gorie introuvable");
		throw be;
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	    BusinessException be = new BusinessException();
	    be.addError("ERROR DB - " + e.getMessage());
	    throw be;
	}
    }

}
