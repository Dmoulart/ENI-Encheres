package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    	HttpSession session = request.getSession();
	    	Utilisateur u = (Utilisateur) session.getAttribute("utilisateurEnSession");
	   
	    	// Récupération de la saisie
	    	request.setCharacterEncoding("UTF-8");
	    	
		String newpseudo = request.getParameter("pseudoUtilisateur");
		String newnom = request.getParameter("nomUtilisateur");
		String newprenom = request.getParameter("prenomUtilisateur");
		String newemail = request.getParameter("emailUtilisateur");
		String newtelephone = request.getParameter("teleponeUtilisateur");
		String newrue = request.getParameter("rueUtilisateur");
		String newcodePostal = request.getParameter("codePostalUtilisateur");
		String newville = request.getParameter("villeUtilisateur");
		String newmotDePasse = request.getParameter("mdpUtilisateur");
			
		System.out.println("Pseudo : " + newpseudo);
		System.out.println("Nom : " + newnom);
		System.out.println("Prenom : " + newprenom);
		System.out.println("Email : " + newemail);
		System.out.println("Telephone : " + newtelephone);
		System.out.println("Rue : " + newrue);
		System.out.println("Code Postal : " + newcodePostal);
		System.out.println("Ville : " + newville);
		System.out.println("Mot de Passe : " + newmotDePasse);
		
		try {
		
		
		if (!newpseudo.equals(u.getPseudo())) {
		    	u.setPseudo(newpseudo);}
		if (!newnom.equals(u.getNom())) {
			u.setNom(newnom);}
		if (!newprenom.equals(u.getPrenom())) { 
			u.setPrenom(newprenom);}
		if (!newemail.equals(u.getEmail())) {
			u.setEmail(newemail);}
		if (!newtelephone.equals(u.getTelephone())) {
			u.setTelephone(newtelephone);}
		if (!newrue.equals(u.getRue())) {
			u.setRue(newrue);}
		if (!newcodePostal.equals(u.getCodePostal())) {
			u.setCodePostal(newcodePostal);}
		if (!newville.equals(u.getVille())) {
			u.setVille(newville);}
		if (!newmotDePasse.equals(u.getMotDePasse())) {
			u.setMotDePasse(newmotDePasse);
		}
		System.out.println("Id utilisateur : " + u.getId());
		UtilisateurManager.getUtilisateurManager().update(u);
		session.setAttribute("utilisateurEnSession",UtilisateurManager.getUtilisateurManager().selectById(u.getId()));
		System.out.println("UPDATED");
		response.sendRedirect(request.getContextPath() + "/ProfilServlet");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
