package br.ipt.servico.relevancia.multidigrafo;

import java.util.Map;

/**
 * Especializacao de um {@link Vertice} denotando o inicio de um processo de
 * negocio BPEL.
 * 
 * @author Rodrigo Mendes Leme
 */
public final class VerticeInicio extends Vertice {

    private static final long serialVersionUID = 8572396024453960520L;

    private static final int NUMERO_ROTULOS = 1;

    private static final String PREFIXO_VERTICE_INICIO = "Vertice inicio";

    public VerticeInicio(Map<Rotulo, String> rotulos) {
	if (rotulos == null) {
	    throw new IllegalArgumentException("rotulos nao pode ser nulo.");
	}
	if (rotulos.size() != NUMERO_ROTULOS
		|| !rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)) {
	    throw new IllegalArgumentException(
		    "um vertice inicio deve conter apenas o rotulo "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ", preenchido.");
	}
	this.rotulos = rotulos;
    }

    public void adicionarRotulo(Rotulo rotulo, String valor) {
	throw new UnsupportedOperationException("um vertice inicio e imutavel.");
    }

    public String toString() {
	return PREFIXO_VERTICE_INICIO + " PN "
		+ this.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO);
    }

    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof VerticeInicio)) {
	    return false;
	}

	Vertice verticeInicio = (Vertice) obj;
	return this.rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)
		&& verticeInicio.rotulos
			.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)
		&& this.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO).equals(
			verticeInicio
				.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    public int hashCode() {
	return this.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO) == null ? super
		.hashCode()
		: this.toString().hashCode();
    }
}