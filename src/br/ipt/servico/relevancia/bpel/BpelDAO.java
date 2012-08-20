package br.ipt.servico.relevancia.bpel;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.bd.Conexao;

/**
 * DAO para persistencia de processos de negocio e <i>partner links</i>.
 * 
 * @author Rodrigo Mendes Leme
 */
public class BpelDAO {

    private Logger log = Logger.getLogger(BpelDAO.class);

    public void criarProcessoNegocio(ProcessoNegocio processoNegocio)
	    throws SQLException {
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    int proximoId = Conexao.getInstance().obterProximoId(
		    "ProcessoNegocioSeq");
	    processoNegocio.setId(proximoId);

	    sql
		    .append("INSERT INTO ProcessoNegocio (id, nome, conteudo_bpel, url) ");
	    sql.append("VALUES (?, ?, ?, ?)");

	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, proximoId);
	    ps.setString(2, processoNegocio.getNome());
	    ps.setClob(3, new StringReader(processoNegocio.getBpel()
		    .getConteudo()));
	    ps.setString(4, processoNegocio.getURL());

	    int registrosAlterados = ps.executeUpdate();
	    if (registrosAlterados != 1) {
		throw new SQLException(
			"Erro: numero de registros retornados por executeUpdate = "
				+ registrosAlterados);
	    }

	    for (PartnerLink pl : processoNegocio.getPartnerLinks()) {
		this.criarPartnerLink(pl, con);
	    }

	    con.commit();
	} catch (SQLException e) {
	    try {
		if (con != null)
		    con.rollback();
	    } catch (SQLException e1) {
		this.log.fatal(
			"Erro: nao conseguiu fazer rollback da transacao.", e1);
	    }
	    throw e;
	} finally {
	    try {
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
    }

    private void criarPartnerLink(PartnerLink partnerLink, Connection con)
	    throws SQLException {
	StringBuilder sql = new StringBuilder();
	PreparedStatement ps = null;

	try {
	    int proximoId = Conexao.getInstance().obterProximoId(
		    "PartnerLinkSeq");
	    partnerLink.setId(proximoId);

	    sql
		    .append("INSERT INTO PartnerLink (id, id_processo_negocio, nome, conteudo_wsdl, url_servico, operacoes) ");
	    sql.append("VALUES (?, ?, ?, ?, ?, ?)");

	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, proximoId);
	    ps.setInt(2, partnerLink.getProcessoNegocio().getId());
	    ps.setString(3, partnerLink.getNome());
	    ps
		    .setClob(4, new StringReader(partnerLink.getWsdl()
			    .getConteudo()));
	    ps.setString(5, partnerLink.getURLServico());
	    ps.setString(6, partnerLink.serializaOperacoes());

	    int registrosAlterados = ps.executeUpdate();
	    if (registrosAlterados != 1) {
		throw new SQLException(
			"Erro: numero de registros retornados por executeUpdate = "
				+ registrosAlterados);
	    }
	} finally {
	    try {
		if (ps != null)
		    ps.close();
	    } catch (SQLException e) {
		this.log
			.fatal(
				"Erro: nao conseguiu liberar os recursos de acesso ao BD.",
				e);
	    }
	}
    }

    public ProcessoNegocio obterProcessoNegocio(int id) {
	ProcessoNegocio processoNegocio = null;
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT id, ");
	    sql.append("       nome, ");
	    sql.append("       conteudo_bpel, ");
	    sql.append("       url ");
	    sql.append("FROM ProcessoNegocio ");
	    sql.append("WHERE id = ?");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, id);
	    rs = ps.executeQuery();

	    if (rs.next()) {
		processoNegocio = new ProcessoNegocio(rs.getInt(1), rs
			.getString(2), new DocumentoBPEL(rs.getString(3)), rs
			.getString(4));
		processoNegocio.getPartnerLinks().addAll(
			this.obterPartnerLinks(processoNegocio));
	    }
	} catch (SQLException e) {
	    processoNegocio = null;
	    this.log.error("Erro: nao conseguiu ler o processo de negocio "
		    + id + " do BD.", e);
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

	return processoNegocio;
    }

    public boolean existeProcessoNegocio(String nome) {
	boolean existeProcessoNegocio = false;
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT COUNT(*) ");
	    sql.append("FROM ProcessoNegocio ");
	    sql.append("WHERE nome = ?");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    ps.setString(1, nome);
	    rs = ps.executeQuery();

	    if (rs.next()) {
		existeProcessoNegocio = (rs.getInt(1) >= 1);
	    }
	} catch (SQLException e) {
	    existeProcessoNegocio = false;
	    this.log.error("Erro: nao conseguiu ler o processo de negocio "
		    + nome + " do BD.", e);
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

	return existeProcessoNegocio;
    }

    public List<ProcessoNegocio> obterProcessosNegocio() {
	List<ProcessoNegocio> processosNegocio = new ArrayList<ProcessoNegocio>();
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT id, ");
	    sql.append("       nome, ");
	    sql.append("       conteudo_bpel, ");
	    sql.append("       url ");
	    sql.append("FROM ProcessoNegocio");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    rs = ps.executeQuery();

	    while (rs.next()) {
		ProcessoNegocio processoNegocio = new ProcessoNegocio(rs
			.getInt(1), rs.getString(2), new DocumentoBPEL(rs
			.getString(3)), rs.getString(4));
		processoNegocio.getPartnerLinks().addAll(
			this.obterPartnerLinks(processoNegocio));
		processosNegocio.add(processoNegocio);
	    }
	} catch (SQLException e) {
	    processosNegocio = new ArrayList<ProcessoNegocio>();
	    this.log
		    .error(
			    "Erro: nao conseguiu ler os processos de negocio do BD.",
			    e);
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

	return processosNegocio;
    }

    private List<PartnerLink> obterPartnerLinks(ProcessoNegocio processoNegocio) {
	List<PartnerLink> partnerLinks = new ArrayList<PartnerLink>();
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT id, ");
	    sql.append("       id_processo_negocio, ");
	    sql.append("       nome, ");
	    sql.append("       conteudo_wsdl, ");
	    sql.append("       url_servico, ");
	    sql.append("       operacoes ");
	    sql.append("FROM PartnerLink ");
	    sql.append("WHERE id_processo_negocio = ?");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, processoNegocio.getId());
	    rs = ps.executeQuery();

	    while (rs.next()) {
		partnerLinks.add(new PartnerLink(rs.getInt(1), processoNegocio,
			rs.getString(3), new DocumentoWSDL(rs.getString(4)), rs
				.getString(5), Arrays.asList(rs.getString(6)
				.split("\\|"))));
	    }
	} catch (SQLException e) {
	    partnerLinks = new ArrayList<PartnerLink>();
	    this.log.error(
		    "Erro: nao conseguiu ler os partner links do processo de negocio "
			    + processoNegocio.getId() + " do BD.", e);
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

	return partnerLinks;
    }
}