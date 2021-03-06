package fr.eni.troc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.exception.Errors;

public class UtilisateurDAOJdbcImpl implements UtilisateurDal {

    public static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE id=?";


    public static final String FIND = "SELECT id, pseudo, prenom, nom, email, telephone, rue, code_postal, ville, credit, administrateur, mot_de_passe FROM utilisateurs WHERE pseudo=? AND mot_de_passe=? ";

    public static final String SELECT_BY_EMAIL = "SELECT id, pseudo, prenom, nom, email, telephone, rue, code_postal, ville, credit, administrateur, mot_de_passe FROM utilisateurs WHERE email=? AND mot_de_passe=?";
    
    public static final String INSERT = "INSERT INTO utilisateurs (id, pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)\r\n"
	    + "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

    public static final String DELETE = "DELETE FROM utilisateurs WHERE id= ?";

    public static final String UPDATE = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, credit=? WHERE id=?";
    
    public static final String UPDATE_MDP = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE id=?";
    
    public static final String GET_DUPLICATES_PSEUDO = "SELECT pseudo, COUNT(*) c FROM utilisateurs WHERE pseudo=? GROUP BY pseudo HAVING c >= 1;";

    public static final String GET_DUPLICATES_EMAIL = "SELECT email, COUNT(*) c FROM utilisateurs WHERE email=? GROUP BY email HAVING c >= 1;";
    
    public static final String SELECT_ALL ="SELECT  id, pseudo, prenom, nom, email, telephone, rue, code_postal, ville, credit, administrateur FROM utilisateurs FROM Utilisateurs";
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
			u.setAdministrateur(rs.getBoolean("administrateur"));
			u.setMotDePasse(rs.getString("mot_de_passe"));
		
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
		u.setAdministrateur(rs.getBoolean("administrateur"));
		u.setMotDePasse(rs.getString("mot_de_passe"));

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
	    PreparedStatement insert = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

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
	    
	    
	    ResultSet generatedKey = insert.getGeneratedKeys(); 
	    if ( generatedKey != null && generatedKey.next()) {
		    utilisateur.setId((int)generatedKey.getLong(1)); 
		}
		else {
		    throw new DALException();
		}

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
	    update.setInt(9, utilisateur.getCredit());
	    update.setInt(10, utilisateur.getId());
	    
	    update.executeUpdate();

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.UPDATE, this.getClass().getSimpleName(), e);
	    throw de;
	}

    }
    
    @Override
    public void updateMdp(Utilisateur utilisateur) throws DALException {

	try (Connection cnx = ConnectionProvider.getConnection()) {
	    PreparedStatement update = cnx.prepareStatement(UPDATE_MDP);

	    update.setString(1, utilisateur.getPseudo());
	    update.setString(2, utilisateur.getNom());
	    update.setString(3, utilisateur.getPrenom());
	    update.setString(4, utilisateur.getEmail());
	    update.setString(5, utilisateur.getTelephone());
	    update.setString(6, utilisateur.getRue());
	    update.setString(7, utilisateur.getCodePostal());
	    update.setString(8, utilisateur.getVille());
	    update.setString(9, utilisateur.getMotDePasse());
	    update.setInt(10, utilisateur.getCredit());
	    update.setInt(11, utilisateur.getId());
	    
	    update.executeUpdate();

	} catch (SQLException e) {
	    DALException de = new DALException(Errors.UPDATE, this.getClass().getSimpleName(), e);
	    throw de;
	}

    }

    /**
     * Sert ?? retourner un utilisateur en tant que vendeur et vendeur seulement
     * M??thode apell??e lorsqu'on veut assigner un vendeur ?? un article sans avoir
     * besoin d'en savoir plus sur cet utilisateur. Signifie que les ench??res ??mises
     * par cet utilisateur ne seront pas connues/!\ Utilis?? par le SelectAll de
     * ArticleDAO Sert ?? ??viter les boucles infinies
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
     * Sert ?? retourner un utilisateur en tant qu'??metteur et ??metteur seulement.
     * M??thode apell??e lorsqu'on veut assigner un ??metteur ?? une ench??re sans avoir
     * besoin d'en savoir plus sur cet utilisateur. Signifie que les ench??res ??mises
     * par cet utilisateur ne seront pas connues/!\ Utilis?? par le SelectByArticle
     * de EnchereDAO. Sert ?? ??viter les boucles infinies
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
    public boolean hasDuplicates(String field, String value) throws DALException {
	String statement = ";";
	try (Connection cnx = ConnectionProvider.getConnection()) {
	    switch (field) {
	    case "email":
		statement = GET_DUPLICATES_EMAIL;
		break;
	    case "pseudo":
		statement = GET_DUPLICATES_PSEUDO;
		break;
	    default:
		throw new DALException("Impossible de savoir quelle duplication chercher",
			this.getClass().getSimpleName());

	    }
	    PreparedStatement pstmt = cnx.prepareStatement(statement);
	    pstmt.setString(1,value);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while (rs.next()) {
		System.out.println(rs.getInt("c") + " " + field);
		return (rs.getInt("c") >= 1) ? true : false;
	    }
	} catch (SQLException e) {
	    DALException de = new DALException(Errors.SEARCH_DUPLICATES, this.getClass().getSimpleName(), e);
	    throw de;
	}
	return false;
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
     * M??thode apell??e par SelectByIdAsVendeur : permet de retourner un utilisateur
     * sans ses ench??res mais seulement avec ses articles ?? vendre.
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

    @Override
    public List<Utilisateur> selectAll() throws DALException {
	List<Utilisateur> utilisateurs = new ArrayList<>();
	try(Connection cnx = ConnectionProvider.getConnection()) {
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while(rs.next()) {
			Utilisateur utilisateur = itemBuilder(rs);
			utilisateurs.add(utilisateur);
		}
	} catch (Exception e) {
		DALException de = new DALException(Errors.SELECT_ALL,this.getClass().getSimpleName(),e);
		throw de;
	}
	return null;
    }
}