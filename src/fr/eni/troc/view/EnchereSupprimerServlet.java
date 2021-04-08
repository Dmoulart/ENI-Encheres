package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.EnchereManager;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/EnchereSupprimerServlet")
public class EnchereSupprimerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	Article article = null;
	System.out.println(request.getParameter("articleId"));
	int articleId = Integer.parseInt(request.getParameter("articleId"));
    	
    	
    	//Appel a la BLL
    	try {
    	    article = ArticleManager.getArticleManager().selectById(Integer.parseInt(request.getParameter("articleId")));
    	    if(article.getDebutEncheres().isAfter(LocalDate.now()) ||
    		article.getFinEncheres().isBefore(LocalDate.now())) {
		request.setAttribute("peutEncherir", "false");
	    }
	    else {
		request.setAttribute("peutEncherir", "true");
	    }
	    ArticleManager.getArticleManager().delete(articleId);
	} catch (BusinessException e) {
	    e.printStackTrace();
	    request.setAttribute("errors", e.getErrors());
	    request.getRequestDispatcher("/WEB-INF/vente.jsp").forward(request, response);
	}
    	request.getRequestDispatcher("./IndexServlet").forward(request, response);
        }
    

}
