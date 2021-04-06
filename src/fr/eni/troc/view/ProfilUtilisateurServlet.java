package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ProfilUtilisateur
 */
@WebServlet("/ProfilUtilisateurServlet")
public class ProfilUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Utilisateur utilisateur = null;
		try {
		    utilisateur = UtilisateurManager.getUtilisateurManager()
			    .selectById(Integer.parseInt(request.getParameter("utilisateurId")));
		} catch (BusinessException e) {
		    e.printStackTrace();
		}
		request.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/profilUtilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
