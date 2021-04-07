package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.bo.Utilisateur;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");

	HttpSession session = request.getSession();

	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurEnSession");
	/*
	 * System.out.println("---------PARAMETER MAP--------------"); Map<String,
	 * String[]> parameters = request.getParameterMap();
	 * 
	 * request.getParameterMap().forEach((k,v) -> { System.out.println(k + " : ");
	 * Arrays.asList(v).forEach(s -> System.out.println(s)); });
	 */

	List<Article> articles = new ArrayList<>();
	List<Categorie> categories = new ArrayList<>();

	String categorieSelectionnee = (request.getParameter("selectCategorie") == null) ? "Toutes"
		: request.getParameter("selectCategorie");

	String motsRecherches = request.getParameter("searchContent");

	// Charge les categories
	try {
	    categories = CategorieManager.getCategorieManager().selectAll();
	} catch (BusinessException e1) {
	    e1.printStackTrace();
	}

	// Filtre les articles
	try {

	    articles = ArticleManager.getArticleManager().selectAll();
	    if (categorieSelectionnee != null && !categorieSelectionnee.equals("Toutes")) {
		articles = articles.stream()
			.filter(a -> a.getCategorie().getLibelle().toLowerCase().replaceAll("\\s", "")
				.equals(categorieSelectionnee.toLowerCase().replaceAll("\\s", "")))
			.collect(Collectors.toList());
	    }

	    if (motsRecherches != null) {
		articles = articles.stream()
			.filter(a -> a.getNom().toLowerCase().replaceAll("\\s", "")
				.contains(motsRecherches.toLowerCase().replaceAll("\\s", "")))
			.collect(Collectors.toList());
	    }

	    if ("on".equals(request.getParameter("achats"))) {

		if ("on".equals(request.getParameter("encheresOuvertes"))) {
		    articles = articles.stream().filter(a -> a.getDebutEncheres().compareTo(LocalDate.now()) <= 0
			    && a.getFinEncheres().compareTo(LocalDate.now()) >= 0).collect(Collectors.toList());
		}

		if ("on".equals(request.getParameter("mesEncheres"))) {
		    articles = articles.stream()
			    .filter(a -> a.getEncheres().get(0).getEmetteur().getId() == utilisateur.getId())
			    .collect(Collectors.toList());
		}
		// ToDo mes encheresRemportées !
	    }

	    if ("on".equals(request.getParameter("mesVentes"))) {

		if ("on".equals(request.getParameter("ventesEnCours"))) {
		    articles = articles.stream()
			    .filter(a -> a.getVendeur().getId() == utilisateur.getId()
				    && a.getDebutEncheres().compareTo(LocalDate.now()) <= 0
				    && a.getFinEncheres().compareTo(LocalDate.now()) >= 0)

			    .filter(a -> a.getVendeur().getId() == utilisateur.getId()).collect(Collectors.toList());
		}

		if ("on".equals(request.getParameter("ventesNonDebutees"))) {
		    articles = articles.stream().filter(a -> a.getDebutEncheres().compareTo(LocalDate.now()) > 0)
			    .collect(Collectors.toList());
		}

		if ("on".equals(request.getParameter("ventesTerminees"))) {
		    articles = articles.stream().filter(a -> a.getFinEncheres().compareTo(LocalDate.now()) < 0)
			    .collect(Collectors.toList());
		}

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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	doGet(request, response);
    }

    private String formated(String s) {
	return s.toLowerCase().replaceAll("\\s", "");
    }
}
