package fr.eni.troc.dal;

public class DALException extends Exception {
	//CONSTRUCTEURS
	
	public DALException() {
		super();
	}
	
	public DALException(String message) {
		super(message);
	}
	
	public DALException(String message, Throwable exception) {
		super(message, exception);
	}
	
	//METHODES
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("!!! COUCHE DAL - ");
		sb.append(super.getMessage());
		
		return sb.toString();
	}
}
