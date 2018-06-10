package com.pedro.sw.exceptions;

public class MultipleResultsFoundException extends Exception{

	/** */
	private static final long serialVersionUID = 2293648374576570768L;
	
	public MultipleResultsFoundException() {
		super();
	}
	
	public MultipleResultsFoundException(String message) {
		super(message);
	}
	
	public MultipleResultsFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MultipleResultsFoundException(Throwable cause) {
		super(cause);
	}

}
