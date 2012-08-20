package br.ipt.servico.relevancia.multidigrafo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstracao de um caminho no fluxo de controle de um processo de negocio BPEL,
 * na forma de um arco entre dois vertices de um grafo.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Arco implements Serializable {

    private static final long serialVersionUID = -2545903716865183080L;

    private static final int NUMERO_ROTULOS = 2;

    protected Map<Rotulo, String> rotulos;

    protected Arco() {
	this.rotulos = new HashMap<Rotulo, String>();
    }

    public Arco(Map<Rotulo, String> rotulos) {
	if (rotulos == null) {
	    throw new IllegalArgumentException("rotulos nao pode ser nulo.");
	}
	if (rotulos.size() > NUMERO_ROTULOS
		|| !(rotulos.containsKey(Rotulo.NUMERO_INVOCACOES) || rotulos
			.containsKey(Rotulo.ID_PROCESSO_NEGOCIO))) {
	    throw new IllegalArgumentException(
		    "um arco deve conter apenas os rotulos "
			    + Rotulo.NUMERO_INVOCACOES + " e "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ".");
	}
	this.rotulos = rotulos;
    }

    public void adicionarRotulo(Rotulo rotulo, String valor) {
	if (rotulo == null || valor == null) {
	    throw new IllegalArgumentException("rotulo nao pode ser nulo.");
	}
	this.rotulos.put(rotulo, valor);
    }

    public String obterValorRotulo(Rotulo rotulo) {
	return this.rotulos.get(rotulo);
    }

    public String toString() {
	return this.obterValorRotulo(Rotulo.NUMERO_INVOCACOES);
    }

    /**
     * Rotulos que podem ser associados a um arco.
     * 
     * @author Rodrigo Mendes Leme
     */
    public enum Rotulo {
	NUMERO_INVOCACOES, ID_PROCESSO_NEGOCIO
    }
}