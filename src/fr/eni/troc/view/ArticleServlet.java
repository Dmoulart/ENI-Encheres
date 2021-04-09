package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
	HttpSession session = request.getSession();
	Article article = null;
	if(request.getParameter("articleId") == null) {
	    request.getRequestDispatcher("./IndexServlet").forward(request, response);
	}
	try {
	    article = ArticleManager.getArticleManager()
		    .selectById(Integer.parseInt(request.getParameter("articleId")));
	    
	    if(article.getDebutEncheres().isAfter(LocalDate.now()) ||
		    article.getFinEncheres().isBefore(LocalDate.now())) {
		request.setAttribute("peutEncherir", "false");
	    }
	    else {
		request.setAttribute("peutEncherir", "true");
	    }
	    
	    if(article.getFinEncheres().compareTo(LocalDate.now()) <= 0){
		request.setAttribute("peutEncherir", "articleVendu");
		
		if(article.getEncheres().size() == 0 ) {
		    request.setAttribute("acquereur", "aucun");
		}
		else {
		    
		    int idAcquereur =  article.getEncheres().get(article.getEncheres().size()-1).getEmetteur().getId();
		    
		    if(idAcquereur == ((Utilisateur)session.getAttribute("utilisateurEnSession")).getId()) {
			    request.setAttribute("acquereur", "utilisateurEnSession");
		    }
		    else {
			request.setAttribute("acquereur", article.getEncheres().get(article.getEncheres().size()-1).getEmetteur().getPseudo());
		    }
		}
		
	    }
	    
	    
	    
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
	
	boolean encherisseurPrecedentEstUtilisateurEnSession = false;
	
	Utilisateur u = (Utilisateur) session.getAttribute("utilisateurEnSession");
	Enchere e = new Enchere();
	Article a = null;
	
	int debitEncherisseur;
	
	
	try {

	    a = ArticleManager.getArticleManager().selectById(articleId); // On récupère l'article
	    a.setPrixVente(montant);
	    
	    
	    
	    e.setArticle(a); // On assigne l'article à l'enchère
	    e.setEmetteur(u); // On assigne l'utilisateur en tant qu'emetteur de l'enchère
	    e.setDate(LocalDate.now());
	    e.setMontant(montant);

	   
	    List<Enchere> encheresArticle = a.getEncheres();  // ON cherche l'enchèrisseur précédent pour lui réassigner ses crédits

	    Enchere encherePrecedente = null;
	    Utilisateur encherisseurPrecedant = null;
	    
	    if (encheresArticle.size() > 1) {
		
		encherePrecedente = encheresArticle.get(encheresArticle.size()-1);//Derniere enchere ajoutée 
		encherisseurPrecedant = encherePrecedente.getEmetteur();

		if (encherisseurPrecedant.getId() == u.getId()) {
		    
		    encherisseurPrecedentEstUtilisateurEnSession = true;
		    debitEncherisseur = montant -  encherePrecedente.getMontant();
		} 
		else {
		    encherisseurPrecedant.setCredit(encherisseurPrecedant.getCredit() + encherePrecedente.getMontant());
		    debitEncherisseur = montant;
		}
	    }
	    else
	    {
		debitEncherisseur = montant;
	    }
	    
	    //a.getEncheres().add(e);

	    EnchereManager.getEnchereManager().insert(e,a, debitEncherisseur ); // On insère l'enchère

	    u.setCredit(u.getCredit() - debitEncherisseur); // On débite l'utilisateur en session
	    
	    UtilisateurManager.getUtilisateurManager().update(u); // On update l'utilisateurs(nombre de points)
	    
	    if(encherisseurPrecedant != null && !encherisseurPrecedentEstUtilisateurEnSession) {
		UtilisateurManager.getUtilisateurManager().update(encherisseurPrecedant);// On update l'encherisseur
		     // précédent pour qu'il récupère
		     // ses points
	    }
	    
	    ArticleManager.getArticleManager().update(e.getArticle()); // On update le prix de vente de l'article
	    
	    request.setAttribute("article", a);
	    this.doGet(request, response);
	    //request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);

	} catch (BusinessException be1) {
	    be1.printStackTrace();
	    request.setAttribute("errors", be1.getErrors());
	    
	    try {
		request.setAttribute("article", ArticleManager.getArticleManager().selectById(articleId));
	    } catch (BusinessException e1) {
		request.getRequestDispatcher("./IndexServlet").forward(request, response);//Erreur
	    }
	    
	    
	    if(a.getDebutEncheres().isAfter(LocalDate.now()) ||
		    a.getFinEncheres().isBefore(LocalDate.now())) {
		request.setAttribute("peutEncherir", "false");
	    }
	    else {
		request.setAttribute("peutEncherir", "true");
	    }
	    if(a.getFinEncheres().compareTo(LocalDate.now()) <= 0){
		request.setAttribute("peutEncherir", "articleVendu");
	    }
	    
	    
	    request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
	}

    }

}
