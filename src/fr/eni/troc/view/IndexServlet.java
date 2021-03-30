package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.exception.BusinessException;

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
		Article article = new Article();
			article.setNom("TEST_NOM");
			article.setDescription("TEST_DESCRIPTION");
			article.setDebutEncheres(LocalDate.now());
			article.setFinEcheres(LocalDate.now());
			article.setPrixVente(100);
			article.setCategorie(new Categorie());
		System.out.println(article);
		try {
			DALFactory.getArticleDal().insert(article);
		} catch (BusinessException e) {
			e.addError("ERROR INDEX SERVLET");
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
