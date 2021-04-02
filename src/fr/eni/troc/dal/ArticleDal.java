package fr.eni.troc.dal;

import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface ArticleDal {
    public void insert(final Article article) throws BusinessException;

    public void delete(final int id) throws BusinessException;

    public void update(final Article article) throws BusinessException;

    public List<Article> selectAll() throws BusinessException;

    public List<Article> selectByVendeur(Utilisateur utilisateur) throws BusinessException;

    public Article selectById(int ide) throws BusinessException;

    public List<Article> selectByName(String nom) throws BusinessException;
}
