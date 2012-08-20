package br.ipt.servico.relevancia.teste;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ipt.servico.relevancia.bpel.DocumentoBPEL;
import br.ipt.servico.relevancia.bpel.DocumentoWSDL;
import br.ipt.servico.relevancia.bpel.PartnerLink;
import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeFim;
import br.ipt.servico.relevancia.multidigrafo.VerticeInicio;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

/**
 * Classe utilitaria com constantes para uso em testes unitarios.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Constantes {

    public static class ConstantesArquivo {
	public static final String CONTEUDO = Propriedades
		.obterPropriedade("teste.bpel.conteudo");
	public static final String EXTENSAO = "bpel";
    }

    public static class ConstantesConexao {
	public static final br.ipt.servico.relevancia.bd.Conexao CONEXAO = br.ipt.servico.relevancia.bd.Conexao
		.getInstance();
	public static final String NOME_SEQUENCIA = "ProcessoNegocioSeq";
    }

    public static class ConstantesConexaoOrquestrador {
	public static final br.ipt.servico.relevancia.metricas.Conexao CONEXAO = br.ipt.servico.relevancia.metricas.Conexao
		.getInstance();
    }

    public static class ConstantesProcessoNegocio {
	public static final int ID = 32001;
	public static final String NOME = "Teste";
	public static final DocumentoBPEL BPEL = new DocumentoBPEL(Propriedades
		.obterPropriedade("teste.bpel.conteudo"));
	public static final String URL = "http://127.0.0.1:9700/orabpel/default/TesteColetaMetricas/1.0";
	public static final ProcessoNegocio PROCESSO_NEGOCIO = new ProcessoNegocio(
		ID, NOME, BPEL, URL);
	public static final int ID_PARTNER_LINK = 32001;
	public static final String NOME_PARTNER_LINK = "Servico";
	public static final DocumentoWSDL WSDL = new DocumentoWSDL(Propriedades
		.obterPropriedade("teste.wsdl.conteudo"));
	public static final String URL_SERVICO = "http://127.0.0.1:8080/TesteColetaMetricas/Servico/Servico";
	public static final List<String> OPERACOES = Arrays
		.asList(new String[] { "operacao1", "operacao2", "operacao3",
			"operacao4", "operacao5" });
	public static final PartnerLink PARTNER_LINK = new PartnerLink(
		ID_PARTNER_LINK, PROCESSO_NEGOCIO, NOME_PARTNER_LINK, WSDL,
		URL_SERVICO, OPERACOES);
	static {
	    PROCESSO_NEGOCIO.getPartnerLinks().add(PARTNER_LINK);
	}
    }

    public static class ConstantesArco {
	public static final String NUMERO_INVOCACOES = "3";
	public static final String ID_PROCESSO_NEGOCIO = String
		.valueOf(ConstantesProcessoNegocio.ID);
	public static final Map<Arco.Rotulo, String> ROTULOS = new HashMap<Arco.Rotulo, String>();
	public static final Arco ARCO;
	static {
	    ROTULOS.put(Arco.Rotulo.NUMERO_INVOCACOES, NUMERO_INVOCACOES);
	    ROTULOS.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO, ID_PROCESSO_NEGOCIO);
	    ARCO = new Arco(ROTULOS);
	}
    }

    public static class ConstantesVertice {
	public static final String TEMPO_MEDIO_EXECUCAO = "3500";
	public static final String TEMPO_RESPOSTA_ESPERADO = "4000";
	public static final String SERVICO_OPERACAO = ConstantesProcessoNegocio.NOME_PARTNER_LINK
		+ "." + ConstantesProcessoNegocio.OPERACOES.get(0);
	public static final String URL = ConstantesProcessoNegocio.URL_SERVICO;
	public static final Map<Vertice.Rotulo, String> ROTULOS = new HashMap<Vertice.Rotulo, String>();
	public static final Vertice VERTICE;
	static {
	    ROTULOS.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO,
		    TEMPO_MEDIO_EXECUCAO);
	    ROTULOS.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO,
		    TEMPO_RESPOSTA_ESPERADO);
	    ROTULOS.put(Vertice.Rotulo.SERVICO_OPERACAO, SERVICO_OPERACAO);
	    ROTULOS.put(Vertice.Rotulo.URL, URL);
	    VERTICE = new Vertice(ROTULOS);
	}
    }

    public static class ConstantesVerticeInicio {
	public static final String ID_PROCESSO_NEGOCIO = String
		.valueOf(ConstantesProcessoNegocio.ID);
	public static final Map<Vertice.Rotulo, String> ROTULOS = new HashMap<Vertice.Rotulo, String>();
	public static final VerticeInicio VERTICE_INICIO;
	static {
	    ROTULOS
		    .put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
			    ID_PROCESSO_NEGOCIO);
	    VERTICE_INICIO = new VerticeInicio(ROTULOS);
	}
    }

    public static class ConstantesVerticeFim {
	public static final String ID_PROCESSO_NEGOCIO = String
		.valueOf(ConstantesProcessoNegocio.ID);
	public static final Map<Vertice.Rotulo, String> ROTULOS = new HashMap<Vertice.Rotulo, String>();
	public static final VerticeFim VERTICE_FIM;
	static {
	    ROTULOS
		    .put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
			    ID_PROCESSO_NEGOCIO);
	    VERTICE_FIM = new VerticeFim(ROTULOS);
	}
    }

    public static class ConstantesMultidigrafo {
	public static final String SERVICO_OPERACAO_1 = ConstantesProcessoNegocio.NOME_PARTNER_LINK
		+ "." + ConstantesProcessoNegocio.OPERACOES.get(0);
	public static final String SERVICO_OPERACAO_2 = ConstantesProcessoNegocio.NOME_PARTNER_LINK
		+ "." + ConstantesProcessoNegocio.OPERACOES.get(1);
	public static final String URL = ConstantesProcessoNegocio.URL_SERVICO;
	public static final String ID_PROCESSO_NEGOCIO = String
		.valueOf(ConstantesProcessoNegocio.ID);
	public static final Graph<Vertice, Arco> MULTIDIGRAFO = new DirectedSparseMultigraph<Vertice, Arco>();
	protected static VerticeInicio vInicio;
	protected static Vertice v1;
	protected static Vertice v2;
	protected static VerticeFim vFim;
	protected static Arco a1;
	protected static Arco a2;
	protected static Arco a3;
	protected static Arco a4;
	protected static Arco a5;
	protected static Arco a6;
	static {
	    Map<Vertice.Rotulo, String> rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    vInicio = new VerticeInicio(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "5000");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "4000");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    SERVICO_OPERACAO_1);
	    rotulosVertice.put(Vertice.Rotulo.URL, URL);
	    v1 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "4000");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "5000");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    SERVICO_OPERACAO_2);
	    rotulosVertice.put(Vertice.Rotulo.URL, URL);
	    v2 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    vFim = new VerticeFim(rotulosVertice);

	    Map<Arco.Rotulo, String> rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "4");
	    a1 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "4");
	    a2 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "12");
	    a3 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "8");
	    a4 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "5");
	    a5 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    a6 = new Arco(rotulosArco);

	    MULTIDIGRAFO.addVertex(vInicio);
	    MULTIDIGRAFO.addVertex(v1);
	    MULTIDIGRAFO.addVertex(v2);
	    MULTIDIGRAFO.addVertex(vFim);
	    MULTIDIGRAFO.addEdge(a1, vInicio, v1);
	    MULTIDIGRAFO.addEdge(a2, v1, v2);
	    MULTIDIGRAFO.addEdge(a3, v1, v2);
	    MULTIDIGRAFO.addEdge(a4, v2, v1);
	    MULTIDIGRAFO.addEdge(a5, v2, v2);
	    MULTIDIGRAFO.addEdge(a6, v2, vFim);
	}
    }

    public static class ConstantesCalculadoraRelevancia extends
	    ConstantesMultidigrafo {
	public static double RELEVANCIA_OPERACAO_1;
	public static double RELEVANCIA_OPERACAO_2;
	static {
	    double mediaGrauDeEntrada = ((double) MULTIDIGRAFO.inDegree(v1) + MULTIDIGRAFO
		    .inDegree(v2))
		    / (MULTIDIGRAFO.getVertexCount() - 2);

	    double mediaNumeroInvocacoes = (Double.parseDouble(a1
		    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))
		    + Double.parseDouble(a2
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))
		    + Double.parseDouble(a3
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))
		    + Double.parseDouble(a4
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES)) + Double
		    .parseDouble(a5
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES)))
		    / (MULTIDIGRAFO.getVertexCount() - 2);

	    RELEVANCIA_OPERACAO_1 = (MULTIDIGRAFO.inDegree(v1) / mediaGrauDeEntrada)
		    * ((Double.parseDouble(a1
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES)) + Double
			    .parseDouble(a4
				    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))) / mediaNumeroInvocacoes)
		    * (Double
			    .parseDouble(v1
				    .obterValorRotulo(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO)) / Double
			    .parseDouble(v1
				    .obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO)));

	    RELEVANCIA_OPERACAO_2 = (MULTIDIGRAFO.inDegree(v2) / mediaGrauDeEntrada)
		    * ((Double.parseDouble(a2
			    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))
			    + Double
				    .parseDouble(a3
					    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES)) + Double
			    .parseDouble(a5
				    .obterValorRotulo(Arco.Rotulo.NUMERO_INVOCACOES))) / mediaNumeroInvocacoes)
		    * (Double
			    .parseDouble(v2
				    .obterValorRotulo(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO)) / Double
			    .parseDouble(v2
				    .obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO)));
	}
    }

    public static class ConstantesColetorMetricasDesempenho {
	public static final int CIKEY = 32001;
	public static final String NOME_PROCESSO_NEGOCIO = ConstantesProcessoNegocio.NOME;
	public static final String NOME_OPERACAO = ConstantesProcessoNegocio.OPERACOES
		.get(0);
	public static final Calendar DATA_INICIO = Calendar.getInstance();
	public static final Calendar DATA_FIM = Calendar.getInstance();
	static {
	    DATA_INICIO.set(2012, 5, 1, 21, 35, 12);
	    DATA_INICIO.set(Calendar.MILLISECOND, 0);
	    DATA_FIM.setTimeInMillis(DATA_INICIO.getTimeInMillis());
	    DATA_FIM.add(Calendar.MILLISECOND, 3120);
	}
    }

    public static class ConstantesParserBPEL {
	public static final List<ProcessoNegocio> PROCESSOS_NEGOCIO = Arrays
		.asList(new ProcessoNegocio[] { ConstantesProcessoNegocio.PROCESSO_NEGOCIO });
	public static final String ID_PROCESSO_NEGOCIO = String
		.valueOf(ConstantesProcessoNegocio.ID);
	public static final int NUMERO_VERTICES_INICIO = 1;
	public static final int NUMERO_VERTICES_FIM = 1;
	public static final Graph<Vertice, Arco> MULTIDIGRAFO = new DirectedSparseMultigraph<Vertice, Arco>();
	public static VerticeInicio VERTICE_INICIO;
	public static VerticeFim VERTICE_FIM;
	static {
	    Map<Vertice.Rotulo, String> rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    VERTICE_INICIO = new VerticeInicio(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "0");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "0");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    ConstantesProcessoNegocio.NOME_PARTNER_LINK + "."
			    + ConstantesProcessoNegocio.OPERACOES.get(0));
	    rotulosVertice.put(Vertice.Rotulo.URL,
		    ConstantesProcessoNegocio.URL_SERVICO);
	    Vertice v1 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "0");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "0");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    ConstantesProcessoNegocio.NOME_PARTNER_LINK + "."
			    + ConstantesProcessoNegocio.OPERACOES.get(1));
	    rotulosVertice.put(Vertice.Rotulo.URL,
		    ConstantesProcessoNegocio.URL_SERVICO);
	    Vertice v2 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "0");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "0");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    ConstantesProcessoNegocio.NOME_PARTNER_LINK + "."
			    + ConstantesProcessoNegocio.OPERACOES.get(2));
	    rotulosVertice.put(Vertice.Rotulo.URL,
		    ConstantesProcessoNegocio.URL_SERVICO);
	    Vertice v3 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "0");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "0");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    ConstantesProcessoNegocio.NOME_PARTNER_LINK + "."
			    + ConstantesProcessoNegocio.OPERACOES.get(3));
	    rotulosVertice.put(Vertice.Rotulo.URL,
		    ConstantesProcessoNegocio.URL_SERVICO);
	    Vertice v4 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, "0");
	    rotulosVertice.put(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, "0");
	    rotulosVertice.put(Vertice.Rotulo.SERVICO_OPERACAO,
		    ConstantesProcessoNegocio.NOME_PARTNER_LINK + "."
			    + ConstantesProcessoNegocio.OPERACOES.get(4));
	    rotulosVertice.put(Vertice.Rotulo.URL,
		    ConstantesProcessoNegocio.URL_SERVICO);
	    Vertice v5 = new Vertice(rotulosVertice);

	    rotulosVertice = new HashMap<Vertice.Rotulo, String>();
	    rotulosVertice.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    VERTICE_FIM = new VerticeFim(rotulosVertice);

	    Map<Arco.Rotulo, String> rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a1 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a2 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a3 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a4 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a5 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a6 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a7 = new Arco(rotulosArco);

	    rotulosArco = new HashMap<Arco.Rotulo, String>();
	    rotulosArco.put(Arco.Rotulo.ID_PROCESSO_NEGOCIO,
		    ID_PROCESSO_NEGOCIO);
	    rotulosArco.put(Arco.Rotulo.NUMERO_INVOCACOES, "0");
	    Arco a8 = new Arco(rotulosArco);

	    MULTIDIGRAFO.addVertex(VERTICE_INICIO);
	    MULTIDIGRAFO.addVertex(v1);
	    MULTIDIGRAFO.addVertex(v2);
	    MULTIDIGRAFO.addVertex(v3);
	    MULTIDIGRAFO.addVertex(v4);
	    MULTIDIGRAFO.addVertex(v5);
	    MULTIDIGRAFO.addVertex(VERTICE_FIM);
	    MULTIDIGRAFO.addEdge(a1, VERTICE_INICIO, v1);
	    MULTIDIGRAFO.addEdge(a2, v1, v2);
	    MULTIDIGRAFO.addEdge(a3, v1, v3);
	    MULTIDIGRAFO.addEdge(a4, v2, v4);
	    MULTIDIGRAFO.addEdge(a5, v3, v4);
	    MULTIDIGRAFO.addEdge(a6, v4, v5);
	    MULTIDIGRAFO.addEdge(a7, v4, v1);
	    MULTIDIGRAFO.addEdge(a8, v5, VERTICE_FIM);
	}
    }
}