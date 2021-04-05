package fr.eni.troc.exception;

public class DALException extends Exception {

    private static final long serialVersionUID = -7789341893123314689L;
    public static final String layer = "Couche DAL";
    public String exceptionStartingLocation;
    public String errorCase;
    
    public DALException() {
	super();
    }
    
    public DALException(String message, String className, Throwable cause) {
	super(message + " en Couche DAL dans la classe " + className + " " + cause.toString());
	this.exceptionStartingLocation = className;
	this.errorCase = message;
    }

    public DALException(String message, String className) {
	super(message + " en Couche DAL dans la classe " + className + " ");
	this.exceptionStartingLocation = className;
	this.errorCase = message;
    }
}
