package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Article;
import fr.eni.troc.exception.BusinessException;

/**
 * Servlet implementation class VenteServlet
 */
@WebServlet("/VenteServlet")
public class VenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nomArticle");
		String description = request.getParameter("descriptionArticle");
		int categorie = Integer.parseInt(request.getParameter("categorieEnchere"));
		String photo = request.getParameter("photoEnchere");
		int misePrix = Integer.parseInt(request.getParameter("prixEnchere"));
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutEnchere"));
		LocalDate finEnchere = LocalDate.parse(request.getParameter("finEnchere"));
		String rueRetrait = request.getParameter("rueRetrait");
		String codePostalEnchere = request.getParameter("CodePostalRetrait");
		String villeEnchere = request.getParameter("villeRetrait");
		
		System.out.println("Nom : " + nom);
		System.out.println("Description : " + description);
		System.out.println("Categorie : " + categorie);
		System.out.println("Mise à prix : " + misePrix);
		System.out.println("Début de l'enchère : " + debutEnchere);
		System.out.println("Fin de l'enchère : " + finEnchere);
		System.out.println("Rue du rettrait : " + rueRetrait);
		System.out.println("Code Postal du retrait : " + codePostalEnchere);
		System.out.println("Ville du Retrait : " + villeEnchere);
		/*
		if(nom != null && description != null && categorie > 0 && photo != null && misePrix > 0 && debutEnchere != null && finEnchere != null && rueRetrait != null && codePostalEnchere != null && villeEnchere != null) {
			try {
				Article article = new Article();
				article.setNom(nom);
				article.setDescription(description);
				article.setCategorie(categorie);
				article.setPrixInitial(misePrix);
				article.setDebutEncheres(debutEnchere);
				article.setFinEcheres(finEnchere);
				article.setVendeur(vendeur);
			ArticleManager.
			response.sendRedirect(request.getContextPath() + "/WEB-INF/index.jsp");
			System.out.println("Ajout d'un nouvel Article de la Base de donnée !");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} */
	}
}
