package br.ipt.servico.relevancia.multidigrafo;

import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import edu.uci.ics.jung.graph.Graph;

/**
 * Metodos auxiliares para manipulacao de multidigrafos.
 * 
 * @author Rodrigo Mendes Leme
 */
public class MultidigrafoUtil {

    public static boolean contemProcessoNegocio(
	    Graph<Vertice, Arco> multidigrafo, ProcessoNegocio processoNegocio) {
	if (multidigrafo == null || processoNegocio == null) {
	    return false;
	}

	for (Arco arco : multidigrafo.getEdges()) {
	    if (processoNegocio.getId() == Integer.parseInt(arco
		    .obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO))) {
		return true;
	    }
	}
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeInicio
		    || vertice instanceof VerticeFim) {
		if (processoNegocio.getId() == Integer.parseInt(vertice
			.obterValorRotulo(Vertice.Rotulo.ID_PROCESSO_NEGOCIO))) {
		    return true;
		}
	    }
	}

	return false;
    }
}