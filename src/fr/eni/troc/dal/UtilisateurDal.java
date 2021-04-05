package fr.eni.troc.dal;

import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.exception.DALException;

public interface UtilisateurDal {

    public Utilisateur find(String pseudo, String motDePasse) throws DALException;

    public Utilisateur selectByEmail(String email, String motDePasse) throws DALException;

    public boolean hasDuplicates(String field, String value) throws DALException;
    
    public void insert(Utilisateur utilisateur) throws DALException;

    public void delete(int id) throws DALException;

    public void update(Utilisateur utilisateur) throws DALException;

    public Utilisateur selectById(int id) throws DALException;

    public Utilisateur selectByIdAsVendeur(int id) throws DALException;

    public Utilisateur selectByIdAsEmetteur(int id) throws DALException;

    

    // public void updateMDP(Utilisateur utilisateur) throws BusinessException;

}
