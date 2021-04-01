package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.UtilisateurManager;


/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudoUtilisateur");
		String nom = request.getParameter("nomUtilisateur");
		String prenom = request.getParameter("prenomUtilisateur");
		String email = request.getParameter("emailUtilisateur");
		String telephone = request.getParameter("teleponeUtilisateur");
		String rue = request.getParameter("rueUtilisateur");
		String codePostal = request.getParameter("codePostalUtilisateur");
		String ville = request.getParameter("villeUtilisateur");
		String motDePasse = request.getParameter("mdpUtilisateur");
			
		System.out.println("Pseudo : " + pseudo);
		System.out.println("Nom : " + nom);
		System.out.println("Prenom : " + prenom);
		System.out.println("Email : " + email);
		System.out.println("Telephone : " + telephone);
		System.out.println("Rue : " + rue);
		System.out.println("Code Postal : " + codePostal);
		System.out.println("Ville : " + ville);
		System.out.println("Mot de Passe : " + motDePasse);
		
		if(pseudo != null && nom != null && prenom != null && email != null && telephone != null && rue != null && codePostal != null && ville != null && motDePasse != null) {
			try {
				Utilisateur utilisateur = new Utilisateur();
					utilisateur.setPseudo(pseudo);
					utilisateur.setNom(nom);
					utilisateur.setPrenom(prenom);
					utilisateur.setEmail(email);
					utilisateur.setTelephone(telephone);
					utilisateur.setRue(rue);
					utilisateur.setCodePostal(codePostal);
					utilisateur.setVille(ville);
					utilisateur.setMotDePasse(motDePasse);
				UtilisateurManager.getUtilisateurManager().creer(utilisateur);
				response.sendRedirect(request.getContextPath() + "/WEB-INF/index.jsp");
				System.out.println("Ajout d'un nouvel Utilisateur de la Base de donnée !");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

}
