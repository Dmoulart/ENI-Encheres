package fr.eni.troc.service;


import fr.eni.troc.bo.Utilisateur;
import fr.eni.troc.dal.DALFactory;
import fr.eni.troc.dal.UtilisateurDal;
import fr.eni.troc.exception.BusinessException;


/**
 * pattern singleton
 * @author nicolas
 *
 */
public class UtilisateurManager {
	
		//Attribut pour représenter la couche DAL
		private UtilisateurDal utilisateurDal; 
	
		// Pattern Singleton
		private static UtilisateurManager instance;

		private UtilisateurManager() {
			//Récupération de l'instance de userDAO
			utilisateurDal = DALFactory.getUtilisateurDal();
		}

		public static UtilisateurManager getUtilisateurManager() {
			if (instance == null) {
				instance = new UtilisateurManager();
			}
			return instance;
		}
		
		
		/**
		 * Permet de valider le pseudo / mot de passe tranmis par l'affichage
		 * 
		 * @param pseudo
		 * @param motDePasse
		 * @return
		 * @throws BusinessException
		 */
		public Utilisateur validateConnection(String pseudo, String motDePasse) throws BusinessException {
			
			// Validation des données par rapport au métier
			
			BusinessException be = new BusinessException();
			boolean isValidPseudo = validatePseudo(pseudo, be);
			boolean isValidPassword = validatePassword(motDePasse, be);
			if (isValidPseudo && isValidPassword) {
				
				//Appelle de la couche DAL
				return utilisateurDal.find(pseudo, motDePasse);
			} else {
				throw be;
			}
		}
		
		/**
		 * Permet de creer un nouvel utilisateur si le pseudo et mot de passe valident les conditions requises 
		 * dans les methodes validatePseudo et validatePassword
		 * @param utilisateur
		 * @throws BusinessException
		 */
		public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException{
			
			// Validation des données par rapport au métier
			BusinessException be = new BusinessException();
			boolean isValidPseudo = validatePseudo(utilisateur.getPseudo(), be);
			boolean isValidPassword = validatePassword(utilisateur.getMotDePasse(), be);
			if (isValidPseudo && isValidPassword) {
				
				//Appelle de la couche DAL
				utilisateurDal.creerUtilisateur(utilisateur);
			}
			else {
				throw be;
			}
		}

		/**
		 * Vérifier que le pseudo n'est pas null, pas vide 
		 * 
		 * @param pseudo
		 * @param be
		 * @return
		 */
		private boolean validatePseudo(String pseudo, BusinessException be) {
			if (pseudo == null) {
				be.addError("Pseudo est obligatoire");
				return false;
			}

			return true;
		}

		/**
		 * Vérifier que le password n'est pas null, pas vide et qu'il correspond au bon mot de passe existant
		 * 
		 * @param pwd
		 * @param be
		 * @return
		 */
		private boolean validatePassword(String motDePasse, BusinessException be) {
			if (motDePasse == null) {
				be.addError("Mot de passe est obligatoire");
				return false;
			}

			return true;
		}

}
