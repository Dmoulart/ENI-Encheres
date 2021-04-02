package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import fr.eni.troc.bo.Retrait;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class RetraitDAOJdbcImpl implements RetraitDal {
    private static final String INSERT = "INSERT INTO Retraits VALUES(?,?,?,?)";
    private static final String DELETE = "DELETE FROM Retraits WHERE id_article=?";
    private static final String UPDATE = "UPDATE Retraits SET rue=?, code_postal=?, ville=?, WHERE id_article=?";

    @Override
    public void insert(Retrait retrait) throws DALException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	    pstmt.setInt(1, retrait.getArticle().getId());
	    pstmt.setString(2, retrait.getRue());
	    pstmt.setString(3, retrait.getCodePostal());
	    pstmt.setString(4, retrait.getVille());
	    pstmt.executeUpdate();
	} catch (Exception e) {
	    DALException de = new DALException(Errors.INSERT, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    @Override
    public void delete(int id) throws DALException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(DELETE);
	    pstmt.setInt(1, id);
	    pstmt.executeUpdate();
	} catch (Exception e) {
	    DALException de = new DALException(Errors.DELETE, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    @Override
    public void update(Retrait retrait) throws DALException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
	    pstmt.setString(1, retrait.getRue());
	    pstmt.setString(2, retrait.getCodePostal());
	    pstmt.setString(3, retrait.getVille());
	    pstmt.setInt(4, retrait.getArticle().getId());
	    pstmt.executeUpdate();
	} catch (Exception e) {
	    DALException de = new DALException(Errors.UPDATE, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }
}
