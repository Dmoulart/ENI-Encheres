package fr.eni.troc.dal;

import fr.eni.troc.bo.Retrait;
import fr.eni.troc.exception.DALException;

public interface RetraitDal {
    public void insert(final Retrait retrait) throws DALException;

    public void delete(final int id) throws DALException;

    public void update(final Retrait retrait) throws DALException;
}
