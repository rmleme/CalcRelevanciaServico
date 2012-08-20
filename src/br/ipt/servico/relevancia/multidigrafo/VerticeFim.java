package br.ipt.servico.relevancia.multidigrafo;

import java.util.Map;

/**
 * Especializacao de um {@link Vertice} denotando o fim de um processo de
 * negocio BPEL.
 * 
 * @author Rodrigo Mendes Leme
 */
public final class VerticeFim extends Vertice {

    private static final long serialVersionUID = 3452714107759455443L;

    private static final int NUMERO_ROTULOS = 1;

    private static final String PREFIXO_VERTICE_FIM = "Vertice fim";

    public VerticeFim(Map<Rotulo, String> rotulos) {
	if (rotulos == null) {
	    throw new IllegalArgumentException("rotulos nao pode ser nulo.");
	}
	if (rotulos.size() != NUMERO_ROTULOS
		|| !rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)) {
	    throw new IllegalArgumentException(
		    "um vertice fim deve conter apenas o rotulo "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ", preenchido.");
	}
	this.rotulos = rotulos;
    }

    public void adicionarRotulo(Rotulo rotulo, String valor) {
	throw new UnsupportedOperationException("um vertice fim e imutavel.");
    }

    public String toString() {
	return PREFIXO_VERTICE_FIM + " PN "
		+ this.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO);
    }

    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof VerticeFim)) {
	    return false;
	}

	Vertice verticeFim = (Vertice) obj;
	return this.rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)
		&& verticeFim.rotulos.containsKey(Rotulo.ID_PROCESSO_NEGOCIO)
		&& this
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO)
			.equals(
				verticeFim
					.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    public int hashCode() {
	return this.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO) == null ? super
		.hashCode()
		: this.toString().hashCode();
    }
}