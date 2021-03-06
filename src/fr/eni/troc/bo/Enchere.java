package fr.eni.troc.bo;

import java.time.LocalDate;

public class Enchere {
    private int id;
    private LocalDate date;
    private int montant;
    private Utilisateur emetteur;
    private Article article; // A SUPPRIMER

    // CONSTRUCTEURS ENCHERES

    public Enchere() {
    }

    public Enchere(int id, LocalDate date, int montant, Utilisateur emetteur, Article article) {
	this.id = id;
	this.date = date;
	this.montant = montant;
	this.emetteur = emetteur;
	this.article = article;
    }

    // GETTERS AND SETTERS ENCHERES

    public LocalDate getDate() {
	return date;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public int getMontant() {
	return montant;
    }

    public void setMontant(int montant) {
	this.montant = montant;
    }

    public Utilisateur getEmetteur() {
	return emetteur;
    }

    public void setEmetteur(Utilisateur emetteur) {
	this.emetteur = emetteur;
    }

    public Article getArticle() {
	return this.article;
    }

    public void setArticle(Article article) {
	this.article = article;
    }

    // TO STRING
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Enchere [id=");
	builder.append(id);
	builder.append(", date=");
	builder.append(date);
	builder.append(", montant=");
	builder.append(montant);
	/*if (emetteur != null) {
	    builder.append(", emetteur=");
	    builder.append(emetteur);
	}*/
	return builder.toString();
    }
    // HASH CODE AND EQUALS ENCHERES

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((article == null) ? 0 : article.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((emetteur == null) ? 0 : emetteur.hashCode());
	result = prime * result + id;
	result = prime * result + montant;
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
	Enchere other = (Enchere) obj;
	if (article == null) {
	    if (other.article != null)
		return false;
	} else if (!article.equals(other.article))
	    return false;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (emetteur == null) {
	    if (other.emetteur != null)
		return false;
	} else if (!emetteur.equals(other.emetteur))
	    return false;
	if (id != other.id)
	    return false;
	if (montant != other.montant)
	    return false;
	return true;
    }

}
