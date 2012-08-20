package br.ipt.servico.relevancia.teste.metricas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import br.ipt.servico.relevancia.metricas.Conexao;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesConexaoOrquestrador;

/**
 * Testes unitarios da funcionalidade de conexao com o banco de dados do
 * orquestrador BPEL.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteConexao {

    @Test
    public void testeGetInstance() throws Exception {
	Conexao conexao = Conexao.getInstance();

	assertNotNull("Objeto conexao e nulo.", conexao);
	assertEquals("Objeto conexao nao bate.",
		ConstantesConexaoOrquestrador.CONEXAO, conexao);
    }

    @Test
    public void testeObterConexao() throws Exception {
	Connection con = Conexao.getInstance().obterConexao();

	assertNotNull("Conexao e nula.", con);

	con.close();
    }
}