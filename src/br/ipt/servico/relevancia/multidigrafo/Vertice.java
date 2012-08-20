package br.ipt.servico.relevancia.multidigrafo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstracao de uma chamada a uma operacao de </i>web service</i> por um
 * processo de negocio BPEL, na forma de um vertice de grafo.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Vertice implements Serializable {

    private static final long serialVersionUID = -4245047524426877632L;

    private static final int NUMERO_ROTULOS = 4;

    protected Map<Rotulo, String> rotulos;

    protected Vertice() {
	this.rotulos = new HashMap<Rotulo, String>();
    }

    public Vertice(Map<Rotulo, String> rotulos) {
	if (rotulos == null) {
	    throw new IllegalArgumentException("rotulos nao pode ser nulo.");
	}
	if (rotulos.size() > NUMERO_ROTULOS
		|| !(rotulos.containsKey(Rotulo.TEMPO_MEDIO_EXECUCAO)
			|| rotulos.containsKey(Rotulo.TEMPO_RESPOSTA_ESPERADO)
			|| rotulos.containsKey(Rotulo.SERVICO_OPERACAO) || rotulos
			.containsKey(Rotulo.URL))
		|| rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)) {
	    throw new IllegalArgumentException(
		    "um vertice comum deve conter apenas os rotulos "
			    + Rotulo.TEMPO_MEDIO_EXECUCAO + ", "
			    + Rotulo.TEMPO_RESPOSTA_ESPERADO + ", "
			    + Rotulo.SERVICO_OPERACAO + " e " + Rotulo.URL
			    + ".");
	}
	this.rotulos = rotulos;
    }

    public void adicionarRotulo(Rotulo rotulo, String valor) {
	if (rotulo == null || valor == null) {
	    throw new IllegalArgumentException("rotulo nao pode ser nulo.");
	}
	if (!Rotulo.TEMPO_MEDIO_EXECUCAO.equals(rotulo)
		&& !Rotulo.TEMPO_RESPOSTA_ESPERADO.equals(rotulo)
		&& !Rotulo.SERVICO_OPERACAO.equals(rotulo)
		&& !Rotulo.URL.equals(rotulo)) {
	    throw new IllegalArgumentException(rotulo
		    + ": rotulo nao permitido em um vertice comum.");
	}
	this.rotulos.put(rotulo, valor);
    }

    public String obterValorRotulo(Rotulo rotulo) {
	return this.rotulos.get(rotulo);
    }

    public String toString() {
	return this.obterValorRotulo(Rotulo.SERVICO_OPERACAO);
    }

    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof Vertice)) {
	    return false;
	}

	Vertice vertice = (Vertice) obj;
	return this.rotulos.containsKey(Rotulo.SERVICO_OPERACAO)
		&& vertice.rotulos.containsKey(Rotulo.SERVICO_OPERACAO)
		&& this.rotulos.containsKey(Rotulo.URL)
		&& vertice.rotulos.containsKey(Rotulo.URL)
		&& this.obterValorRotulo(Rotulo.SERVICO_OPERACAO).equals(
			vertice.obterValorRotulo(Rotulo.SERVICO_OPERACAO))
		&& this.obterValorRotulo(Rotulo.URL).equals(
			vertice.obterValorRotulo(Rotulo.URL));
    }

    public int hashCode() {
	return this.obterValorRotulo(Rotulo.SERVICO_OPERACAO) == null ? super
		.hashCode() : (this.obterValorRotulo(Rotulo.SERVICO_OPERACAO)
		+ "_" + this.obterValorRotulo(Rotulo.URL)).hashCode();
    }

    /**
     * Rotulos que podem ser associados a um vertice.
     * 
     * @author Rodrigo Mendes Leme
     */
    public enum Rotulo {
	TEMPO_MEDIO_EXECUCAO, TEMPO_RESPOSTA_ESPERADO, SERVICO_OPERACAO, URL, ID_PROCESSO_NEGOCIO
    }
}