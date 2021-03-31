package fr.eni.troc.dal;

import fr.eni.troc.bo.Article;
import fr.eni.troc.exception.BusinessException;

public interface ArticleDal {
	public void insert (final Article article) throws BusinessException;
	public void delete (final int id) throws BusinessException; 
	public void update (final Article article) throws BusinessException;
}
