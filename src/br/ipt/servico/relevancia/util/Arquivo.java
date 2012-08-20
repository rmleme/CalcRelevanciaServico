package br.ipt.servico.relevancia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Metodos auxiliares para manipulacao de arquivos.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Arquivo {

    private static final String ENCODING = "UTF-8";

    public static String lerArquivo(File arquivo) throws FileNotFoundException {
	if (arquivo == null) {
	    return null;
	}

	StringBuilder conteudo = new StringBuilder();
	Scanner scanner = new Scanner(new FileInputStream(arquivo), ENCODING);
	try {
	    while (scanner.hasNextLine()) {
		String linha = scanner.nextLine();
		conteudo.append(linha + System.getProperty("line.separator"));
	    }
	} finally {
	    scanner.close();
	}

	return conteudo.toString();
    }

    public static String obterExtensao(File arquivo) {
	if (arquivo == null) {
	    return null;
	}

	String extensao = null;
	String s = arquivo.getName();
	int i = s.lastIndexOf('.');

	if (i > 0 && i < s.length() - 1) {
	    extensao = s.substring(i + 1).toLowerCase();
	}
	return extensao;
    }
}