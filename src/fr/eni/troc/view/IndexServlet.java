package fr.eni.troc.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.CategorieManager;


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
		request.setCharacterEncoding("UTF-8");
		
		List<Article> articles = new ArrayList<>();
		List<Categorie> categories = new ArrayList<>();
		
		String categorieSelectionnee = (request.getParameter("selectCategorie") == null )?
				"Toutes" 
				: 
				request.getParameter("selectCategorie");
		
		String motsRecherches = request.getParameter("searchContent");
		System.out.println("categorie selectionne : " + categorieSelectionnee);
		System.out.println("mot recherchés : " + motsRecherches);
		
		//Charge les categories
		try {
			categories = CategorieManager.getCategorieManager().selectAll();
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		//Charge les articles
		try {
			articles = ArticleManager.getArticleManager().selectAll();
			if(categorieSelectionnee != null && !categorieSelectionnee.equals("Toutes")) {
				articles = articles.stream()
						.filter(a -> a.getCategorie().getLibelle()
								.toLowerCase()
								.replaceAll("\\s","")
								.equals(categorieSelectionnee.toLowerCase().replaceAll("\\s","")))
						.collect(Collectors.toList());
			}
			
			if(motsRecherches != null) {
				articles = articles.stream()
						.filter(a -> a.getNom()
								.toLowerCase()
								.replaceAll("\\s","")
								.contains(motsRecherches.toLowerCase().replaceAll("\\s","")))
						.collect(Collectors.toList());
			}
		
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		articles.forEach(a -> System.out.println(a.toString()));
		
		request.setAttribute("categorieSelectionnee", categorieSelectionnee);
		request.setAttribute("motsRecherches", motsRecherches);
		request.setAttribute("articles", articles);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
