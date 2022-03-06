package com.packagingandelivery.exceptions;

public class ComponentChargeDetailsNotFoundException extends RuntimeException {
	/**
	 * this is an exception thrown to the business layer.it simplifies DAO layer
	 * exceptions in Service layer and send to user
	 */
	private static final long serialVersionUID = 1L;

	public ComponentChargeDetailsNotFoundException(String message) {
		super(message);
	}
}