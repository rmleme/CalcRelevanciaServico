package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ipt.servico.relevancia.bd.Conexao;
import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import edu.uci.ics.jung.graph.Graph;

/**
 * Metodos auxiliares para os testes da camada de multidigrafo.
 * 
 * @author Rodrigo Mendes Leme
 */
class Util {

    static void assertMultidigrafoCriado(Graph<Vertice, Arco> multidigrafo) {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    con = Conexao.getInstance().obterConexao();
	    ps = con
		    .prepareStatement("SELECT conteudo_serializado FROM Multidigrafo");
	    rs = ps.executeQuery();

	    assertTrue("Nenhum multidigrafo foi retornado pela consulta.", rs
		    .next());
	    Util.assertMultidigrafoEquals(multidigrafo,
		    desserializaMultidigrafo(rs.getBlob(1).getBinaryStream()));
	    assertFalse("Mais de um multidigrafo foi retornado pela consulta.",
		    rs.next());
	} catch (Exception e) {
	    fail("Um erro de banco de dados ocorreu durante o teste.");
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		fail("Um erro de banco de dados ocorreu durante o teste.");
	    }
	}
    }

    static void assertMultidigrafoEquals(
	    Graph<Vertice, Arco> multidigrafoEsperado,
	    Graph<Vertice, Arco> multidigrafoObtido) throws Exception {
	assertEquals("getVertexCount() nao bate.", multidigrafoEsperado
		.getVertexCount(), multidigrafoObtido.getVertexCount());
	assertEquals("getEdgeCount() nao bate.", multidigrafoEsperado
		.getEdgeCount(), multidigrafoObtido.getEdgeCount());
	for (Vertice vertice : multidigrafoObtido.getVertices()) {
	    assertTrue("vertice nao bate.", multidigrafoEsperado
		    .containsVertex(vertice));
	}
    }

    static Graph<Vertice, Arco> salvarMultidigrafo() throws Exception {
	Graph<Vertice, Arco> multidigrafo = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    con = Conexao.getInstance().obterConexao();
	    ps = con
		    .prepareStatement("SELECT conteudo_serializado FROM Multidigrafo");
	    rs = ps.executeQuery();
	    if (rs.next()) {
		multidigrafo = desserializaMultidigrafo(rs.getBlob(1)
			.getBinaryStream());
	    }

	    ps = con.prepareStatement("DELETE FROM Multidigrafo");
	    ps.executeUpdate();
	} catch (Exception e) {
	    multidigrafo = null;
	    fail("Um erro de banco de dados ocorreu durante o teste.");
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		fail("Um erro de banco de dados ocorreu durante o teste.");
	    }
	}

	return multidigrafo;
    }

    static void restaurarMultidigrafo(Graph<Vertice, Arco> multidigrafo)
	    throws Exception {
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);

	    ps = con.prepareStatement("DELETE FROM Multidigrafo");
	    ps.executeUpdate();

	    if (multidigrafo != null) {
		ps = con
			.prepareStatement("INSERT INTO Multidigrafo (conteudo_serializado) VALUES (?)");
		ps.setBlob(1, serializaMultidigrafo(multidigrafo));
		ps.executeUpdate();
	    }

	    con.commit();
	} catch (Exception e) {
	    try {
		con.rollback();
	    } catch (SQLException e1) {
		fail("Um erro de banco de dados ocorreu durante o teste.");
	    }
	    fail("Um erro de banco de dados ocorreu durante o teste.");
	} finally {
	    try {
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (Exception e) {
		fail("Um erro de banco de dados ocorreu durante o teste.");
	    }
	}
    }

    private static ByteArrayInputStream serializaMultidigrafo(
	    Graph<Vertice, Arco> multidigrafo) throws Exception {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(multidigrafo);
	oos.close();
	return new ByteArrayInputStream(baos.toByteArray());
    }

    private static Graph<Vertice, Arco> desserializaMultidigrafo(InputStream is)
	    throws Exception {
	ObjectInputStream ois = new ObjectInputStream(is);
	Graph<Vertice, Arco> multidigrafo = (Graph<Vertice, Arco>) ois
		.readObject();
	ois.close();
	return multidigrafo;
    }
}