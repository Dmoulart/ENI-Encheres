package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class UtilisateurDAOJdbcImpl implements UtilisateurDal {

    public static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE id=?";

    public static final String FIND = "SELECT id, pseudo, prenom, nom, email, telephone, rue, code_postal, ville, credit FROM utilisateurs WHERE pseudo=? AND mot_de_passe=? ";

    public static final String SELECT_BY_EMAIL = "SELECT id, pseudo, prenom, nom, email, telephone, rue, code_postal, ville, credit FROM utilisateurs WHERE email=? AND mot_de_passe=?";
    
    public static final String INSERT = "INSERT INTO utilisateurs (id, pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)\r\n"
	    + "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    public static final String DELETE = "DELETE FROM utilisateurs WHERE id= ?";

    public static final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=? WHERE id=?";
    
    public static final String GET_DUPLICATES = "SELECT ?, COUNT(*) c FROM utilisateurs GROUP BY ? HAVING c > 1;";
    // public static final String UPDATE_MDP = "UPDATE utilisateur SET
    // mot_de_passe=? WHERE id=?";

    
    
    /**
     * Methode pour trouver un utilisateur dans la BDD
     * 
     * @author nicolas
     *
     */
    @Override
    public Utilisateur find(String pseudo, String motDePasse) throws DALException {
	
	try (Connection cnx = ConnectionProvider.getConnection()) {
		PreparedStatement pstmt = cnx.prepareStatement(FIND);
		pstmt.setString(1,pseudo);
		pstmt.setString(2,motDePasse);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			Utilisateur u = new Utilisateur();
			u.setId(rs.getInt("id"));
			u.setPseudo(rs.getString("pseudo"));
			u.setNom(rs.getString("nom"));
			u.setPrenom(rs.getString("prenom"));
			u.setEmail(rs.getString("email"));
			u.setTelephone(rs.getString("telephone"));
			u.setRue(rs.getString("rue"));
			u.setCodePostal(rs.getString("code_postal"));
			u.setVille(rs.getString("ville"));
			u.setCredit(rs.getInt("credit"));
		
			return u;

	    } else {
		DALException de = new DALException(Errors.NO_DATA_FOUND, this.getClass().getSimpleName());
		throw de;
	    }

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.INSERT, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    /**
     * 
     * Methode qui permet de retrouver un utilisateur par son email
     * 
     * @param email
     * @param motDePasse
     * @author nicolas
     */
    @Override
    public Utilisateur selectByEmail(String email, String motDePasse) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
	    pstmt.setString(1, email);
	    pstmt.setString(2, motDePasse);

	    ResultSet rs = pstmt.executeQuery();

	    if (rs.next()) {
		Utilisateur u = new Utilisateur();
		u.setId(rs.getInt("id"));
		u.setPseudo(rs.getString("pseudo"));
		u.setNom(rs.getString("nom"));
		u.setPrenom(rs.getString("prenom"));
		u.setEmail(rs.getString("email"));
		u.setTelephone(rs.getString("telephone"));
		u.setRue(rs.getString("rue"));
		u.setCodePostal(rs.getString("code_postal"));
		u.setVille(rs.getString("ville"));
		u.setCredit(rs.getInt("credit"));

		return u;

	    } else {
		DALException de = new DALException(Errors.NO_DATA_FOUND, this.getClass().getSimpleName());
		throw de;
	    }

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.SELECT_BY_EMAIL, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }
    /**
     * Methode pour creer un nouvel utilisateur en BDD
     * 
     * @author nicolas
     *
     */
    public void insert(Utilisateur utilisateur) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement insert = cnx.prepareStatement(INSERT);

	    insert.setString(1, utilisateur.getPseudo());
	    insert.setString(2, utilisateur.getNom());
	    insert.setString(3, utilisateur.getPrenom());
	    insert.setString(4, utilisateur.getEmail());
	    insert.setString(5, utilisateur.getTelephone());
	    insert.setString(6, utilisateur.getRue());
	    insert.setString(7, utilisateur.getCodePostal());
	    insert.setString(8, utilisateur.getVille());
	    insert.setString(9, utilisateur.getMotDePasse());
	    insert.setInt(10, 100);
	    insert.setInt(11, 0);

	    insert.executeUpdate();

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.INSERT, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    /**
     * Methode qui supprime un utilisateur en BDD
     * 
     * @param id
     * @author nicolas
     */
    @Override
    public void delete(int id) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement delete = cnx.prepareStatement(DELETE);

	    delete.setInt(1, id);

	    delete.executeUpdate();

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.DELETE, this.getClass().getSimpleName());
	    throw de;
	}
    }

    @Override
    public void update(Utilisateur utilisateur) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement update = cnx.prepareStatement(UPDATE);

	    update.setString(1, utilisateur.getPseudo());
	    update.setString(2, utilisateur.getNom());
	    update.setString(3, utilisateur.getPrenom());
	    update.setString(4, utilisateur.getEmail());
	    update.setString(5, utilisateur.getTelephone());
	    update.setString(6, utilisateur.getRue());
	    update.setString(7, utilisateur.getCodePostal());
	    update.setString(8, utilisateur.getVille());
	    update.setInt(9, utilisateur.getId());

	    update.executeUpdate();

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.UPDATE, this.getClass().getSimpleName(), e);
	    throw de;
	}

    }

    /**
     * Sert à retourner un utilisateur en tant que vendeur et vendeur seulement
     * Méthode apellée lorsqu'on veut assigner un vendeur à un article sans avoir
     * besoin d'en savoir plus sur cet utilisateur. Signifie que les enchères
     * émises par cet utilisateur ne seront pas connues/!\ Utilisé par le
     * SelectAll de ArticleDAO Sert à éviter les boucles infinies
     */
    @Override
    public Utilisateur selectByIdAsVendeur(int id) throws DALException {
	Utilisateur u;
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
		u = itemBuilderAsVendeur(rs);
	    } else {
		DALException de = new DALException(Errors.NO_DATA_FOUND, this.getClass().getSimpleName());
		throw de;
	    }
	} catch (SQLException e) {
	    DALException de = new DALException(Errors.SELECT_BY_ID_AS_VENDEUR, this.getClass().getSimpleName(), e);
	    throw de;
	}
	return u;
    }

    /**
     * Sert à retourner un utilisateur en tant qu'émetteur et émetteur seulement.
     * Méthode apellée lorsqu'on veut assigner un émetteur à une enchère sans
     * avoir besoin d'en savoir plus sur cet utilisateur. Signifie que les enchères
     * émises par cet utilisateur ne seront pas connues/!\ Utilisé par le
     * SelectByArticle de EnchereDAO. Sert à éviter les boucles infinies
     */
    @Override
    public Utilisateur selectByIdAsEmetteur(int id) throws DALException {
	return this.selectByIdAsVendeur(id);
    }

    @Override
    public Utilisateur selectById(int id) throws DALException {
	Utilisateur u;
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();

	    if (rs.next()) {
		u = itemBuilder(rs);
	    } else {
		DALException de = new DALException(Errors.NO_DATA_FOUND, this.getClass().getSimpleName());
		throw de;
	    }

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.SELECT_BY_ID, this.getClass().getSimpleName(), e);
	    throw de;
	}
	return u;
    }
    
    @Override
    public boolean hasDuplicates(String field) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement pstmt = cnx.prepareStatement(GET_DUPLICATES);
	    pstmt.setString(1, field);
	    pstmt.setString(2, field);

	    ResultSet rs = pstmt.executeQuery();
	    
	    return (rs.next())? true : false;
	    
	} catch (SQLException e) {
	    DALException de = new DALException(Errors.SEARCH_DUPLICATES, this.getClass().getSimpleName(), e);
	    throw de;
	}
    }

    /*
     * @Override public void updateMDP (Utilisateur utilisateur) throws
     * BusinessException {
     * 
     * try (Connection cnx = ConnectionProvider.getConnection()) { PreparedStatement
     * update = cnx.prepareStatement(UPDATE_MDP);
     * 
     * update.setString(1, utilisateur.getMotDePasse());
     * 
     * update.executeUpdate();
     * 
     * 
     * 
     * } catch (SQLException e) { e.printStackTrace(); BusinessException be = new
     * BusinessException(); be.addError("ERROR DB - " + e.getMessage()); throw be; }
     * 
     * 
     * 
     * 
     * }
     */

    /*--ITEM BUILDERS------------------------------------------------------------*/
    /**
     * Base pour les itemBuilders.
     * 
     * @param rs
     * @return
     * @throws SQLException
     * @throws BusinessException
     */
    private Utilisateur baseBuilder(ResultSet rs) throws SQLException, DALException {
	Utilisateur u = new Utilisateur();
	u.setId(rs.getInt("id"));
	u.setPseudo(rs.getString("pseudo"));
	u.setNom(rs.getString("nom"));
	u.setPrenom(rs.getString("Prenom"));
	u.setEmail(rs.getString("email"));
	u.setTelephone(rs.getString("telephone"));
	u.setRue(rs.getString("rue"));
	u.setCodePostal(rs.getString("code_postal"));
	u.setVille(rs.getString("ville"));
	u.setCredit(rs.getInt("credit"));
	u.setAdministrateur((rs.getByte("administrateur") == 1) ? true : false);
	return u;
    }

    private Utilisateur itemBuilder(ResultSet rs) throws SQLException, DALException {
	Utilisateur u = this.baseBuilder(rs);
	u.setArticlesEnVentes(DALFactory.getArticleDal().selectByVendeur(u));
	u.setEncheres(DALFactory.getEnchereDal().selectByUtilisateur(u));
	return u;
    }

    /***
     * Méthode apellée par SelectByIdAsVendeur : permet de retourner un
     * utilisateur sans ses enchères mais seulement avec ses articles à vendre.
     * 
     * @param rs
     * @return
     * @throws SQLException
     * @throws BusinessException
     */
    private Utilisateur itemBuilderAsVendeur(ResultSet rs) throws SQLException, DALException {
	Utilisateur u = this.baseBuilder(rs);
	u.setArticlesEnVentes(DALFactory.getArticleDal().selectByVendeur(u));
	u.setEncheres(null);
	return u;
    }
}
