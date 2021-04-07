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
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.exception.DALException;
import fr.eni.troc.service.ArticleManager;


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
	String codePostalEnchere = request.getParameter("CodePostalRetrait");
	String villeEnchere = request.getParameter("villeRetrait");
	
	try {
            Article article = new Article();
            System.out.println("Nouvel Article");
            article.setNom(nom);
            article.setDescription(description);
            article.setCategorie(DALFactory.getCategorieDal().selectById(categorie));
            article.setPrixInitial(misePrix);
            article.setDebutEncheres(debutEnchere);
            article.setFinEcheres(finEnchere);
            System.out.println("Fin Enchère");
            HttpSession session = request.getSession();
            article.setVendeur((Utilisateur) session.getAttribute("utilisateurEnSession"));

        	    try {	 
        		 ArticleManager.getArticleManager().creer(article);
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
