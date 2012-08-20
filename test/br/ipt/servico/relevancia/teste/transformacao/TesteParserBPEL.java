package br.ipt.servico.relevancia.teste.transformacao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesParserBPEL;
import br.ipt.servico.relevancia.transformacao.ParserBPEL;
import edu.uci.ics.jung.graph.Graph;

/**
 * Testes unitarios da camada de transformacao.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteParserBPEL {

    @Test
    public void testeParseSucesso() throws Exception {
	ParserBPEL parser = new ParserBPEL();

	Graph<Vertice, Arco> multidigrafo = parser
		.parse(ConstantesParserBPEL.PROCESSOS_NEGOCIO);

	assertEquals("Numero de vertices nao bate.",
		ConstantesParserBPEL.MULTIDIGRAFO.getVertexCount(),
		multidigrafo.getVertexCount());
	assertEquals("Numero de vertices inicio nao bate.",
		ConstantesParserBPEL.NUMERO_VERTICES_INICIO, Util
			.obterNumeroVerticesInicio(multidigrafo));
	assertEquals("Numero de vertices fim nao bate.",
		ConstantesParserBPEL.NUMERO_VERTICES_FIM, Util
			.obterNumeroVerticesFim(multidigrafo));
	assertEquals("Vertice inicio nao bate.",
		ConstantesParserBPEL.VERTICE_INICIO, Util
			.obterVerticeInicio(multidigrafo));
	assertEquals("Vertice fim nao bate.", ConstantesParserBPEL.VERTICE_FIM,
		Util.obterVerticeFim(multidigrafo));
	for (Vertice vertice : ConstantesParserBPEL.MULTIDIGRAFO.getVertices()) {
	    assertEquals("Vertice nao bate.", 1, Util.obterNumeroVertices(
		    multidigrafo, vertice));
	}
	assertEquals("Numero de arcos nao bate.",
		ConstantesParserBPEL.MULTIDIGRAFO.getEdgeCount(), multidigrafo
			.getEdgeCount());
	for (Arco arco : ConstantesParserBPEL.MULTIDIGRAFO.getEdges()) {
	    assertEquals("Arco nao bate", 1, Util.obterNumeroParVertices(
		    multidigrafo, ConstantesParserBPEL.MULTIDIGRAFO
			    .getEndpoints(arco)));
	}
    }

    @Test
    public void testeParsePNNulo() throws Exception {
	ParserBPEL parser = new ParserBPEL();

	Graph<Vertice, Arco> multidigrafo = parser.parse(null);

	assertEquals("Numero de vertices nao bate.", 0, multidigrafo
		.getVertexCount());
	assertEquals("Numero de arcos nao bate.", 0, multidigrafo
		.getEdgeCount());
    }
}