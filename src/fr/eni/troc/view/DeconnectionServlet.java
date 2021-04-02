package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.troc.bo.Utilisateur;

/**
 * Servlet implementation class DeconnectionServlet
 */
@WebServlet("/DeconnectionServlet")
public class DeconnectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// Invalider la Session utilisateur
	HttpSession session = request.getSession();

	// Tracer l'utilisateur en session avant l'invalidation
	System.out.println("BEFORE");
	if (session.getAttribute("utilisateurEnSession") != null) {
	    Utilisateur u = (Utilisateur) session.getAttribute("utilisateurEnSession");
	    System.out.println(u);
	}

	session.invalidate();

	HttpSession sessionAfter = request.getSession();

	// Redirection vers la page Accueil
	request.getRequestDispatcher("./IndexServlet").forward(request, response);

	// Vérifier qu'il n'est plus présent après invalidation
	System.out.println("AFTER");
	if (sessionAfter.getAttribute("utilisateurEnSession") != null) {
	    Utilisateur u = (Utilisateur) sessionAfter.getAttribute("utilisateurEnSession");
	    System.out.println(u);

	}
    }
}
