package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Retrait;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.RetraitManager;


/**
 * Servlet implementation class VenteServlet
 */
@WebServlet("/VenteServlet")
public class VenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/vente.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String nom = request.getParameter("nomArticle");
	String description = request.getParameter("descriptionArticle");
	int categorie = Integer.parseInt(request.getParameter("categorie"));
	String photo = request.getParameter("photoEnchere");
	int misePrix = Integer.parseInt(request.getParameter("prixEnchere"));
	LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutEnchere"));
	LocalDate finEnchere = LocalDate.parse(request.getParameter("finEnchere"));
	String rueRetrait = request.getParameter("rueRetrait");
	String codePostalRetrait = request.getParameter("CodePostalRetrait");
	String villeRetrait = request.getParameter("villeRetrait");
	
	try {
	    Retrait retrait = new Retrait();
	    Article article = new Article();
            System.out.println("Nouvel Article");
            article.setNom(nom);
            article.setDescription(description);
            article.setCategorie(DALFactory.getCategorieDal().selectById(categorie));
            article.setPrixInitial(misePrix);
            article.setPrixVente(misePrix);//Le prix de vente est identique au prix initial lorsque l'article est crée - Dorian
            article.setDebutEncheres(debutEnchere);
            article.setFinEcheres(finEnchere);
            System.out.println("Fin Enchère");
            HttpSession session = request.getSession();
            article.setVendeur((Utilisateur) session.getAttribute("utilisateurEnSession"));
            
            
            retrait.setRue(rueRetrait);
            retrait.setVille(villeRetrait);
            retrait.setCodePostal(codePostalRetrait);
            
        	    try {	 
        		
        		 int articleId = (int)ArticleManager.getArticleManager().creer(article);
        		
        		 article.setId(articleId);
        		 retrait.setArticle(article);
        		 
        		 RetraitManager.getRetraitManager().insert(retrait);
        		 
        		 session.setAttribute("article", article);
        		 request.getRequestDispatcher("./IndexServlet").forward(request, response);
        		 System.out.println("Ajout d'un nouvel Article dans la Base de donnée !");
        	     } catch (BusinessException e) { 
        		request.setAttribute("errors", e.getErrors());
        		request.getRequestDispatcher("/WEB-INF/vente.jsp").forward(request, response);
        	     }
	} catch (DALException e) {
		System.out.println("LUL");
		e.printStackTrace();
	}
    }
}
