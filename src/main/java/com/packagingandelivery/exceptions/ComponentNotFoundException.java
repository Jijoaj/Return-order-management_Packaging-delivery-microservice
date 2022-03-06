package com.packagingandelivery.exceptions;

public class ComponentNotFoundException extends RuntimeException {
	/**
	 * this exception is thrown when the component is not found with given component
	 * type
	 */
	private static final long serialVersionUID = 1L;

	public ComponentNotFoundException(String message) {
		super(message);
	}
}
