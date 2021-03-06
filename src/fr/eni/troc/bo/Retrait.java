package fr.eni.troc.bo;

public class Retrait {
    private Article article;
    private String rue;
    private String codePostal;
    private String ville;

//CONSTRUCTEUR RETRAITS
    public Retrait() {
    }

    public Retrait(Article article, String rue, String codePostal, String ville) {
	this.article = article;
	this.rue = rue;
	this.codePostal = codePostal;
	this.ville = ville;
    }

    // GETTERS AND SETTERS RETRAITS

    public Article getArticle() {
	return article;
    }

    public void setArticle(Article article) {
	this.article = article;
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

    // TO STRING RETRAITS

    @Override
    public String toString() {
	return "Retrait [article=" + article + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
    }

    // HASH CODE AND EQUALS RETRAITS

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((article == null) ? 0 : article.hashCode());
	result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
	result = prime * result + ((rue == null) ? 0 : rue.hashCode());
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
	Retrait other = (Retrait) obj;
	if (article == null) {
	    if (other.article != null)
		return false;
	} else if (!article.equals(other.article))
	    return false;
	if (codePostal == null) {
	    if (other.codePostal != null)
		return false;
	} else if (!codePostal.equals(other.codePostal))
	    return false;
	if (rue == null) {
	    if (other.rue != null)
		return false;
	} else if (!rue.equals(other.rue))
	    return false;
	if (ville == null) {
	    if (other.ville != null)
		return false;
	} else if (!ville.equals(other.ville))
	    return false;
	return true;
    }
}
