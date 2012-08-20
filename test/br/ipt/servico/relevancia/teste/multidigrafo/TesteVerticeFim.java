package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeFim;
import br.ipt.servico.relevancia.multidigrafo.Vertice.Rotulo;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesVerticeFim;

/**
 * Testes unitarios da camada de multidigrafo (vertices fim).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteVerticeFim {

    @Test
    public void testeConstrutorSucesso() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	assertEquals("rotulos[ID_PROCESSO_NEGOCIO] nao bate.",
		ConstantesVerticeFim.ID_PROCESSO_NEGOCIO, vertice
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    @Test
    public void testeConstrutorRotulosNulo() {
	try {
	    new VerticeFim(null);
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
		    ConstantesVerticeFim.ROTULOS);
	    rotulos.put(null, "");
	    new VerticeFim(rotulos);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um vertice fim deve conter apenas o rotulo "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ", preenchido.", e
			    .getMessage());
	}
    }

    @Test
    public void testeAdicionarRotulo() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	try {
	    vertice.adicionarRotulo(Rotulo.ID_PROCESSO_NEGOCIO, "1");
	    fail("UnsupportedOperationException esperada mas nao capturada.");
	} catch (UnsupportedOperationException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um vertice fim e imutavel.", e.getMessage());
	}
    }

    @Test
    public void testeToString() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	assertEquals("toString() nao bate.", "Vertice fim PN "
		+ ConstantesVerticeFim.ID_PROCESSO_NEGOCIO, vertice.toString());
    }

    @Test
    public void testeEqualsSucesso() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);
	VerticeFim vertice2 = new VerticeFim(new HashMap<Rotulo, String>(
		ConstantesVerticeFim.ROTULOS));

	assertEquals("equals() nao bate.", vertice, vertice2);
    }

    @Test
    public void testeEqualsVerticeFimNulo() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(null));
    }

    @Test
    public void testeEqualsNaoVerticeFim() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	assertFalse("equals() nao bate.", vertice.equals(new Object()));
    }

    @Test
    public void testeEqualsVerticesFimDiferentes() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	Map<Rotulo, String> rotulos = new HashMap<Rotulo, String>();
	rotulos.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO, String.valueOf(System
		.currentTimeMillis()));
	VerticeFim vertice2 = new VerticeFim(rotulos);

	assertFalse("equals() nao bate.", vertice.equals(vertice2));
    }

    @Test
    public void testeHashCodeSucesso() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);
	VerticeFim vertice2 = new VerticeFim(new HashMap<Rotulo, String>(
		ConstantesVerticeFim.ROTULOS));

	assertEquals("hashCode() nao bate.", vertice.hashCode(), vertice2
		.hashCode());
    }

    @Test
    public void testeHashCodeVerticesFimDiferentes() {
	VerticeFim vertice = new VerticeFim(ConstantesVerticeFim.ROTULOS);

	Map<Rotulo, String> rotulos = new HashMap<Rotulo, String>();
	rotulos.put(Vertice.Rotulo.ID_PROCESSO_NEGOCIO, String.valueOf(System
		.currentTimeMillis()));
	VerticeFim vertice2 = new VerticeFim(rotulos);

	assertFalse("hashCode() nao bate.", vertice.hashCode() == vertice2
		.hashCode());
    }
}