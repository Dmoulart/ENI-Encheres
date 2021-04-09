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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	String pseudo = request.getParameter("pseudoUtilisateur").trim();
	String nom = request.getParameter("nomUtilisateur").trim();
	String prenom = request.getParameter("prenomUtilisateur").trim();
	String email = request.getParameter("emailUtilisateur").trim();
	String telephone = request.getParameter("teleponeUtilisateur").trim();
	String rue = request.getParameter("rueUtilisateur").trim();
	String codePostal = request.getParameter("codePostalUtilisateur").trim();
	String ville = request.getParameter("villeUtilisateur").trim();
	String motDePasse = request.getParameter("mdpUtilisateur").trim();
	String confiMotDePasse = request.getParameter("mdpConfUtilisateur").trim();

	
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
	    utilisateur.setCredit(100);
	try {
	    UtilisateurManager.getUtilisateurManager().creer(utilisateur, confiMotDePasse);
	    HttpSession session = request.getSession();
	    
	    session.setAttribute("utilisateurEnSession",utilisateur);
	    request.getRequestDispatcher("./IndexServlet").forward(request, response);
	} catch (BusinessException e) {
	    request.setAttribute("errors", e.getErrors());
	    request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

    }

}
