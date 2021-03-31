package fr.eni.troc.dal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import fr.eni.troc.bo.Article;
import fr.eni.troc.exception.BusinessException;


public class ArticleDAOJdbcImpl implements ArticleDal {
	private static final String INSERT = "INSERT INTO Articles VALUES(null,?,?,?,?,?,?,?,?);";
	private static final String DELETE = "DELETE FROM Articles WHERE id=?";
	private static final String UPDATE = "UPDATE Articles SET nom=?, description=?, date_debut_encheres=?, date_fin_encheres=?,prix_initial=?,id_categorie=? WHERE id=?";

	// INSERT ARTICLES
	@Override
	public void insert(final Article item) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, item.getNom());
			pstmt.setString(2,item.getDescription());
			pstmt.setDate(3, Date.valueOf(item.getDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(item.getFinEncheres()));
			pstmt.setInt(5, item.getPrixInitial());
			pstmt.setNull(6, java.sql.Types.INTEGER);
			pstmt.setInt(7, item.getVendeur().getId());
			pstmt.setInt(8, item.getCategorie().getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS INSERT ARTICLE EN DAO");
			throw be;
		}
	}
	
	//DELETE ARTICLES
	@Override
	public void delete (int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS DELETE ARTICLE EN DAO");
			throw be;
		}
	}
	
	//UPDATE ARTICLES
	@Override
	public void update (Article article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1,article.getNom());
			pstmt.setString(2,article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getFinEncheres()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getCategorie().getId());	
			pstmt.setInt(7, article.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS UPDATE ARTICLE EN DAO");
			throw be;
		}
	}
}
