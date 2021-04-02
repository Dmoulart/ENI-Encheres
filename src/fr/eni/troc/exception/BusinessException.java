package fr.eni.troc.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    // Ensemble des messages d'erreur de l'application
    private List<String> errors;

    private Map<Exception, String> exceptions;

    public void addError(String error) {
	if (errors == null) {
	    errors = new ArrayList<String>();
	}
	errors.add(error);
    }

    public List<String> getErrors() {
	if (errors == null) {
	    errors = new ArrayList<String>();
	}
	return errors;
    }

    /*-TEST--*/
    public Map<Exception, String> getExceptions() {
	if (exceptions == null) {
	    exceptions = new HashMap<Exception, String>();
	}
	return exceptions;
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
