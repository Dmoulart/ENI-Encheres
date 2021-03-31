package fr.eni.troc.dal;
import fr.eni.troc.bo.Enchere;
import fr.eni.troc.exception.BusinessException;

public interface EnchereDal {
	public void insert (final Enchere enchere) throws BusinessException;
	public void delete (final int id) throws BusinessException;
	public void update (final Enchere enchere) throws BusinessException;
}
