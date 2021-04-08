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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId"));
	String utilisateurId = request.getParameter("utilisateurId");
	System.out.println(utilisateurId);
	/*
	    UtilisateurManager.getUtilisateurManager().selectById(utilisateurId);

		/*Utilisateur utilisateur;
		try {
		    utilisateur = UtilisateurManager.getUtilisateurManager().selectById(id);
			//Appel de la BLL
			try {
			    UtilisateurManager.getUtilisateurManager().delete(utilisateur.getId());
			    System.out.println("Id a supprimer : " + utilisateur.getId());
			} catch (BusinessException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		} catch (BusinessException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

	 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
