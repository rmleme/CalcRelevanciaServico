package br.ipt.servico.relevancia.teste.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import br.ipt.servico.relevancia.teste.Propriedades;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesArquivo;
import br.ipt.servico.relevancia.util.Arquivo;

/**
 * Testes unitarios das funcionalidades auxiliares de manipulacao de arquivos.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteArquivo {

    @Test
    public void testeLerArquivoSucesso() throws Exception {
	String conteudo = Arquivo.lerArquivo(new File(Propriedades
		.obterPropriedade("teste.bpel.caminho")));

	assertEquals("Conteudo lido do arquivo nao bate.",
		ConstantesArquivo.CONTEUDO, conteudo);
    }

    @Test
    public void testeLerArquivoNulo() throws Exception {
	String conteudo = Arquivo.lerArquivo(null);

	assertNull("Conteudo lido do arquivo nao e nulo.", conteudo);
    }

    @Test
    public void testeLerArquivoNaoExiste() throws Exception {
	try {
	    // String vazia para garantir que o arquivo nao existe
	    Arquivo.lerArquivo(new File(""));
	    fail("FileNotFoundException esperada mas nao capturada.");
	} catch (FileNotFoundException e) {
	}
    }

    @Test
    public void testeObterExtensaoSucesso() throws Exception {
	String extensao = Arquivo.obterExtensao(new File(Propriedades
		.obterPropriedade("teste.bpel.caminho")));

	assertEquals("Extensao do arquivo nao bate.",
		ConstantesArquivo.EXTENSAO, extensao);
    }

    @Test
    public void testeObterExtensaoArquivoNulo() throws Exception {
	String extensao = Arquivo.obterExtensao(null);

	assertNull("Extensao do arquivo nao e nulo.", extensao);
    }

    @Test
    public void testeObterExtensaoArquivoNaoExiste() throws Exception {
	// String vazia para garantir que o arquivo nao existe
	String extensao = Arquivo.obterExtensao(new File(""));

	assertNull("Extensao do arquivo nao e nulo.", extensao);
    }
}