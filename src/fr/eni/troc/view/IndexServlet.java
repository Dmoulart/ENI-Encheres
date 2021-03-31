package fr.eni.troc.view;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		
	try {
		Utilisateur utilisateurTest = new Utilisateur(); 
		
		utilisateurTest.setPseudo("NicoDu35");
		utilisateurTest.setNom("Nicolas");
		utilisateurTest.setPrenom("TEST");
		utilisateurTest.setEmail("pierre.dupont@wanadoo.fr");
		utilisateurTest.setTelephone("0652698974");
		utilisateurTest.setRue("15, Avenue du test");
		utilisateurTest.setCodePostal("35700");
		utilisateurTest.setVille("Rennes");
		utilisateurTest.setMotDePasse("Azerty05");
		
		System.out.println(utilisateurTest.toString());
		
		UtilisateurManager.getUtilisateurManager().creerUtilisateur(utilisateurTest);
		
		utilisateurTest.setId(7);
		utilisateurTest.setNom("TEST_UPDATE");
		utilisateurTest.setPrenom("TEST_PRENOM_UPDATE");
		utilisateurTest.setVille("Limoges");
		UtilisateurManager.getUtilisateurManager().updateUtilisateur(utilisateurTest);
		
		UtilisateurManager.getUtilisateurManager().deleteUtilisateur(8);
		
		
	}catch (BusinessException be) {
		
		be.printStackTrace(); 
	}
	
	
		
		/*pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
