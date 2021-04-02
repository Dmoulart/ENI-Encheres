package fr.eni.troc.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.ConnectionProvider;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/ConnectionServlet")
public class ConnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);

	// testPoolConnection();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// Récupération de la saisie
	request.setCharacterEncoding("UTF-8");
	String identifiant = request.getParameter("identifiantUtilisateur");
	String motDePasse = request.getParameter("motDePasse");

	try {
	    // Email REGEX
	    Utilisateur u = identifiant.matches("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})")
		    ? UtilisateurManager.getUtilisateurManager().validateConnectionWithEmail(identifiant, motDePasse)
		    : UtilisateurManager.getUtilisateurManager().validateConnection(identifiant, motDePasse);

	    HttpSession session = request.getSession();
	    session.setAttribute("utilisateurEnSession", u);
	    request.setAttribute("utilisateur", u);
	    request.getRequestDispatcher("./IndexServlet").forward(request, response);
	} catch (BusinessException e) {
	    e.printStackTrace();
	    request.setAttribute("errors", e.getErrors());
	    request.getRequestDispatcher("/WEB-INF/connection.jsp").forward(request, response);
	}
    }

    /**
     * Méthode pour valider la configuration de la base de données
     */
    private void testPoolConnection() {
	try {
	    Connection cnx = ConnectionProvider.getConnection();
	    System.out.println("La connexion est " + (cnx.isClosed() ? "FERMEE" : "OUVERTE"));
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}

