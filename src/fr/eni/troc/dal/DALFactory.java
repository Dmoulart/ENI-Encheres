package fr.eni.troc.dal;

public class DALFactory {
	//FACTORY UTILISATEURS
	public static UtilisateurDal getUtilisateurDal() {
		UtilisateurDal utilisateurDal = new UtilisateurDAOJdbcImpl();
		return new UtilisateurDAOJdbcImpl();
	}
	
	//FACTORY ARTICLES
	public static ArticleDal getArticleDal() {
		ArticleDal articleDal = new ArticleDAOJdbcImpl();
		return new ArticleDAOJdbcImpl();
	}
	
	//FACTORY CATEGORIES
	public static CategorieDal getCategorieDal() {
		CategorieDal categorieDal = new CategorieDAOJdbcImpl();
		return new CategorieDAOJdbcImpl();
	}
	
	//FACTORY RETRAITS
	public static RetraitDal getRetraitDal() {
		RetraitDal retraitDal = new RetraitDAOJdbcImpl();
		return new RetraitDAOJdbcImpl();
	}
	
	//FACTORY ENCHERES
	public static EnchereDal getEnchereDal() {
		EnchereDal enchereDal = new EnchereDAOJdbcImpl();
		return new EnchereDAOJdbcImpl();
	}
}
