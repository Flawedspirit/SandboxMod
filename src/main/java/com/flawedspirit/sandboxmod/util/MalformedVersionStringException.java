package com.flawedspirit.sandboxmod.util;

@SuppressWarnings("serial")
public class MalformedVersionStringException extends Exception {

	public MalformedVersionStringException() {
		super();
	}
	
	public MalformedVersionStringException(String message) {
		super(message);
	}
}
