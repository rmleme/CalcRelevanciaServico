package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeInicio;
import br.ipt.servico.relevancia.multidigrafo.Vertice.Rotulo;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesVerticeInicio;

/**
 * Testes unitarios da camada de multidigrafo (vertices inicio).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteVerticeInicio {

    @Test
    public void testeConstrutorSucesso() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	assertEquals("rotulos[ID_PROCESSO_NEGOCIO] nao bate.",
		ConstantesVerticeInicio.ID_PROCESSO_NEGOCIO, vertice
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    @Test
    public void testeConstrutorRotulosNulo() {
	try {
	    new VerticeInicio(null);
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
		    ConstantesVerticeInicio.ROTULOS);
	    rotulos.put(null, "");
	    new VerticeInicio(rotulos);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um vertice inicio deve conter apenas o rotulo "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ", preenchido.", e
			    .getMessage());
	}
    }

    @Test
    public void testeAdicionarRotulo() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	try {
	    vertice.adicionarRotulo(Rotulo.ID_PROCESSO_NEGOCIO, "1");
	    fail("UnsupportedOperationException esperada mas nao capturada.");
	} catch (UnsupportedOperationException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um vertice inicio e imutavel.", e.getMessage());
	}
    }

    @Test
    public void testeToString() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	assertEquals("toString() nao bate.", "Vertice inicio PN "
		+ ConstantesVerticeInicio.ID_PROCESSO_NEGOCIO, vertice
		.toString());
    }

    @Test
    public void testeEqualsSucesso() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);
	VerticeInicio vertice2 = new VerticeInicio(new HashMap<Rotulo, String>(
		ConstantesVerticeInicio.ROTULOS));

	assertEquals("equals() nao bate.", vertice, vertice2);
    }

    @Test
    public void testeEqualsVerticeInicioNulo() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(null));
    }

    @Test
    public void testeEqualsNaoVerticeInicio() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(new Object()));
    }

    @Test
    public void testeEqualsVerticesInicioDiferentes() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	Map<Rotulo, String> rotulos = new HashMap<Rotulo, String>();
	rotulos.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO, String.valueOf(System
		.currentTimeMillis()));
	VerticeInicio vertice2 = new VerticeInicio(rotulos);

	assertFalse("equals() nao bate.", vertice.equals(vertice2));
    }

    @Test
    public void testeHashCodeSucesso() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);
	VerticeInicio vertice2 = new VerticeInicio(new HashMap<Rotulo, String>(
		ConstantesVerticeInicio.ROTULOS));

	assertEquals("hashCode() nao bate.", vertice.hashCode(), vertice2
		.hashCode());
    }

    @Test
    public void testeHashCodeVerticesInicioDiferentes() {
	VerticeInicio vertice = new VerticeInicio(
		ConstantesVerticeInicio.ROTULOS);

	Map<Rotulo, String> rotulos = new HashMap<Rotulo, String>();
	rotulos.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO, String.valueOf(System
		.currentTimeMillis()));
	VerticeInicio vertice2 = new VerticeInicio(rotulos);

	assertFalse("hashCode() nao bate.", vertice.hashCode() == vertice2
		.hashCode());
    }
}