package br.ipt.servico.relevancia.teste.transformacao;

import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeFim;
import br.ipt.servico.relevancia.multidigrafo.VerticeInicio;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * Metodos auxiliares para os testes da camada de transformacao.
 * 
 * @author Rodrigo Mendes Leme
 */
class Util {

    static int obterNumeroVerticesInicio(Graph<Vertice, Arco> multidigrafo) {
	int numeroVerticesInicio = 0;
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeInicio) {
		numeroVerticesInicio++;
	    }
	}
	return numeroVerticesInicio;
    }

    static VerticeInicio obterVerticeInicio(Graph<Vertice, Arco> multidigrafo) {
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeInicio) {
		return (VerticeInicio) vertice;
	    }
	}
	return null;
    }

    static int obterNumeroVerticesFim(Graph<Vertice, Arco> multidigrafo) {
	int numeroVerticesFim = 0;
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeFim) {
		numeroVerticesFim++;
	    }
	}
	return numeroVerticesFim;
    }

    static VerticeFim obterVerticeFim(Graph<Vertice, Arco> multidigrafo) {
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeFim) {
		return (VerticeFim) vertice;
	    }
	}
	return null;
    }

    static int obterNumeroVertices(Graph<Vertice, Arco> multidigrafo,
	    Vertice vertice) {
	int numeroVertices = 0;
	for (Vertice v : multidigrafo.getVertices()) {
	    if (v.equals(vertice)) {
		numeroVertices++;
	    }
	}
	return numeroVertices;
    }

    static int obterNumeroParVertices(Graph<Vertice, Arco> multidigrafo,
	    Pair<Vertice> par) {
	int numeroParVertices = 0;
	for (Arco arco : multidigrafo.getEdges()) {
	    if (multidigrafo.getEndpoints(arco).equals(par)) {
		numeroParVertices++;
	    }
	}
	return numeroParVertices;
    }
}