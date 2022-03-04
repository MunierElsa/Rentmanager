package com.epf.rentmanager.exception;

public class ContrainteException extends Exception{

	private static final long serialVersionUID = 1L;

	public ContrainteException() {
		super("Au moins une des contraintes n'est pas respectee");
	}
}