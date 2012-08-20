package br.ipt.servico.relevancia.multidigrafo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.bd.Conexao;
import edu.uci.ics.jung.graph.Graph;

/**
 * DAO para persistencia de multidigrafos.
 * 
 * @author Rodrigo Mendes Leme
 */
public class MultidigrafoDAO {

    private Logger log = Logger.getLogger(MultidigrafoDAO.class);

    public void criarMultidigrafo(Graph<Vertice, Arco> multidigrafo)
	    throws SQLException {
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    sql.append("INSERT INTO Multidigrafo (conteudo_serializado) ");
	    sql.append("VALUES (?)");

	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);

	    this.excluirMultidigrafo(con);

	    ps = con.prepareStatement(sql.toString());
	    ps.setBlob(1, this.serializaMultidigrafo(multidigrafo));

	    int registrosAlterados = ps.executeUpdate();
	    if (registrosAlterados != 1) {
		throw new SQLException(
			"Erro: numero de registros retornados por executeUpdate = "
				+ registrosAlterados);
	    }

	    con.commit();
	} catch (IOException e) {
	    this.log.error("Erro: nao conseguiu serializar o multidigrafo.", e);
	    try {
		if (con != null)
		    con.rollback();
	    } catch (SQLException e1) {
		this.log.fatal(
			"Erro: nao conseguiu fazer rollback da transacao.", e1);
	    }
	    throw new SQLException(e);
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

    private void excluirMultidigrafo(Connection con) throws SQLException {
	StringBuilder sql = new StringBuilder();
	PreparedStatement ps = null;

	try {
	    sql.append("DELETE FROM Multidigrafo");

	    ps = con.prepareStatement(sql.toString());
	    ps.executeUpdate();
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

    public Graph<Vertice, Arco> obterMultidigrafo() {
	Graph<Vertice, Arco> multidigrafo = null;
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    sql.append("SELECT conteudo_serializado ");
	    sql.append("FROM Multidigrafo");

	    con = Conexao.getInstance().obterConexao();
	    ps = con.prepareStatement(sql.toString());
	    rs = ps.executeQuery();

	    if (rs.next()) {
		multidigrafo = this.desserializaMultidigrafo(rs.getBlob(1)
			.getBinaryStream());
	    }
	} catch (ClassNotFoundException e) {
	    multidigrafo = null;
	    this.log.error("Erro: nao conseguiu desserializar o multidigrafo.",
		    e);
	} catch (IOException e) {
	    multidigrafo = null;
	    this.log.error("Erro: nao conseguiu desserializar o multidigrafo.",
		    e);
	} catch (SQLException e) {
	    multidigrafo = null;
	    this.log.error("Erro: nao conseguiu ler o multidigrafo do BD.", e);
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

	return multidigrafo;
    }

    private InputStream serializaMultidigrafo(Graph<Vertice, Arco> multidigrafo)
	    throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(multidigrafo);
	oos.close();
	return new ByteArrayInputStream(baos.toByteArray());
    }

    private Graph<Vertice, Arco> desserializaMultidigrafo(InputStream is)
	    throws IOException, ClassNotFoundException {
	ObjectInputStream ois = new ObjectInputStream(is);
	Graph<Vertice, Arco> multidigrafo = (Graph<Vertice, Arco>) ois
		.readObject();
	ois.close();
	return multidigrafo;
    }
}