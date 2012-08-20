package br.ipt.servico.relevancia.teste.metricas;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import br.ipt.servico.relevancia.metricas.Conexao;

/**
 * Metodos auxiliares para os testes da camada de metricas de desempenho.
 * 
 * @author Rodrigo Mendes Leme
 */
class Util {

    static void incluirMetricasDesempenho(int ciKey,
	    String nomeProcessoNegocio, String nomeOperacao,
	    Calendar dataInicio, Calendar dataFim) throws Exception {
	StringBuilder sql = new StringBuilder();
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    sql
		    .append("INSERT INTO Cube_Instance (cikey, domain_ref, process_id, revision_tag, creation_date, modify_date, state, priority, title, status, conversation_id, root_id, scope_revision, scope_csize, scope_usize, process_guid, process_type) ");
	    sql
		    .append("VALUES (?, 0, ?, '1.0', ?, ?, 5, 3, ?, 'initiated', '79235a49cb68a33b:b60b93:1353e0eb19c:-7ff7',  ?, 14, 3420, 21206, 'MD5{d76ef8701012630a4fbe085fdde01b46}', 0)");
	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, ciKey);
	    ps.setString(2, nomeProcessoNegocio);
	    ps.setTimestamp(3, new Timestamp(dataInicio.getTimeInMillis()));
	    ps.setTimestamp(4, new Timestamp(dataFim.getTimeInMillis()));
	    ps.setString(5, nomeProcessoNegocio);
	    ps.setString(6, String.valueOf(ciKey));
	    ps.executeUpdate();

	    sql.delete(0, sql.length());
	    sql
		    .append("INSERT INTO Work_Item (cikey, node_id, scope_id, count_id, domain_ref, creation_date, modify_date, state, transition, exception, exp_flag, priority, label, custom_id, idempotent_flag, process_guid, execution_type, first_delay, delay) ");
	    sql
		    .append("VALUES (?, 'BpInv0', 'BpSeq1.5', 1, 0, ?, ?, 6, 1, 0, 0, 3, ?, 'bpel://localhost/default/TesteColetaMetricas~1.0/601-BpInv0-BpSeq1.5-1', 0, 'MD5{d76ef8701012630a4fbe085fdde01b46}', 0, 0, 0)");
	    ps = con.prepareStatement(sql.toString());
	    ps.setInt(1, ciKey);
	    ps.setTimestamp(2, new Timestamp(dataInicio.getTimeInMillis()));
	    ps.setTimestamp(3, new Timestamp(dataFim.getTimeInMillis()));
	    ps.setString(4, nomeOperacao);
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

    static void excluirMetricasDesempenho(int ciKey,
	    String nomeProcessoNegocio, String nomeOperacao) throws Exception {
	Connection con = null;
	PreparedStatement ps = null;

	try {
	    con = Conexao.getInstance().obterConexao();
	    con.setAutoCommit(false);

	    ps = con
		    .prepareStatement("DELETE FROM Work_Item WHERE cikey = ? AND label = ?");
	    ps.setInt(1, ciKey);
	    ps.setString(2, nomeOperacao);
	    ps.executeUpdate();

	    ps = con
		    .prepareStatement("DELETE FROM Cube_Instance WHERE cikey = ?");
	    ps.setInt(1, ciKey);
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