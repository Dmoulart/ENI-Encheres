package fr.eni.troc.exception;
import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

private static final long serialVersionUID = 1L;

	//Ensemble des messages d'erreur de l'application
	private List<String> errors;

	public void addError(String error) {
		if(errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}

	public BusinessException() {
	}

	public BusinessException(String message) {
			super(message);
	}

	public BusinessException(Throwable cause) {
			super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
