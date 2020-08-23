package com.ingaia.apistarter.exceptions;

public class NoContentDataAccessException extends RuntimeException{
	
	private static final long serialVersionUID = -5202737413990547314L;

	public NoContentDataAccessException(String message){
		super(message);
	}

}
