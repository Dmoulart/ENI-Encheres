package fr.eni.troc.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class CategorieDAOJdbcImpl implements CategorieDal{
	
	private static final String SELECT_ALL = "SELECT * FROM Categories;";
	
	private static final String SELECT_BY_ID = "SELECT * FROM Categories WHERE id = ?;";
	
	private static final String INSERT = "INSERT INTO Categories VALUES(null,?)";
	
	private static final String DELETE = "DELETE FROM Categories WHERE id=?";
	
	private static final String UPDATE = "UPDATE Categories SET libelle=? WHERE id=?";
	
	@Override
	public void insert(Categorie categorie) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,categorie.getLibelle());
			pstmt.executeUpdate();
		} catch (Exception e) {
			DALException de = new DALException(Errors.INSERT,this.getClass().getSimpleName(),e);
			throw de;
		}
	}

	@Override
	public void delete(int id) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			DALException de = new DALException(Errors.DELETE,this.getClass().getSimpleName(),e);
			throw de;
		}
	}

	@Override
	public void update(Categorie categorie) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			DALException de = new DALException(Errors.UPDATE,this.getClass().getSimpleName(),e);
			throw de;
		}		
	}
	
	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categories = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next()) {
				categories.add(new Categorie(rs.getInt("id"),rs.getString("libelle")));
			}			
			
		}catch (SQLException e) {
			DALException de = new DALException(Errors.SELECT_ALL,this.getClass().getSimpleName(),e);
			throw de;
		}
		return categories;
	}
	
	@Override
	public Categorie selectById(int id) throws DALException{
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Categorie(rs.getInt("id"),rs.getString("libelle"));
				
			}else {
				DALException de = new DALException(Errors.NO_DATA_FOUND,this.getClass().getSimpleName());
				throw de;
			}			
			
			}catch (SQLException e) {
				DALException de = new DALException(Errors.SELECT_BY_ID,this.getClass().getSimpleName(),e);
				throw de;
			}
	}


}
