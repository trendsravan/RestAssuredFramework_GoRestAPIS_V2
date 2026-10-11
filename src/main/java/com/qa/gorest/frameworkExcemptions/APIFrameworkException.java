package com.qa.gorest.frameworkExcemptions;

public class APIFrameworkException extends RuntimeException {

	public APIFrameworkException(String msg) {
		super(msg);
	}
	
	public APIFrameworkException(String msg, Throwable cause) {
		super (msg,cause);
	}
}
	//APIFrameworkException(String mesg)
	//↓
	//RuntimeException(String message)
	//↓
	//Exception(String message)
	//↓
	//Throwable(String message)
	//↓
	//Object()
