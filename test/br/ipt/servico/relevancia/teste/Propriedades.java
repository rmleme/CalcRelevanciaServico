package br.ipt.servico.relevancia.teste;

import java.io.IOException;
import java.util.Properties;

/**
 * Carrega configuracoes especificas de casos de teste do arquivo
 * <code>teste.properties</code>.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Propriedades {

    public static final String ARQUIVO_CONFIGURACAO = "teste.properties";

    private static Properties propriedades;

    public static String obterPropriedade(String nomePropriedade) {
	try {
	    return carregarPropriedades().getProperty(nomePropriedade);
	} catch (IOException e) {
	    return null;
	}
    }

    private static Properties carregarPropriedades() throws IOException {
	if (propriedades == null) {
	    propriedades = new Properties();
	    propriedades.load(Thread.currentThread().getContextClassLoader()
		    .getResourceAsStream(ARQUIVO_CONFIGURACAO));
	}
	return propriedades;
    }
}