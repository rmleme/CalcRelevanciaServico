package br.ipt.servico.relevancia.teste.metricas;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ipt.servico.relevancia.metricas.ColetorMetricasDesempenho;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesColetorMetricasDesempenho;

/**
 * Testes unitarios da camada de metricas de desempenho (coleta de metricas).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteColetorMetricasDesempenho {

    @BeforeClass
    public static void setUpClass() throws Exception {
	Util.incluirMetricasDesempenho(
		ConstantesColetorMetricasDesempenho.CIKEY,
		ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
		ConstantesColetorMetricasDesempenho.NOME_OPERACAO,
		ConstantesColetorMetricasDesempenho.DATA_INICIO,
		ConstantesColetorMetricasDesempenho.DATA_FIM);
    }

    @Test
    public void testeColetarTempoMedioExecucaoSucesso() throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Double> temposMedios = coletor.coletarTempoMedioExecucao(
		ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
		ConstantesColetorMetricasDesempenho.NOME_OPERACAO);

	assertEquals("Tempo medio de execucao nao bate.",
		ConstantesColetorMetricasDesempenho.DATA_FIM.getTimeInMillis()
			- ConstantesColetorMetricasDesempenho.DATA_INICIO
				.getTimeInMillis(), temposMedios.get(
			ConstantesColetorMetricasDesempenho.CIKEY)
			.doubleValue(), 0);
    }

    @Test
    public void testeColetarTempoMedioExecucaoNomeProcessoNegocioNulo()
	    throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Double> temposMedios = coletor.coletarTempoMedioExecucao(
		null, ConstantesColetorMetricasDesempenho.NOME_OPERACAO);

	assertEquals("temposMediosExecucao nao bate.", 0, temposMedios.size());
    }

    @Test
    public void testeColetarTempoMedioExecucaoNomeOperacaoNulo()
	    throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Double> temposMedios = coletor
		.coletarTempoMedioExecucao(
			ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
			null);

	assertEquals("temposMediosExecucao nao bate.", 0, temposMedios.size());
    }

    @Test
    public void testeColetarNumeroInvocacoesSucesso() throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Integer> numerosInvocacoes = coletor
		.coletarNumeroInvocacoes(
			ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
			ConstantesColetorMetricasDesempenho.NOME_OPERACAO);

	assertEquals("Numero de invocacoes nao bate.", 1, numerosInvocacoes
		.get(ConstantesColetorMetricasDesempenho.CIKEY).intValue());
    }

    @Test
    public void testeColetarNumeroInvocacoesNomeProcessoNegocioNulo()
	    throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Integer> numerosInvocacoes = coletor
		.coletarNumeroInvocacoes(null,
			ConstantesColetorMetricasDesempenho.NOME_OPERACAO);

	assertEquals("numerosInvocacoes nao bate.", 0, numerosInvocacoes.size());
    }

    @Test
    public void testeColetarNumeroInvocacoesNomeOperacaoNulo() throws Exception {
	ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	Map<Integer, Integer> numerosInvocacoes = coletor
		.coletarNumeroInvocacoes(
			ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
			null);

	assertEquals("numerosInvocacoes nao bate.", 0, numerosInvocacoes.size());
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
	Util.excluirMetricasDesempenho(
		ConstantesColetorMetricasDesempenho.CIKEY,
		ConstantesColetorMetricasDesempenho.NOME_PROCESSO_NEGOCIO,
		ConstantesColetorMetricasDesempenho.NOME_OPERACAO);
    }
}