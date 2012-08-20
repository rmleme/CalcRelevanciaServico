package br.ipt.servico.relevancia.teste.bpel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.ipt.servico.relevancia.bd.Conexao;
import br.ipt.servico.relevancia.bpel.DocumentoBPEL;
import br.ipt.servico.relevancia.bpel.DocumentoWSDL;
import br.ipt.servico.relevancia.bpel.PartnerLink;
import br.ipt.servico.relevancia.bpel.ProcessoNegocio;

/**
 * Metodos auxiliares para os testes da camada BPEL.
 * 
 * @author Rodrigo Mendes Leme
 */
class Util {

    static void assertProcessoNegocioCriado(ProcessoNegocio pn) {
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
	    ps.setInt(1, pn.getId());
	    rs = ps.executeQuery();

	    assertTrue(
		    "Nenhum processo de negocio foi retornado pela consulta.",
		    rs.next());
	    assertEquals("id nao bate.", pn.getId(), rs.getInt(1));
	    Util.assertProcessoNegocioEquals(pn, new ProcessoNegocio(rs
		    .getInt(1), rs.getString(2), new DocumentoBPEL(rs
		    .getString(3)), rs.getString(4)));
	    assertFalse("Mais de um processo de negocio (id = " + pn.getId()
		    + ") foi retornado pela consulta.", rs.next());

	    sql.delete(0, sql.length());
	    sql.append("SELECT id, ");
	    sql.append("       id_processo_negocio, ");
	    sql.append("       nome, ");
	    sql.append("       conteudo_wsdl, ");
	    sql.append("       url_servico, ");
	    sql.append("       operacoes ");
	    sql.append("FROM PartnerLink ");
	    sql.append("WHERE id_processo_negocio = ?");
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, pn.getId());
	    rs = ps.executeQuery();

	    assertTrue("Nenhum partner link foi retornado pela consulta.", rs
		    .next());
	    assertEquals("id nao bate.", pn.getPartnerLinks().get(0).getId(),
		    rs.getInt(1));
	    assertEquals("processoNegocio.id nao bate.", pn.getPartnerLinks()
		    .get(0).getProcessoNegocio().getId(), rs.getInt(2));
	    Util.assertPartnerLinkEquals(pn.getPartnerLinks().get(0),
		    new PartnerLink(rs.getInt(1), pn, rs.getString(3),
			    new DocumentoWSDL(rs.getString(4)),
			    rs.getString(5), Arrays.asList(rs.getString(6)
				    .split("\\|"))));
	    assertFalse("Mais de um partner link (processoNegocio.id = "
		    + pn.getId() + ") foi retornado pela consulta.", rs.next());
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

    static void assertProcessoNegocioEquals(ProcessoNegocio pnEsperado,
	    ProcessoNegocio pnObtido) {
	assertEquals("nome nao bate.", pnEsperado.getNome(), pnObtido.getNome());
	assertEquals("bpel nao bate.", pnEsperado.getBpel().getConteudo(),
		pnObtido.getBpel().getConteudo());
	assertEquals("url nao bate.", pnEsperado.getURL(), pnObtido.getURL());
    }

    static void assertPartnerLinkEquals(PartnerLink plEsperado,
	    PartnerLink plObtido) {
	assertEquals("nome nao bate.", plEsperado.getNome(), plObtido.getNome());
	assertEquals("wsdl nao bate.", plEsperado.getWsdl().getConteudo(),
		plObtido.getWsdl().getConteudo());
	assertEquals("urlServico nao bate.", plEsperado.getURLServico(),
		plObtido.getURLServico());
	assertOperacoesEquals(plEsperado.getOperacoes(), plObtido
		.getOperacoes());
    }

    private static void assertOperacoesEquals(List<String> operacoesEsperado,
	    List<String> operacoesObtido) {
	assertEquals("operacoes nao bate.", operacoesEsperado.size(),
		operacoesObtido.size());
	Collections.sort(operacoesEsperado);
	Collections.sort(operacoesObtido);
	for (int i = 0; i < operacoesEsperado.size(); i++) {
	    assertEquals("operacoes nao bate.", operacoesEsperado.get(i),
		    operacoesObtido.get(i));
	}
    }

    static void excluirProcessoNegocio(int id) throws Exception {
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);

	    ps = con
		    .prepareStatement("DELETE FROM PartnerLink WHERE id_processo_negocio = ?");
	    ps.setInt(1, id);
	    ps.executeUpdate();

	    ps = con
		    .prepareStatement("DELETE FROM ProcessoNegocio WHERE id = ?");
	    ps.setInt(1, id);
	    ps.executeUpdate();

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
}