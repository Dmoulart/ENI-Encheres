package fr.eni.troc.dal;

import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.DALException;

public interface ArticleDal {
    public void insert(final Article article) throws DALException;

    public void delete(final int id) throws DALException;

    public void update(final Article article) throws DALException;

    public List<Article> selectAll() throws DALException;

    public List<Article> selectByVendeur(Utilisateur utilisateur) throws DALException;

    public Article selectById(int ide) throws DALException;
    // public List<Article> selectByName(String nom) throws DALException;
}
