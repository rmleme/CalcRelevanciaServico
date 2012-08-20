package br.ipt.servico.relevancia.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Cria conexoes com o banco de dados.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Conexao {

    public static final String ARQUIVO_STRING_CONEXAO = "bd.properties";

    private static String driver;

    private static String url;

    private static String usuario;

    private static String senha;

    private static Conexao conexao;

    private static Logger log = Logger.getLogger(Conexao.class);

    static {
	try {
	    Properties configBD = new Properties();
	    configBD.load(Thread.currentThread().getContextClassLoader()
		    .getResourceAsStream(ARQUIVO_STRING_CONEXAO));
	    driver = configBD.getProperty("driver");
	    url = configBD.getProperty("url");
	    usuario = configBD.getProperty("usuario");
	    senha = configBD.getProperty("senha");
	} catch (IOException e) {
	    log
		    .error("Erro: nao conseguiu obter a string de conexao do arquivo "
			    + ARQUIVO_STRING_CONEXAO + ".");
	}
    }

    private Conexao() {
	try {
	    DriverManager.registerDriver((Driver) Class.forName(driver)
		    .newInstance());
	} catch (Exception e) {
	    log.error("Erro: nao conseguiu carregar o driver JDBC ( " + driver
		    + ").");
	}
    }

    public static Conexao getInstance() {
	if (conexao == null) {
	    conexao = new Conexao();
	}
	return conexao;
    }

    public synchronized Connection obterConexao() throws SQLException {
	return DriverManager.getConnection(url, usuario, senha);
    }

    public synchronized int obterProximoId(String nomeSequencia)
	    throws SQLException {
	if (nomeSequencia == null || nomeSequencia.equals("")) {
	    throw new IllegalArgumentException(
		    "nomeSequencia nao pode ser nulo ou vazio.");
	}

	int proximoId = Integer.MIN_VALUE;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
	    con = this.obterConexao();
	    ps = con.prepareStatement("SELECT " + nomeSequencia
		    + ".NextVal FROM dual");
	    rs = ps.executeQuery();
	    if (rs.next()) {
		proximoId = rs.getInt(1);
	    }
	} catch (SQLException e) {
	    log.error("Erro: nao conseguiu obter o proximo id da sequencia "
		    + nomeSequencia + ".", e);
	    throw new SQLException(
		    "Erro: nao conseguiu obter o proximo id da sequencia "
			    + nomeSequencia + ".", e);
	} finally {
	    try {
		if (rs != null)
		    rs.close();
		if (ps != null)
		    ps.close();
		if (con != null)
		    con.close();
	    } catch (SQLException e) {
		log
			.fatal(
				"Erro: nao conseguiu liberar os recursos de acesso ao BD.",
				e);
	    }
	}

	return proximoId;
    }
}