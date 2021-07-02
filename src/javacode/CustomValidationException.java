package javacode;

/**
 * This class includes custom exception "CustomValidationException"
 * to display a custom error message to an user
 * @author denis.rozit
 *
 */

public class CustomValidationException extends Exception {

	/**
	 * @param message String to display a specific error message
	 * 
	 */
	public CustomValidationException(String message) {
		super(message);
	}
	
	
}
