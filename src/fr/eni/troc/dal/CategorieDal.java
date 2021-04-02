package fr.eni.troc.dal;

import java.util.List;

import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.BusinessException;

public interface CategorieDal {
    public void insert(final Categorie categorie) throws BusinessException;

    public void delete(final int id) throws BusinessException;

    public void update(final Categorie categorie) throws BusinessException;

    public List<Categorie> selectAll() throws BusinessException;

    public Categorie selectById(int id) throws BusinessException;
}
