package fr.eni.troc.dal;
import java.util.List;

import fr.eni.troc.bo.Categorie;
import fr.eni.troc.exception.DALException;

public interface CategorieDal {
	public void insert (final Categorie categorie) throws DALException;
	public void delete (final int id) throws DALException;
	public void update (final Categorie categorie) throws DALException;
	public List<Categorie> selectAll() throws DALException;
	public Categorie selectById(int id) throws DALException;
}
