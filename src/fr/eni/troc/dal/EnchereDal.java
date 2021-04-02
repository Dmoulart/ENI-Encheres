package fr.eni.troc.dal;

import java.util.List;

import fr.eni.troc.bo.Article;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.BusinessException;

public interface EnchereDal {
    public void insert(final Enchere enchere) throws BusinessException;

    public void delete(final int id) throws BusinessException;

    public void update(final Enchere enchere) throws BusinessException;

    public List<Enchere> selectByArticle(Article article) throws BusinessException;

    public List<Enchere> selectByUtilisateur(final Utilisateur emetteur) throws BusinessException;

}
