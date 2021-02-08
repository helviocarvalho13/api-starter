package com.pulse.apistarter.exceptions;

public class UnauthorizedAccessException extends RuntimeException{

	private static final long serialVersionUID = -5202737413990547314L;

	public UnauthorizedAccessException(String message){
		super(message);
	}

}
