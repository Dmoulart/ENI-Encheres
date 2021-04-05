package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;
import fr.eni.troc.service.ArticleManager;
import fr.eni.troc.service.EnchereManager;
import fr.eni.troc.service.UtilisateurManager;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	Article article = null;
	try {
	    article = ArticleManager.getArticleManager()
		    .selectById(Integer.parseInt(request.getParameter("articleId")));
	} catch (BusinessException e) {
	    e.printStackTrace();
	}
	request.setAttribute("article", article);
	request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	HttpSession session = request.getSession();

	int montant = Integer.parseInt(request.getParameter("montantEnchere"));
	int articleId = Integer.parseInt(request.getParameter("articleId"));
	int prixVentePrecedent = 0;

	Utilisateur u = (Utilisateur) session.getAttribute("utilisateurEnSession");
	Enchere e = new Enchere();
	Article a = null;

	try {

	    a = ArticleManager.getArticleManager().selectById(articleId); // On récupère l'article
	    prixVentePrecedent = a.getPrixVente();
	    a.setPrixVente(montant);

	    e.setArticle(a); // On assigne l'article à l'enchère
	    e.setEmetteur(u); // On assigne l'utilisateur en tant qu'emetteur de l'enchère
	    e.setDate(LocalDate.now());
	    e.setMontant(montant);

	    List<Enchere> encheresUtilisateur = EnchereManager.getEnchereManager().selectByUtilisateur(u);

	    List<Enchere> encheresUtilisateurSurMemeArticle = encheresUtilisateur.stream()
		    .filter(enchere -> enchere.getArticle().getId() == articleId)
		    .sorted(Comparator.comparingInt(Enchere::getMontant)).collect(Collectors.toList());

	    if (encheresUtilisateurSurMemeArticle.size() >= 1) {

		int dernierMontantSurMemeArticle = encheresUtilisateurSurMemeArticle
			.get(encheresUtilisateurSurMemeArticle.size() - 1).getMontant();

		u.setCredit((u.getCredit() - montant) + dernierMontantSurMemeArticle);
	    } else {
		u.setCredit(u.getCredit() - montant);
	    }
	    EnchereManager.getEnchereManager().insert(e, prixVentePrecedent); // On insère l'enchère
	    UtilisateurManager.getUtilisateurManager().update(u); // On update l'utilisateurs(nombre de points)
	    ArticleManager.getArticleManager().update(e.getArticle()); // On update le prix de vente de l'article

	    request.setAttribute("article", a);

	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);

	} catch (BusinessException be1) {
	    be1.printStackTrace();
	    request.setAttribute("errors", be1.getErrors());
	    request.setAttribute("article", a);
	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}

    }

}
