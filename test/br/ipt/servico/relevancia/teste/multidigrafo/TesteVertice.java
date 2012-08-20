package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.Vertice.Rotulo;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesVertice;

/**
 * Testes unitarios da camada de multidigrafo (vertices).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteVertice {

    @Test
    public void testeConstrutorSucesso() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);

	assertEquals("rotulos[TEMPO_MEDIO_EXECUCAO] nao bate.",
		ConstantesVertice.TEMPO_MEDIO_EXECUCAO, vertice
			.obterValorRotulo(Rotulo.TEMPO_MEDIO_EXECUCAO));
	assertEquals("rotulos[TEMPO_RESPOSTA_ESPERADO] nao bate.",
		ConstantesVertice.TEMPO_RESPOSTA_ESPERADO, vertice
			.obterValorRotulo(Rotulo.TEMPO_RESPOSTA_ESPERADO));
	assertEquals("rotulos[SERVICO_OPERACAO] nao bate.",
		ConstantesVertice.SERVICO_OPERACAO, vertice
			.obterValorRotulo(Rotulo.SERVICO_OPERACAO));
	assertEquals("rotulos[URL] nao bate.", ConstantesVertice.URL, vertice
		.obterValorRotulo(Rotulo.URL));
    }

    @Test
    public void testeConstrutorRotulosNulo() {
	try {
	    new Vertice(null);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "rotulos nao pode ser nulo.", e.getMessage());
	}
    }

    @Test
    public void testeConstrutorRotulosInvalido() {
	try {
	    Map<Rotulo, String> rotulos = new HashMap<Rotulo, String>(
		    ConstantesVertice.ROTULOS);
	    rotulos.put(null, "");
	    new Vertice(rotulos);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um vertice comum deve conter apenas os rotulos "
			    + Rotulo.TEMPO_MEDIO_EXECUCAO + ", "
			    + Rotulo.TEMPO_RESPOSTA_ESPERADO + ", "
			    + Rotulo.SERVICO_OPERACAO + " e " + Rotulo.URL
			    + ".", e.getMessage());
	}
    }

    @Test
    public void testeAdicionarRotuloSucesso() {
	Vertice vertice = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));
	String tempoMedioExecucaoNovo = String.valueOf(Integer
		.valueOf(ConstantesVertice.TEMPO_MEDIO_EXECUCAO) + 1000);

	vertice.adicionarRotulo(Rotulo.TEMPO_MEDIO_EXECUCAO,
		tempoMedioExecucaoNovo);

	assertEquals("rotulos[TEMPO_MEDIO_EXECUCAO] nao bate.",
		tempoMedioExecucaoNovo, vertice
			.obterValorRotulo(Rotulo.TEMPO_MEDIO_EXECUCAO));
    }

    @Test
    public void testeAdicionarRotuloNulo() {
	Vertice vertice = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));

	try {
	    vertice.adicionarRotulo(null, "");
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "rotulo nao pode ser nulo.", e.getMessage());
	}
    }

    @Test
    public void testeAdicionarRotuloValorNulo() {
	Vertice vertice = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));

	try {
	    vertice.adicionarRotulo(Rotulo.SERVICO_OPERACAO, null);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "rotulo nao pode ser nulo.", e.getMessage());
	}
    }

    @Test
    public void testeAdicionarRotuloInvalido() {
	Vertice vertice = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));

	try {
	    vertice.adicionarRotulo(Rotulo.ID_PROCESSO_NEGOCIO, "1");
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    Rotulo.ID_PROCESSO_NEGOCIO
			    + ": rotulo nao permitido em um vertice comum.", e
			    .getMessage());
	}
    }

    @Test
    public void testeObterValorRotulo() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);

	assertEquals("rotulos[SERVICO_OPERACAO] nao bate.",
		ConstantesVertice.SERVICO_OPERACAO, vertice
			.obterValorRotulo(Rotulo.SERVICO_OPERACAO));
    }

    @Test
    public void testeToString() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);

	assertEquals("toString() nao bate.",
		ConstantesVertice.SERVICO_OPERACAO, vertice.toString());
    }

    @Test
    public void testeEqualsSucesso() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);
	Vertice vertice2 = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));

	assertEquals("equals() nao bate.", vertice, vertice2);
    }

    @Test
    public void testeEqualsVerticeNulo() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(null));
    }

    @Test
    public void testeEqualsNaoVertice() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(new Object()));
    }

    @Test
    public void testeEqualsVerticesDiferentes() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);
	Vertice vertice2 = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));
	vertice2.adicionarRotulo(Rotulo.SERVICO_OPERACAO, String.valueOf(System
		.currentTimeMillis()));

	assertFalse("equals() nao bate.", vertice.equals(vertice2));
    }

    @Test
    public void testeHashCodeSucesso() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);
	Vertice vertice2 = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));

	assertEquals("hashCode() nao bate.", vertice.hashCode(), vertice2
		.hashCode());
    }

    @Test
    public void testeHashCodeVerticesDiferentes() {
	Vertice vertice = new Vertice(ConstantesVertice.ROTULOS);
	Vertice vertice2 = new Vertice(new HashMap<Rotulo, String>(
		ConstantesVertice.ROTULOS));
	vertice2.adicionarRotulo(Rotulo.SERVICO_OPERACAO, String.valueOf(System
		.currentTimeMillis()));

	assertFalse("hashCode() nao bate.", vertice.hashCode() == vertice2
		.hashCode());
    }
}