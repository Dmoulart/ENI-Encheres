package fr.eni.troc.dal;
import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.BusinessException;

public interface CategorieDal {
	public void insert (final Categorie categorie) throws BusinessException;
	public void delete (final int id) throws BusinessException;
	public void update (final Categorie categorie) throws BusinessException;
}
