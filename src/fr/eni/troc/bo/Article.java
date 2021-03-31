package fr.eni.troc.bo;


import java.time.LocalDate;
import java.util.List;

public class Article {
	private int id;
	private String nom;
	private Utilisateur vendeur;
	private String description;
	private List<Enchere> encheres;
	private LocalDate debutEncheres;
	private LocalDate finEncheres;
	private int prixInitial;
	private int prixVente;
	private boolean estVendu;
	private Categorie categorie;
	
		
	//CONSTRUCTEURS ARTICLES
	
	
	public Article() {
	}

	public Article(int id, String nom, Utilisateur vendeur, String description, List<Enchere> encheres, LocalDate debutEncheres, 
			LocalDate finEncheres, int prixInitial, int prixVente, boolean estVendu, Categorie categorie) {
		this.id = id;
		this.nom = nom;
		this.vendeur = vendeur;
		this.description = description;
		this.encheres = encheres;
		this.debutEncheres = debutEncheres;
		this.finEncheres = finEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.estVendu = estVendu;
		this.categorie = categorie;
	}	
	
	
	//GETTERS AND SETTERS ARTICLES

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public LocalDate getDebutEncheres() {
		return debutEncheres;
	}

	public void setDebutEncheres(LocalDate debutEncheres) {
		this.debutEncheres = debutEncheres;
	}

	public LocalDate getFinEncheres() {
		return finEncheres;
	}

	public void setFinEcheres(LocalDate finEncheres) {
		this.finEncheres = finEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isEstVendu() {
		return estVendu;
	}

	public void setEstVendu(boolean estVendu) {
		this.estVendu = estVendu;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	//TO STRING ARTICLES
	

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", vendeur=" + vendeur + ", description=" + description
				+ ", encheres=" + encheres + ", debutEncheres=" + debutEncheres + ", finEcheres=" + finEncheres
				+ ", prixInitial=" + prixInitial + ", prixVente=" + prixVente + ", estVendu=" + estVendu
				+ ", categorie=" + categorie + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + ((debutEncheres == null) ? 0 : debutEncheres.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((encheres == null) ? 0 : encheres.hashCode());
		result = prime * result + (estVendu ? 1231 : 1237);
		result = prime * result + ((finEncheres == null) ? 0 : finEncheres.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + prixInitial;
		result = prime * result + prixVente;
		result = prime * result + ((vendeur == null) ? 0 : vendeur.hashCode());
		return result;
	}
	
	
	//HASH CODE AND EQUALS ARTICLES
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (debutEncheres == null) {
			if (other.debutEncheres != null)
				return false;
		} else if (!debutEncheres.equals(other.debutEncheres))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (encheres == null) {
			if (other.encheres != null)
				return false;
		} else if (!encheres.equals(other.encheres))
			return false;
		if (estVendu != other.estVendu)
			return false;
		if (finEncheres == null) {
			if (other.finEncheres != null)
				return false;
		} else if (!finEncheres.equals(other.finEncheres))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prixInitial != other.prixInitial)
			return false;
		if (prixVente != other.prixVente)
			return false;
		if (vendeur == null) {
			if (other.vendeur != null)
				return false;
		} else if (!vendeur.equals(other.vendeur))
			return false;
		return true;
	}

}
