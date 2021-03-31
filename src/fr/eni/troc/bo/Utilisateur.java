package fr.eni.troc.bo;

import java.util.List;

public class Utilisateur {


	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone; 
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	private List <Enchere> encheres; 
	private List<Article> articlesEnVentes; 
	private List <Article> articlesAcquis; 
	

	
	///CONSTRUCTEURS UTILISATEURS
	
	public Utilisateur() {
	}
	
	public Utilisateur(int id, List<Article> articlesEnVentes, List<Article> articlesAcquis, String pseudo, String nom,
			String prenom, String email, String telephone, String rue, String codePostal, String ville,
			String motDePasse, int credit, boolean administrateur, List<Enchere> encheres) {
		this.id = id;
		this.articlesEnVentes = articlesEnVentes;
		this.articlesAcquis = articlesAcquis;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.encheres = encheres;
	}
	
	
	//GETTERS AND SETTERS CONSTRUCTEURS

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Article> getArticlesEnVentes() {
		return articlesEnVentes;
	}

	public void setArticlesEnVentes(List<Article> articlesEnVentes) {
		this.articlesEnVentes = articlesEnVentes;
	}

	public List<Article> getArticlesAcquis() {
		return articlesAcquis;
	}

	public void setArticlesAcquis(List<Article> articlesAcquis) {
		this.articlesAcquis = articlesAcquis;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	
	//TO STRING

	
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", articlesEnVentes=" + articlesEnVentes + ", articlesAcquis=" + articlesAcquis
				+ ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone="
				+ telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", motDePasse="
				+ motDePasse + ", credit=" + credit + ", administrateur=" + administrateur + ", encheres=" + encheres
				+ "]";
	}	
	
	
	//HASH CODE AND EQUALS UTILISATEURS


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrateur ? 1231 : 1237);
		result = prime * result + ((articlesAcquis == null) ? 0 : articlesAcquis.hashCode());
		result = prime * result + ((articlesEnVentes == null) ? 0 : articlesEnVentes.hashCode());
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + credit;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((encheres == null) ? 0 : encheres.hashCode());
		result = prime * result + id;
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (administrateur != other.administrateur)
			return false;
		if (articlesAcquis == null) {
			if (other.articlesAcquis != null)
				return false;
		} else if (!articlesAcquis.equals(other.articlesAcquis))
			return false;
		if (articlesEnVentes == null) {
			if (other.articlesEnVentes != null)
				return false;
		} else if (!articlesEnVentes.equals(other.articlesEnVentes))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (credit != other.credit)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (encheres == null) {
			if (other.encheres != null)
				return false;
		} else if (!encheres.equals(other.encheres))
			return false;
		if (id != other.id)
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (pseudo == null) {
			if (other.pseudo != null)
				return false;
		} else if (!pseudo.equals(other.pseudo))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;}
	
}
	


