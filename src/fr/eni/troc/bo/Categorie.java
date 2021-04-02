package fr.eni.troc.bo;

public class Categorie {
    private int id;
    private String libelle;

    // CONSTRUCTEURS CATEGORIES

    public Categorie(int id, String libelle) {
	this.id = id;
	this.libelle = libelle;
    }

    public Categorie() {
    }

    // GETTERS AND SETTERS CATEGORIES

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getLibelle() {
	return libelle;
    }

    public void setLibelle(String libelle) {
	this.libelle = libelle;
    }

    // TO STRING CATEGORIES

    @Override
    public String toString() {
	return "Categorie [id=" + id + ", libelle=" + libelle + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
	return result;
    }

    // HASH CODE AND EQUALS CATEGORIES

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Categorie other = (Categorie) obj;
	if (id != other.id)
	    return false;
	if (libelle == null) {
	    if (other.libelle != null)
		return false;
	} else if (!libelle.equals(other.libelle))
	    return false;
	return true;
    }
}
