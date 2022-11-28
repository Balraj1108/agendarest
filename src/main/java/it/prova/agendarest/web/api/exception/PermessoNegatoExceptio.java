package it.prova.agendarest.web.api.exception;

public class PermessoNegatoExceptio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PermessoNegatoExceptio(String message) {
		super(message);
	}
}
