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
@WebServlet("/ModifierProfilServlet")
public class ModifierProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	HttpSession session = request.getSession();
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurEnSession");
	Utilisateur utilisateurModifie = new Utilisateur(); 

	// Récupération de la saisie
	request.setCharacterEncoding("UTF-8");

	String newpseudo = request.getParameter("pseudoUtilisateur").trim();
	String newnom = request.getParameter("nomUtilisateur").trim();
	String newprenom = request.getParameter("prenomUtilisateur").trim();
	String newemail = request.getParameter("emailUtilisateur").trim();
	String newtelephone = request.getParameter("telephoneUtilisateur").trim();
	String newrue = request.getParameter("rueUtilisateur").trim();
	String newcodePostal = request.getParameter("codePostalUtilisateur").trim();
	String newville = request.getParameter("villeUtilisateur").trim();
	String newmotDePasse = request.getParameter("mdpUtilisateur").trim();
	String confiMotDePasse = request.getParameter("mdpConfUtilisateur").trim();

	System.out.println("Pseudo : " + newpseudo);
	System.out.println("Nom : " + newnom);
	System.out.println("Prenom : " + newprenom);
	System.out.println("Email : " + newemail);
	System.out.println("Telephone : " + newtelephone);
	System.out.println("Rue : " + newrue);
	System.out.println("Code Postal : " + newcodePostal);
	System.out.println("Ville : " + newville);
	System.out.println("Mot de Passe : " + newmotDePasse);
	

	// Vérification pour savoir qu'elle sont les infos à modifié sur l'utilisateur
	try {

	    	utilisateurModifie.setId(utilisateur.getId());
		utilisateurModifie.setPseudo(newpseudo);	   
		utilisateurModifie.setNom(newnom);
		utilisateurModifie.setPrenom(newprenom);
		utilisateurModifie.setEmail(newemail);
		utilisateurModifie.setTelephone(newtelephone);
		utilisateurModifie.setRue(newrue);
		utilisateurModifie.setCodePostal(newcodePostal);
		utilisateurModifie.setVille(newville);
		utilisateurModifie.setMotDePasse(newmotDePasse);
		utilisateurModifie.setCredit(utilisateur.getCredit());
	    
	    System.out.println("Id utilisateur : " + utilisateurModifie.getId());

	    // Appel à la BLL
	    
	    UtilisateurManager.getUtilisateurManager().verifUpdateUtilisateur(utilisateurModifie, confiMotDePasse);
	    utilisateur = utilisateurModifie; 
	    System.out.println(utilisateur.toString());
	    UtilisateurManager.getUtilisateurManager().update(utilisateur);
	    System.out.println(utilisateur.toString());
	    session.setAttribute("utilisateurEnSession", UtilisateurManager.getUtilisateurManager().selectById(utilisateur.getId()));
	    System.out.println("UPDATED");
	    response.sendRedirect(request.getContextPath() + "/ProfilServlet");
	} catch (BusinessException e) {
	    request.setAttribute("errors", e.getErrors());
	    request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	    e.printStackTrace();
	}
    }
}
