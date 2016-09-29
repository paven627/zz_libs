package test.spring.aop.spring.advice.exceptions;


public class NoSuchTvException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchTvException(String string) {
		super(string);
	}
	
}
