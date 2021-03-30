package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import fr.eni.troc.bo.Article;
import fr.eni.troc.exception.BusinessException;


public class ArticleDAOJdbcImpl implements ArticleDal {
	// INSERT ARTICLES
	@Override
	public void insert(final Article item) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO Articles VALUES(null,?,?,?,?,?,?);");
			pstmt.setString(1, item.getNom());
			pstmt.setString(2,item.getDescription());
			pstmt.setDate(3, Date.valueOf(item.getDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(item.getFinEncheres()));
			pstmt.setInt(5, item.getPrixInitial());
			pstmt.setInt(6, item.getCategorie().getId());
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
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM Articles WHERE id=?");
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
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE Articles SET nom=?, description=?, date_debut_encheres=?, date_fin_encheres=?,prix_initial=?,id_categorie=? WHERE id=?");
			pstmt.setString(1,article.getNom());
			pstmt.setString(1,article.getDescription());
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
