package com.pedro.sw.exceptions;

public class NoResultsFoundException extends Exception {

	/** */
	private static final long serialVersionUID = -7220391385489522974L;

	public NoResultsFoundException() {
		super();
	}

	public NoResultsFoundException(String message) {
		super(message);
	}

	public NoResultsFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoResultsFoundException(Throwable cause) {
		super(cause);
	}

}
