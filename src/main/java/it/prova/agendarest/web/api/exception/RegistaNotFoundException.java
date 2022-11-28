package it.prova.agendarest.web.api.exception;

public class RegistaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegistaNotFoundException(String message) {
		super(message);
	}

}
