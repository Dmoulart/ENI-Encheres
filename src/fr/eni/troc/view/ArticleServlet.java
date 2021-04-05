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
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.ArticleDAOJdbcImpl;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.EnchereManager;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Article article = null;
	try {
	    article = ArticleManager.getArticleManager()
		    .selectById(Integer.parseInt(request.getParameter("articleId")));
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	request.setAttribute("article", article);
	request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	//EN COURS
	HttpSession session = request.getSession();
	int montant = Integer.parseInt(request.getParameter("montantEnchere"));
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	
	Utilisateur u = (Utilisateur) session.getAttribute("utilisateurEnSession");
	Enchere e = new Enchere();
	
	try {
	    e.setArticle(ArticleManager.getArticleManager().selectById(articleId)); // On récupère l'article
	} catch (BusinessException be1) {
	    be1.printStackTrace();
	    request.setAttribute("errors", be1.getErrors());
	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}
	
	e.setEmetteur(u);
	e.setDate(LocalDate.now());
	e.setMontant(montant);
	System.out.println(e.toString());
	System.out.println(u.toString());
	
	try {
	    EnchereManager.getEnchereManager().insert(e); // On insère l'enchère
	} catch (BusinessException be2) {
	    be2.printStackTrace();
	    request.setAttribute("errors", be2.getErrors());
	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}
	u.setCredit(u.getCredit()-montant);
	
	try {
	    UtilisateurManager.getUtilisateurManager().update(e.getEmetteur()); // On retire les points à l'utilisateurs
	} catch (BusinessException be3) {
	    be3.printStackTrace();
	    request.setAttribute("errors", be3.getErrors());
	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}

	doGet(request, response);
    }

}
