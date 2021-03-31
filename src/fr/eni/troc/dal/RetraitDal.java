package fr.eni.troc.dal;


import fr.eni.troc.bo.Retrait;
import fr.eni.troc.exception.BusinessException;

public interface RetraitDal {
	public void insert (final Retrait item) throws BusinessException;
	public void delete (final int id) throws BusinessException;
	public void update (final Retrait retrait) throws BusinessException;
}
