package br.ipt.servico.relevancia.metricas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Obtem do orquestrador BPEL metricas de desempenho de um servico: tempo medio
 * de execucao (em milissegundos) e numero de invocacoes.
 * 
 * @author Rodrigo Mendes Leme
 */
public class ColetorMetricasDesempenho {

    private Logger log = Logger.getLogger(ColetorMetricasDesempenho.class);

    public Map<Integer, Double> coletarTempoMedioExecucao(
	    String nomeProcessoNegocio, String nomeOperacao) {
	Map<Integer, Double> temposMediosExecucao = new HashMap<Integer, Double>();
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT wi.cikey, ");
	    sql
		    .append("       AVG({fn TIMESTAMPDIFF(SQL_TSI_SECOND, wi.creation_date, wi.modify_date)} * 1000 + {fn TIMESTAMPDIFF(SQL_TSI_FRAC_SECOND, wi.creation_date, wi.modify_date)} / 1000000) ");
	    sql.append("FROM Cube_Instance ci, ");
	    sql.append("     Work_Item wi ");
	    sql.append("WHERE ci.cikey = wi.cikey AND ");
	    sql.append("      wi.node_id LIKE 'BpInv%' AND ");
	    sql.append("      ci.process_id = ? AND ");
	    sql.append("      wi.label = ? ");
	    sql.append("GROUP BY wi.cikey");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    ps.setString(1, nomeProcessoNegocio);
	    ps.setString(2, nomeOperacao);
	    rs = ps.executeQuery();

	    StringBuilder textoLog = new StringBuilder(
		    "Metrica coletada: tempo medio de execucao (processo de negocio = "
			    + nomeProcessoNegocio + "; operacao = "
			    + nomeOperacao + ".");
	    while (rs.next()) {
		temposMediosExecucao.put(rs.getInt(1), rs.getDouble(2));
		textoLog.append("\n   cikey = " + rs.getInt(1)
			+ "; tempo medio = " + rs.getDouble(2));
	    }
	    this.log.trace(textoLog.toString());
	} catch (SQLException e) {
	    temposMediosExecucao = new HashMap<Integer, Double>();
	    this.log.error(
		    "Erro: nao conseguiu coletar o tempo medio de execucao da operacao "
			    + nomeOperacao + " do BD.", e);
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (SQLException e) {
		this.log
			.fatal(
				"Erro: nao conseguiu liberar os recursos de acesso ao BD.",
				e);
	    }
	}

	return temposMediosExecucao;
    }

    public Map<Integer, Integer> coletarNumeroInvocacoes(
	    String nomeProcessoNegocio, String nomeOperacao) {
	Map<Integer, Integer> numerosInvocacoes = new HashMap<Integer, Integer>();
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT wi.cikey, ");
	    sql.append("       COUNT(*) ");
	    sql.append("FROM Cube_Instance ci, ");
	    sql.append("     Work_Item wi ");
	    sql.append("WHERE ci.cikey = wi.cikey AND ");
	    sql.append("      wi.node_id LIKE 'BpInv%' AND ");
	    sql.append("      ci.process_id = ? AND ");
	    sql.append("      wi.label = ? ");
	    sql.append("GROUP BY wi.cikey, ");
	    sql.append("         wi.label");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    ps.setString(1, nomeProcessoNegocio);
	    ps.setString(2, nomeOperacao);
	    rs = ps.executeQuery();

	    StringBuilder textoLog = new StringBuilder(
		    "Metrica coletada: numero de invocacoes (processo de negocio = "
			    + nomeProcessoNegocio + "; operacao = "
			    + nomeOperacao + ".");
	    while (rs.next()) {
		numerosInvocacoes.put(rs.getInt(1), rs.getInt(2));
		textoLog.append("\n   cikey = " + rs.getInt(1)
			+ "; # invocacoes = " + rs.getInt(2));
	    }
	    this.log.trace(textoLog.toString());
	} catch (SQLException e) {
	    numerosInvocacoes = new HashMap<Integer, Integer>();
	    this.log.error(
		    "Erro: nao conseguiu coletar o numero de invocacoes da operacao "
			    + nomeOperacao + " do BD.", e);
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (SQLException e) {
		this.log
			.fatal(
				"Erro: nao conseguiu liberar os recursos de acesso ao BD.",
				e);
	    }
	}

	return numerosInvocacoes;
    }
}