package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Retrait;
import fr.eni.troc.exception.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDal {

	@Override
	public void insert(Retrait item) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("INSERT INTO Retraits VALUES(null,?,?,?)");
			pstmt.setString(1, item.getRue());
			pstmt.setString(2, item.getCodePostal());
			pstmt.setString(3, item.getVille());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS INSERT RETRAIT EN DAO");
			throw be;
		}
	}

	@Override
	public void delete(Article article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM Retraits WHERE id_article?");
			((Retrait) pstmt).setArticle(article);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			throw be;
		}
	}

	@Override
	public void update(Retrait retrait) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE Retraits SET rue=?, code_postal=?, ville=?, WHERE id_article=?");
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			((Retrait) pstmt).setArticle(retrait.getArticle());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS UPDATE RETRAIT EN DAO");
			throw be;
		}
	}

}
