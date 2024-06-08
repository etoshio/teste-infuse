package br.com.infuse.teste.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4731120321315422173L;

	public NotFoundException(String mensagem) { super(mensagem); }
}
