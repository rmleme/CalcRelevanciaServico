package br.ipt.servico.relevancia.excecao;

/**
 * Excecao lancada quando tenta-se importar um processo de negocio ja importado.
 * 
 * @author Rodrigo Mendes Leme
 */
public class BPAlreadyExistsException extends Exception {

    private static final long serialVersionUID = -3688368092962369813L;

    public BPAlreadyExistsException() {
    }

    public BPAlreadyExistsException(String message) {
	super(message);
    }

    public BPAlreadyExistsException(Throwable cause) {
	super(cause);
    }

    public BPAlreadyExistsException(String message, Throwable cause) {
	super(message, cause);
    }
}