package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class EnchereDAOJdbcImpl implements EnchereDal {

    private static final String SELECT_BY_ARTICLE = "SELECT * FROM Encheres WHERE id_article = ?";
    
    private static final String SELECT_BY_UTILISATEUR = "SELECT * FROM Encheres WHERE id_utilisateur = ?";

    private static final String INSERT = "INSERT INTO Encheres VALUES(null,?,?,?,?)";

    private static final String DELETE = "DELETE FROM Encheres WHERE id=?";

    private static final String UPDATE = "UPDATE Encheres SET date=?,montant=?,id_article=?,id_utilisateur=? WHERE id=?";

    @Override
    public void insert(Enchere enchere) throws DALException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(INSERT);
	    pstmt.setDate(1, Date.valueOf(enchere.getDate()));
	    pstmt.setInt(2, enchere.getMontant());
	    pstmt.setInt(3, enchere.getArticle().getId());
	    pstmt.setInt(4, enchere.getEmetteur().getId());
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
    public void update(Enchere enchere) throws DALException {
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
	    pstmt.setDate(1, Date.valueOf(enchere.getDate()));
	    pstmt.setInt(2, enchere.getMontant());
	    pstmt.setInt(3, enchere.getArticle().getId());
	    pstmt.setInt(4, enchere.getEmetteur().getId());
	    pstmt.setInt(5, enchere.getId());
	    pstmt.executeUpdate();
	} catch (Exception e) {
	    DALException de = new DALException(Errors.UPDATE, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    @Override
    public List<Enchere> selectByArticle(Article article) throws DALException {
	List<Enchere> encheres = new ArrayList<>();

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
	    pstmt.setInt(1, article.getId());
	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
		Enchere enchere = itemBuilder(rs, article);
		encheres.add(enchere);
	    }
	} catch (Exception e) {
	    DALException de = new DALException(Errors.SELECT_BY_ARTICLE, this.getClass().getSimpleName(), e);
	    throw de;
	}
	return encheres;
    }

    @Override
    public List<Enchere> selectByUtilisateur(Utilisateur emetteur) throws DALException {
	List<Enchere> encheres = new ArrayList<>();

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR);
	    pstmt.setInt(1, emetteur.getId());
	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
		Enchere enchere = itemBuilder(rs, emetteur);
		encheres.add(enchere);
	    }
	} catch (Exception e) {
	    DALException de = new DALException(Errors.SELECT_BY_UTILISATEUR, this.getClass().getSimpleName(), e);
	    throw de;
	}
	return encheres;
    }

    /*--ITEM BUILDERS------------------------------------------------------------*/
    /**
     * Base pour les itemBuilders.
     * 
     * @param rs
     * @return
     * @throws SQLException
     * @throws BusinessException
     */
    private Enchere baseBuilder(ResultSet rs) throws SQLException {
	Enchere e = new Enchere();
	e.setId(rs.getInt("id"));
	e.setDate(rs.getDate("date").toLocalDate());
	e.setMontant(rs.getInt("montant"));
	return e;
    }

    /**
     * Lorsque l'�metteur est connu il est inutile de le cherche en BDD
     * 
     * @param rs
     * @param emetteur
     * @return
     * @throws SQLException
     * @throws DALException
     * @throws BusinessException
     */
    private Enchere itemBuilder(ResultSet rs, Utilisateur emetteur) throws SQLException, DALException {
	Enchere e = baseBuilder(rs);
	e.setEmetteur(emetteur);
	e.setArticle(DALFactory.getArticleDal().selectById(rs.getInt("id_article")));
	return e;
    }

    /**
     * Lorsque l'article est d�j� connu il est inutile de le chercher en BDD
     * 
     * @param rs
     * @param article
     * @return
     * @throws SQLException
     * @throws BusinessException
     */
    private Enchere itemBuilder(ResultSet rs, Article article) throws SQLException, DALException {
	Enchere e = baseBuilder(rs);
	e.setEmetteur(DALFactory.getUtilisateurDal().selectByIdAsEmetteur(rs.getInt("id_utilisateur")));
	e.setArticle(article);
	return e;

    }

}
