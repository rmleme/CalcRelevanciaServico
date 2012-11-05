package br.ipt.servico.relevancia.teste.selecao;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import br.ipt.servico.relevancia.selecao.CalculadoraRelevanciaServicos;
import br.ipt.servico.relevancia.selecao.Relevancia;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesCalculadoraRelevancia;

/**
 * Testes unitarios da camada de selecao de servicos (funcionalidade de calculo
 * da relevancia).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteCalculadoraRelevanciaServicos {

    @Test
    public void testeCalcularSucesso() throws Exception {
	CalculadoraRelevanciaServicos calculadora = new CalculadoraRelevanciaServicos();

	Set<Relevancia> servicos = calculadora
		.calcular(ConstantesCalculadoraRelevancia.MULTIDIGRAFO);

	assertEquals(
		"servicos nao bate.",
		ConstantesCalculadoraRelevancia.MULTIDIGRAFO.getVertexCount() - 2,
		servicos.size());
	for (Relevancia r : servicos) {
	    if (r.equals(ConstantesCalculadoraRelevancia.RELEVANCIA_OPERACAO_1)) {
		assertEquals("servicos[SERVICO_OPERACAO_1] nao bate.",
			ConstantesCalculadoraRelevancia.RELEVANCIA_OPERACAO_1
				.getRelevancia(), r.getRelevancia(), 0);
	    }
	    if (r.equals(ConstantesCalculadoraRelevancia.RELEVANCIA_OPERACAO_2)) {
		assertEquals("servicos[SERVICO_OPERACAO_2] nao bate.",
			ConstantesCalculadoraRelevancia.RELEVANCIA_OPERACAO_2
				.getRelevancia(), r.getRelevancia(), 0);
	    }
	}
    }

    @Test
    public void testeCalcularMultidigrafoNulo() throws Exception {
	CalculadoraRelevanciaServicos calculadora = new CalculadoraRelevanciaServicos();

	Set<Relevancia> servicos = calculadora.calcular(null);

	assertEquals("servicos nao bate.", 0, servicos.size());
    }
}