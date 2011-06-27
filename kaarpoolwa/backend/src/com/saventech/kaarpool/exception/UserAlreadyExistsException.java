package com.saventech.kaarpool.exception;

/**
 * Thrown if the user already exists
 * 
 * @author sfhidayath
 *
 */
public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException(){
		super();
	}
	
	public UserAlreadyExistsException(String msg){
		super(msg);
	}
	

}
