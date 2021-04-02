package fr.eni.troc.dal;

import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.DALException;

public interface EnchereDal {
    public void insert(final Enchere enchere) throws DALException;

    public void delete(final int id) throws DALException;

    public void update(final Enchere enchere) throws DALException;

    public List<Enchere> selectByArticle(Article article) throws DALException;

    public List<Enchere> selectByUtilisateur(final Utilisateur emetteur) throws DALException;

}
