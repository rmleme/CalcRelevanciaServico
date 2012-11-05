package br.ipt.servico.relevancia.selecao;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeFim;
import br.ipt.servico.relevancia.multidigrafo.VerticeInicio;
import edu.uci.ics.jung.graph.Graph;

/**
 * Calcula a relevancia dos servicos de um dado multidigrafo representando
 * processos de negocio.
 * 
 * @author Rodrigo Mendes Leme
 */
public class CalculadoraRelevanciaServicos {

    private Logger log = Logger.getLogger(CalculadoraRelevanciaServicos.class);

    public Set<Relevancia> calcular(Graph<Vertice, Arco> multidigrafo) {
	if (multidigrafo == null) {
	    return new HashSet<Relevancia>();
	}

	int numeroVertices = 0;
	int somaGrauDeEntrada = 0;
	double somaNumeroInvocacoes = 0;
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
		numeroVertices++;
		somaGrauDeEntrada += multidigrafo.inDegree(vertice);
		for (Arco arco : multidigrafo.getInEdges(vertice)) {
		    somaNumeroInvocacoes += Double.parseDouble(arco
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES));
		}
	    }
	}

	Set<Relevancia> servicos = new HashSet<Relevancia>();
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
		Relevancia r = new Relevancia();
		r.setServicoOperacao(vertice
			.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO));
		r.setRelevanciaEstatica(this.calcularRelevanciaEstatica(
			multidigrafo, vertice, numeroVertices,
			somaGrauDeEntrada));
		r.setRelevanciaDinamica(this.calcularRelevanciaDinamica(
			multidigrafo, vertice, numeroVertices,
			somaNumeroInvocacoes));
		r.setRelevanciaDeDesempenho(this
			.calcularRelevanciaDeDesempenho(vertice));
		r.setRelevancia(this.calcularRelevancia(vertice, r
			.getRelevanciaEstatica(), r.getRelevanciaDinamica(), r
			.getRelevanciaDeDesempenho()));
		servicos.add(r);
	    }
	}
	return servicos;
    }

    private double calcularRelevanciaEstatica(
	    Graph<Vertice, Arco> multidigrafo, Vertice vertice,
	    int numeroVertices, int somaGrauDeEntrada) {
	double relevanciaEstatica = ((double) multidigrafo.inDegree(vertice))
		/ ((double) somaGrauDeEntrada / numeroVertices);
	this.log.debug("Relevancia estatica = " + relevanciaEstatica);
	return relevanciaEstatica;
    }

    private double calcularRelevanciaDinamica(
	    Graph<Vertice, Arco> multidigrafo, Vertice vertice,
	    int numeroVertices, double somaNumeroInvocacoes) {
	double numeroInvocacoes = 0;
	for (Arco arco : multidigrafo.getInEdges(vertice)) {
	    numeroInvocacoes += Double.parseDouble(arco
		    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES));
	}
	double relevanciaDinamica = numeroInvocacoes
		/ (somaNumeroInvocacoes / numeroVertices);
	this.log.debug("Relevancia dinamica = " + relevanciaDinamica);
	return relevanciaDinamica;
    }

    private double calcularRelevanciaDeDesempenho(Vertice vertice) {
	double relevanciaDeDesempenho = Double.parseDouble(vertice
		.obterValorRotulo(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO))
		/ Double
			.parseDouble(vertice
				.obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO));
	this.log.debug("Relevancia de desempenho = " + relevanciaDeDesempenho);
	return relevanciaDeDesempenho;
    }

    private double calcularRelevancia(Vertice vertice,
	    double relevanciaEstatica, double relevanciaDinamica,
	    double relevanciaDeDesempenho) {
	this.log.debug("Calculando a relevancia do servico "
		+ vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO)
		+ "...");
	double relevancia = relevanciaEstatica * relevanciaDinamica
		* relevanciaDeDesempenho;
	this.log.debug("Relevancia = " + relevancia);
	return relevancia;
    }
}