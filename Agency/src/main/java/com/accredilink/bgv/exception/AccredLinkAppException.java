package com.accredilink.bgv.exception;

public class AccredLinkAppException extends RuntimeException {
	
	private static final long serialVersionUID = -5483466936177734759L;
	private String exceptionMessge;

	public AccredLinkAppException(String exceptionMessge) {
		super();
		this.exceptionMessge = exceptionMessge;
	}

	public String getExceptionMessge() {
		return exceptionMessge;
	}

	public void setExceptionMessge(String exceptionMessge) {
		this.exceptionMessge = exceptionMessge;
	}
	
	

}
