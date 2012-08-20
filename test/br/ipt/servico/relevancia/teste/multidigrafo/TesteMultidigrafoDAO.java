package br.ipt.servico.relevancia.teste.multidigrafo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.MultidigrafoDAO;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesMultidigrafo;
import edu.uci.ics.jung.graph.Graph;

/**
 * Testes unitarios da camada de multidigrafo (funcionalidade de persistencia de
 * multidigrafos no banco de dados).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteMultidigrafoDAO {

    private static Graph<Vertice, Arco> multidigrafo;

    @BeforeClass
    public static void setUpClass() throws Exception {
	multidigrafo = Util.salvarMultidigrafo();
    }

    @Test
    public void testeCriarMultidigrafoSucesso() throws Exception {
	MultidigrafoDAO dao = new MultidigrafoDAO();
	dao.criarMultidigrafo(ConstantesMultidigrafo.MULTIDIGRAFO);

	Util.assertMultidigrafoCriado(ConstantesMultidigrafo.MULTIDIGRAFO);
    }

    @Test
    public void testeObterMultidigrafoSucesso() throws Exception {
	MultidigrafoDAO dao = new MultidigrafoDAO();
	Graph<Vertice, Arco> multidigrafo2 = dao.obterMultidigrafo();

	Util.assertMultidigrafoEquals(ConstantesMultidigrafo.MULTIDIGRAFO,
		multidigrafo2);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
	Util.restaurarMultidigrafo(multidigrafo);
    }
}