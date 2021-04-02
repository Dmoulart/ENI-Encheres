package fr.eni.troc.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Utilisateur u = new Utilisateur();
		if(newpseudo.equals(u.getPseudo())) {
		    	u.setPseudo(newpseudo);
		} else if (newnom.equals(u.getNom())) {
			u.setNom(newnom);
		}  else if (newprenom.equals(u.getPrenom())) {
			u.setNom(newprenom);
		} else if (newemail.equals(u.getEmail())) {
			u.setNom(newemail);
		}  else if (newtelephone.equals(u.getTelephone())) {
			u.setNom(newtelephone);
		} else if (newrue.equals(u.getRue())) {
			u.setNom(newrue);
		}  else if (newcodePostal.equals(u.getCodePostal())) {
			u.setNom(newcodePostal);
		} else if (newville.equals(u.getVille())) {
			u.setNom(newville);
		}  else if (newmotDePasse.equals(u.getMotDePasse())) {
			u.setNom(newmotDePasse);
		}
		UtilisateurManager.getUtilisateurManager().update(u);
		response.sendRedirect(request.getContextPath() + "/ProfilServlet");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
