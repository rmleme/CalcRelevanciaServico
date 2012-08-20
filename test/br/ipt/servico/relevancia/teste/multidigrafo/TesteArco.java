package br.ipt.servico.relevancia.teste.multidigrafo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.Arco.Rotulo;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesArco;

/**
 * Testes unitarios da camada de multidigrafo (arcos).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteArco {

    @Test
    public void testeConstrutorSucesso() {
	Arco arco = new Arco(ConstantesArco.ROTULOS);

	assertEquals("rotulos[NUMERO_INVOCACOES] nao bate.",
		ConstantesArco.NUMERO_INVOCACOES, arco
			.obterValorRotulo(Rotulo.NUMERO_INVOCACOES));
	assertEquals("rotulos[ID_PROCESSO_NEGOCIO] nao bate.",
		ConstantesArco.ID_PROCESSO_NEGOCIO, arco
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    @Test
    public void testeConstrutorRotulosNulo() {
	try {
	    new Arco(null);
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
		    ConstantesArco.ROTULOS);
	    rotulos.put(null, "");
	    new Arco(rotulos);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "um arco deve conter apenas os rotulos "
			    + Rotulo.NUMERO_INVOCACOES + " e "
			    + Rotulo.ID_PROCESSO_NEGOCIO + ".", e.getMessage());
	}
    }

    @Test
    public void testeAdicionarRotuloSucesso() {
	Arco arco = new Arco(
		new HashMap<Rotulo, String>(ConstantesArco.ROTULOS));
	String idProcessoNegocioNovo = String.valueOf(Integer
		.valueOf(ConstantesArco.ID_PROCESSO_NEGOCIO) / 2);

	arco.adicionarRotulo(Rotulo.ID_PROCESSO_NEGOCIO, idProcessoNegocioNovo);

	assertEquals("rotulos[ID_PROCESSO_NEGOCIO] nao bate.",
		idProcessoNegocioNovo, arco
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    @Test
    public void testeAdicionarRotuloNulo() {
	Arco arco = new Arco(
		new HashMap<Rotulo, String>(ConstantesArco.ROTULOS));

	try {
	    arco.adicionarRotulo(null, "");
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "rotulo nao pode ser nulo.", e.getMessage());
	}
    }

    @Test
    public void testeAdicionarRotuloValorNulo() {
	Arco arco = new Arco(
		new HashMap<Rotulo, String>(ConstantesArco.ROTULOS));

	try {
	    arco.adicionarRotulo(Rotulo.ID_PROCESSO_NEGOCIO, null);
	    fail("IllegalArgumentException esperada mas nao capturada.");
	} catch (IllegalArgumentException e) {
	    assertEquals("Mensagem de erro nao bate.",
		    "rotulo nao pode ser nulo.", e.getMessage());
	}
    }

    @Test
    public void testeObterValorRotulo() {
	Arco arco = new Arco(ConstantesArco.ROTULOS);

	assertEquals("rotulos[ID_PROCESSO_NEGOCIO] nao bate.",
		ConstantesArco.ID_PROCESSO_NEGOCIO, arco
			.obterValorRotulo(Rotulo.ID_PROCESSO_NEGOCIO));
    }

    @Test
    public void testeToString() {
	Arco arco = new Arco(ConstantesArco.ROTULOS);

	assertEquals("toString() nao bate.", ConstantesArco.NUMERO_INVOCACOES,
		arco.toString());
    }
}