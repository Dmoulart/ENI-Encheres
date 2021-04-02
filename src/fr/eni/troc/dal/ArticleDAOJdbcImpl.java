package fr.eni.troc.dal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;


public class ArticleDAOJdbcImpl implements ArticleDal {
	
	private static final String INSERT = "INSERT INTO Articles VALUES(null,?,?,?,?,?,?,?,?);";
	
	private static final String DELETE = "DELETE FROM Articles WHERE id=?";
	
	private static final String UPDATE = "UPDATE Articles SET nom=?, description=?, date_debut_encheres=?, "
										  + "date_fin_encheres=?,prix_initial=?,id_categorie=? WHERE id=?";
	
	private static final String SELECT_ALL = "SELECT id,nom,description,date_debut_encheres,date_fin_encheres,"
										   	  + "prix_initial,prix_vente,id_utilisateur,id_categorie FROM ARTICLES";

	private static final String SELECT_BY_VENDEUR = "SELECT id,nom,description,date_debut_encheres,date_fin_encheres,"
		   	  										+ "prix_initial,prix_vente,id_utilisateur,id_categorie "
		   	  										+ "FROM ARTICLES WHERE id_utilisateur = ?";
	
	private static final String SELECT_BY_ID = "SELECT id,nom,description,date_debut_encheres,date_fin_encheres,"
													+ "prix_initial,prix_vente,id_utilisateur,id_categorie "
													+ "FROM ARTICLES WHERE id = ?";
	private static final String SELECT_BY_NOM = "SELECT id,nom,description,date_debut_encheres,date_fin_encheres,"
												+ "prix_initial,prix_vente,id_utilisateur,id_categorie "
												+ "FROM ARTICLES WHERE TRIM(LOWER(nom) = ?";
	// INSERT ARTICLES
	@Override
	public void insert(final Article article) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNom());
			pstmt.setString(2,article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getFinEncheres()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setNull(6, java.sql.Types.INTEGER);
			pstmt.setInt(7, article.getVendeur().getId());
			pstmt.setInt(8, article.getCategorie().getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			DALException de = new DALException(Errors.INSERT,this.getClass().getSimpleName(),e);
			throw de;
		}
	}
	
	//DELETE ARTICLES
	@Override
	public void delete (int id) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			DALException de = new DALException(Errors.DELETE,this.getClass().getSimpleName(),e);
			throw de;
		}
	}
	
	//UPDATE ARTICLES
	@Override
	public void update (Article article) throws DALException {
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
			DALException de = new DALException(Errors.UPDATE,this.getClass().getSimpleName(),e);
			throw de;
		}
	}
	//SELECT ALL ARTICLES
	@Override
	public List<Article> selectAll() throws DALException {
		List<Article> articles = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {
				Article article = itemBuilder(rs);
				articles.add(article);
			}
		} catch (Exception e) {
			DALException de = new DALException(Errors.UPDATE,this.getClass().getSimpleName(),e);
			throw de;
		}
		
		return articles;
	}

	//SELECT ARTICLES BY VENDEUR
	@Override
	public List<Article> selectByVendeur(Utilisateur utilisateur) throws DALException {
		List<Article> articles = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_VENDEUR);
			pstmt.setInt(1, utilisateur.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Article article = itemBuilder(rs,utilisateur);
				articles.add(article);
			}
		} catch (Exception e) {
			DALException de = new DALException(Errors.SELECT_BY_VENDEUR,this.getClass().getSimpleName(),e);
			throw de;
		}
		return articles;
	}



	@Override
	public Article selectById(int id) throws DALException {
		Article article = new Article();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = itemBuilder(rs);
			}
			else {
				DALException de = new DALException(Errors.NO_DATA_FOUND,this.getClass().getSimpleName());
				throw de;
			}
		} catch (Exception e) {
			DALException de = new DALException(Errors.SELECT_BY_ID,this.getClass().getSimpleName(),e);
			throw de;
		}
		return article;
	}
	
	/*public List<Article> selectByName(String nom) throws DALException {
		List<Article> articles = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_VENDEUR);
			pstmt.setString(1, nom.trim().toLowerCase());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Article article = itemBuilder(rs);
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addError("ERREUR DANS SELECTBYVENDEUR ARTICLE EN DAO");
			throw be;
		}
		return articles;
	}*/
	/*--ITEM BUILDERS------------------------------------------------------------*/
	/**
	 * Base pour les itemBuilders.
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private Article baseBuilder(ResultSet rs) throws SQLException{
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setNom(rs.getString("nom"));
		article.setDescription(rs.getString("description"));
		article.setDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		article.setFinEcheres(rs.getDate("date_fin_encheres").toLocalDate());
		article.setPrixInitial(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		return article;
	}
	/**
	 * ItemBuilder : les vendeurs sont selectionnés en tant que vendeurs seulement
	 * apellé par le SelectAll
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws DALException 
	 * @throws BusinessException
	 */
	private Article itemBuilder(ResultSet rs) throws SQLException, DALException{
		Article article = baseBuilder(rs);
		article.setVendeur(DALFactory.getUtilisateurDal()
				 .selectByIdAsVendeur(rs.getInt("id_utilisateur")));
		article.setEncheres(DALFactory.getEnchereDal().selectByArticle(article));
		article.setEstVendu((article.getVendeur() == null)? true : false); 
		article.setCategorie(DALFactory.getCategorieDal().selectById(rs.getInt("id_categorie")));
		return article;
	}
	/**
	 * ItemBuilder pour le selectByVendeur : le vendeur est déjà connus.
	 * Apellé par SelectByVendeur
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws DALException 
	 * @throws BusinessException
	 */
	private Article itemBuilder(ResultSet rs, Utilisateur utilisateur) throws SQLException, DALException{
		Article article = baseBuilder(rs);
		article.setVendeur(utilisateur);
		article.setEstVendu((article.getVendeur() == null)? true : false); 
		article.setCategorie(DALFactory.getCategorieDal().selectById(rs.getInt("id_categorie")));
		return article;
	}
}
