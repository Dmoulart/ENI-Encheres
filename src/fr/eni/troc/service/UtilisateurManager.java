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
		public void creer(Utilisateur utilisateur) throws BusinessException{
			
			// Validation des données par rapport au métier
			BusinessException be = new BusinessException();
			boolean isValidPseudo = validatePseudo(utilisateur.getPseudo(), be);
			boolean isValidNom = validateNom(utilisateur.getNom(), be);
			boolean isValidPrenom = validatePrenom(utilisateur.getPrenom(), be);
			boolean isValidEmail = validateEmail(utilisateur.getEmail(), be);
			boolean isValidTelephone = validateTelephone(utilisateur.getTelephone(), be);
			boolean isValidRue = validateRue(utilisateur.getRue(), be);
			boolean isValidCodePostal = validateCodePostal(utilisateur.getCodePostal(), be);
			boolean isValidVille = validateVille(utilisateur.getVille(), be);
			boolean isValidPassword = validatePassword(utilisateur.getMotDePasse(), be);
			//boolean isValidCredit = validateCredit(utilisateur.getCredit(), be);
			//boolean isValidAdmin = validateAdmin(utilisateur.isAdministrateur(), be);
			if (isValidPseudo && isValidNom && isValidPrenom && isValidEmail && isValidTelephone && isValidRue && isValidCodePostal && isValidVille && isValidPassword) {
				
				//Appelle de la couche DAL
				DALFactory.getUtilisateurDal().insert(utilisateur);
			}
			else {
				throw be;
			}
		}
		
		
		/**
		 * Permet de supprimer un utilisateur existant
		 * @param id
		 * @throws BusinessException
		 */
		public void delete(int id) throws BusinessException{
			
			//Appelle de la couche DAL - pas de vérifications particulieres
			DALFactory.getUtilisateurDal().delete(id);
			
		}
		
		public void update(Utilisateur utilisateur) throws BusinessException{
			
			
			//Appelle de la couche DAL - pas de vérifications particulieres
			DALFactory.getUtilisateurDal().update(utilisateur);
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
		 * Vérifier que le nom n'est pas null, pas vide 
		 * 
		 * @param nom
		 * @param be
		 * @return
		 */
		private boolean validateNom(String nom, BusinessException be) {
			if (nom == null) {
				be.addError("Nom est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le pseudo n'est pas null, pas vide 
		 * 
		 * @param prenom
		 * @param be
		 * @return
		 */
		private boolean validatePrenom(String prenom, BusinessException be) {
			if (prenom == null) {
				be.addError("Prenom est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le nom n'est pas null, pas vide 
		 * 
		 * @param email
		 * @param be
		 * @return
		 */
		private boolean validateEmail(String email, BusinessException be) {
			if (email == null) {
				be.addError("Email est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le pseudo n'est pas null, pas vide 
		 * 
		 * @param telephone
		 * @param be
		 * @return
		 */
		private boolean validateTelephone(String telephone, BusinessException be) {
			if (telephone == null) {
				be.addError("Telephone est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le nom n'est pas null, pas vide 
		 * 
		 * @param rue
		 * @param be
		 * @return
		 */
		private boolean validateRue(String rue, BusinessException be) {
			if (rue == null) {
				be.addError("Rue est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le pseudo n'est pas null, pas vide 
		 * 
		 * @param codePostal
		 * @param be
		 * @return
		 */
		private boolean validateCodePostal(String codePostal, BusinessException be) {
			if (codePostal == null) {
				be.addError("Code Postal est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le nom n'est pas null, pas vide 
		 * 
		 * @param ville
		 * @param be
		 * @return
		 */
		private boolean validateVille(String ville, BusinessException be) {
			if (ville == null) {
				be.addError("Ville est obligatoire");
				return false;
			}

			return true;
		}
		

		/**
		 * Vérifier que le password n'est pas null, pas vide
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
		
		/**
		 * Vérifier que le password n'est pas null, pas vide
		 * 
		 * @param pwd 2
		 * @param be
		 * @return
		 */
		private boolean validateConfiPassword(String motDePasse, BusinessException be) {
			if (motDePasse == null) {
				be.addError("Mot de passe est obligatoire");
				return false;
			}

			return true;
		}
		
		/**
		 * Vérifier que le password n'est pas null, pas vide
		 * 
		 * @param crédit
		 * @param be
		 * @return
		 */
		private boolean validateCredit(int credit, BusinessException be) {
			if (credit > 101) {
				be.addError("Trop de crédit");
				return false;
			}

			return true;
		}

		/**
		 * Vérifier que le password n'est pas null, pas vide
		 * 
		 * @param admin
		 * @param be
		 * @return
		 */
		private boolean validateAdmin(boolean administrateur, BusinessException be) {
			if (administrateur == false) {
				be.addError("Vous n'êtes pas autorisé à être administrateur");
				return false;
			}

			return true;
		}

}
