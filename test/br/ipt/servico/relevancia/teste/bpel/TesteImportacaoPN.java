package br.ipt.servico.relevancia.teste.bpel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.excecao.BPAlreadyExistsException;
import br.ipt.servico.relevancia.teste.Propriedades;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesProcessoNegocio;

/**
 * Testes unitarios da camada BPEL (funcionalidade de importacao de processos de
 * negocio).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteImportacaoPN {

    private ProcessoNegocio pn;

    @Before
    public void setUp() throws Exception {
	this.pn = ConstantesProcessoNegocio.PROCESSO_NEGOCIO;
    }

    @Test
    public void testeImportarPNSucesso() throws Exception {
	this.pn = ProcessoNegocio.importar(new File(Propriedades
		.obterPropriedade("teste.bpel.caminho")),
		ConstantesProcessoNegocio.URL);

	Util.assertProcessoNegocioEquals(
		ConstantesProcessoNegocio.PROCESSO_NEGOCIO, this.pn);
	assertEquals("parterLinks nao bate.",
		ConstantesProcessoNegocio.PROCESSO_NEGOCIO.getPartnerLinks()
			.size(), this.pn.getPartnerLinks().size());
	Util.assertPartnerLinkEquals(ConstantesProcessoNegocio.PROCESSO_NEGOCIO
		.getPartnerLinks().get(0), this.pn.getPartnerLinks().get(0));
	Util.assertProcessoNegocioCriado(this.pn);
    }

    @Test
    public void testeImportarPNArquivoNaoExiste() throws Exception {
	try {
	    // String vazia para garantir que o arquivo nao existe
	    ProcessoNegocio.importar(new File(""),
		    ConstantesProcessoNegocio.URL);
	    fail("FileNotFoundException esperada mas nao capturada.");
	} catch (FileNotFoundException e) {
	}
    }

    @Test
    public void testeImportarPNArquivoInvalido() throws Exception {
	File arquivoBPEL = new File(Propriedades
		.obterPropriedade("teste.bpel.invalido.caminho"));

	try {
	    // O arquivo contem um XML invalido
	    ProcessoNegocio
		    .importar(arquivoBPEL, ConstantesProcessoNegocio.URL);
	    fail("IOException esperada mas nao capturada.");
	} catch (IOException e) {
	    assertEquals("Mensagem da excecao nao bate.",
		    "Erro: nao conseguiu fazer o parsing do arquivo "
			    + arquivoBPEL.getName() + ".", e.getMessage());
	}
    }

    @Test
    public void testeImportarPNJaExistente() throws Exception {
	this.pn = ProcessoNegocio.importar(new File(Propriedades
		.obterPropriedade("teste.bpel.caminho")),
		ConstantesProcessoNegocio.URL);

	try {
	    ProcessoNegocio.importar(new File(Propriedades
		    .obterPropriedade("teste.bpel.caminho")),
		    ConstantesProcessoNegocio.URL);
	    fail("BPAlreadyExistsException esperada mas nao capturada.");
	} catch (BPAlreadyExistsException e) {
	    assertEquals("Mensagem da excecao nao bate.",
		    "Processo de negocio \"" + this.pn.getNome()
			    + "\" ja existente, nao pode ser importado.", e
			    .getMessage());
	}
    }

    @Test
    public void testeImportarPNURLNula() throws Exception {
	try {
	    ProcessoNegocio.importar(new File(Propriedades
		    .obterPropriedade("teste.bpel.caminho")), null);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "urlProcessoNegocio nao pode ser nulo.", e.getMessage());
	}
    }

    @After
    public void tearDown() throws Exception {
	Util.excluirProcessoNegocio(this.pn.getId());
    }
}