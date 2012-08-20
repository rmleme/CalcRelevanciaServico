package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.multidigrafo.MultidigrafoUtil;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesMultidigrafo;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesProcessoNegocio;

/**
 * Testes unitarios das funcionalidades auxiliares de manipulacao de
 * multidigrafos.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteMultidigrafoUtil {

    @Test
    public void testeContemProcessoNegocioSucesso() throws Exception {
	assertTrue("processo de negocio nao bate.", MultidigrafoUtil
		.contemProcessoNegocio(ConstantesMultidigrafo.MULTIDIGRAFO,
			ConstantesProcessoNegocio.PROCESSO_NEGOCIO));
    }

    @Test
    public void testeContemProcessoNegocioMultidigrafoNulo() throws Exception {
	assertFalse("processo de negocio nao bate.", MultidigrafoUtil
		.contemProcessoNegocio(null,
			ConstantesProcessoNegocio.PROCESSO_NEGOCIO));
    }

    @Test
    public void testeContemProcessoNegocioPNNulo() throws Exception {
	assertFalse("processo de negocio nao bate.", MultidigrafoUtil
		.contemProcessoNegocio(ConstantesMultidigrafo.MULTIDIGRAFO,
			null));
    }

    @Test
    public void testeContemProcessoNegocioPNNaoExiste() throws Exception {
	ProcessoNegocio processoNegocio = new ProcessoNegocio((int) System
		.currentTimeMillis(), ConstantesProcessoNegocio.NOME,
		ConstantesProcessoNegocio.BPEL, ConstantesProcessoNegocio.URL);

	assertFalse("processo de negocio nao bate.", MultidigrafoUtil
		.contemProcessoNegocio(ConstantesMultidigrafo.MULTIDIGRAFO,
			processoNegocio));
    }
}