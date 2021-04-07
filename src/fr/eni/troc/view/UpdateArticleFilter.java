package fr.eni.troc.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import fr.eni.troc.bo.Article;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.exception.DALException;

/**
 * Servlet Filter implementation class UpdateArticleFIlter
 */
@WebFilter("/UpdateArticleFilter")
public class UpdateArticleFilter implements Filter {

    /**
     * Default constructor.
     */
    public UpdateArticleFilter() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
	// TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	try {
	    List<Article> articlesFinEncheres = DALFactory.getArticleDal()
		        .selectAll().stream()
	    		.filter(a -> a.getFinEncheres().compareTo(LocalDate.now()) > 0)
	    		.collect(Collectors.toList());

	} catch (DALException e) {
	    System.out.println(e.getMessage());
	    chain.doFilter(request, response);
	}
	
	chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
	// TODO Auto-generated method stub
    }

}
