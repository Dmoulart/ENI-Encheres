package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MotDePasseOublieServlet
 */
@WebServlet("/MotDePasseOublieServlet")
public class MotDePasseOublieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/motdepasseoublie.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	/*
	 * //Récupération de la saisie request.setCharacterEncoding("UTF-8"); String
	 * identifiant = request.getParameter("identifiantUtilisateur"); String
	 * motDePasse = request.getParameter("nvxMotDePasse"); Utilisateur
	 * utilisateurAModifie = null; utilisateurAModifie.getPseudo();
	 * 
	 * 
	 * //Appel de la BLL
	 * 
	 * try {
	 * 
	 * UtilisateurManager.getUtilisateurManager().creerNvxMdp(utilisateurAModifie);
	 * 
	 * } catch (BusinessException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 */

    }
}
