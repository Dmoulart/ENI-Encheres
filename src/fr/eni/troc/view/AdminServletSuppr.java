package fr.eni.troc.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.UtilisateurDal;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServletSuppr")
public class AdminServletSuppr extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId"));
	try {
	    UtilisateurManager.getUtilisateurManager().delete(utilisateurId);
	    System.out.println("Id a supprimer : " + utilisateurId);
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	request.getRequestDispatcher("./IndexServlet").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }

}
