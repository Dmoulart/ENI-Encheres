package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/SupprimerProfilServlet")
public class SupprimerProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	HttpSession session = request.getSession();
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurEnSession");
	
	//Appel de la BLL
	try {
	    UtilisateurManager.getUtilisateurManager().delete(utilisateur.getId());
	    System.out.println("Id a supprimer : " + utilisateur.getId());
	} catch (BusinessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	request.getRequestDispatcher("./DeconnectionServlet").forward(request, response);
    }

  
}
