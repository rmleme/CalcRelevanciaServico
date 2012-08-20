package br.ipt.servico.relevancia.teste.bd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import br.ipt.servico.relevancia.bd.Conexao;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesConexao;

/**
 * Testes unitarios da funcionalidade de conexao com o banco de dados.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteConexao {

    @Test
    public void testeGetInstance() throws Exception {
	Conexao conexao = Conexao.getInstance();

	assertNotNull("Objeto conexao e nulo.", conexao);
	assertEquals("Objeto conexao nao bate.", ConstantesConexao.CONEXAO,
		conexao);
    }

    @Test
    public void testeObterConexao() throws Exception {
	Connection con = Conexao.getInstance().obterConexao();

	assertNotNull("Conexao e nula.", con);
	assertTrue("Conexao nao e valida.", con.isValid(0));

	con.close();
    }

    @Test
    public void testeObterProximoIdSucesso() throws Exception {
	int idAnterior = Conexao.getInstance().obterProximoId(
		ConstantesConexao.NOME_SEQUENCIA);
	int id = Conexao.getInstance().obterProximoId(
		ConstantesConexao.NOME_SEQUENCIA);

	assertEquals("O id obtido da sequencia "
		+ ConstantesConexao.NOME_SEQUENCIA + " nao bate.",
		idAnterior + 1, id);
    }

    @Test
    public void testeObterProximoIdSequenciaNula() throws Exception {
	try {
	    Conexao.getInstance().obterProximoId(null);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "nomeSequencia nao pode ser nulo ou vazio.", e.getMessage());
	}
    }

    @Test
    public void testeObterProximoIdSequenciaNaoExiste() throws Exception {
	// Apenas a primeira metade do nome da sequencia para garantir que a
	// sequencia nao existe
	String sequencia = ConstantesConexao.NOME_SEQUENCIA.substring(0,
		ConstantesConexao.NOME_SEQUENCIA.length() / 2);
	try {
	    Conexao.getInstance().obterProximoId(sequencia);
	    fail("SQLException esperada mas nao capturada.");
	} catch (SQLException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "Erro: nao conseguiu obter o proximo id da sequencia "
			    + sequencia + ".", e.getMessage());
	}
    }
}