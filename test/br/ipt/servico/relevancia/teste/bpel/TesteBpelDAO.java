package br.ipt.servico.relevancia.teste.bpel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ipt.servico.relevancia.bpel.BpelDAO;
import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.teste.Constantes.ConstantesProcessoNegocio;

/**
 * Testes unitarios da camada BPEL (funcionalidade de persistencia de processos
 * de negocio no banco de dados).
 * 
 * @author Rodrigo Mendes Leme
 */
public class TesteBpelDAO {

    private static ProcessoNegocio pn;

    @BeforeClass
    public static void setUpClass() throws Exception {
	pn = ConstantesProcessoNegocio.PROCESSO_NEGOCIO;
    }

    @Test
    public void testeCriarPNSucesso() throws Exception {
	BpelDAO dao = new BpelDAO();
	dao.criarProcessoNegocio(pn);

	Util.assertProcessoNegocioCriado(pn);
    }

    @Test
    public void testeObterPNSucesso() throws Exception {
	BpelDAO dao = new BpelDAO();
	ProcessoNegocio pn2 = dao.obterProcessoNegocio(pn.getId());

	assertEquals("id nao bate.", pn.getId(), pn2.getId());
	Util.assertProcessoNegocioEquals(pn, pn2);
	assertEquals("parterLinks nao bate.", pn.getPartnerLinks().size(), pn2
		.getPartnerLinks().size());
	assertEquals("id nao bate.", pn.getPartnerLinks().get(0).getId(), pn2
		.getPartnerLinks().get(0).getId());
	assertEquals("processoNegocio.id nao bate.", pn.getPartnerLinks()
		.get(0).getProcessoNegocio().getId(), pn2.getPartnerLinks()
		.get(0).getProcessoNegocio().getId());
	Util.assertPartnerLinkEquals(pn.getPartnerLinks().get(0), pn2
		.getPartnerLinks().get(0));
    }

    @Test
    public void testeObterPNRegistroNaoExiste() throws Exception {
	BpelDAO dao = new BpelDAO();
	// Numero negativo para garantir que o id nao existe no banco de dados
	ProcessoNegocio pn2 = dao
		.obterProcessoNegocio(-ConstantesProcessoNegocio.ID);

	assertNull(
		"BpelDAO.obterProcessoNegocio() retornou um processo de negocio.",
		pn2);
    }

    @Test
    public void testeExistePNSucesso() throws Exception {
	BpelDAO dao = new BpelDAO();
	boolean existeProcessoNegocio = dao.existeProcessoNegocio(pn.getNome());

	assertTrue("BpelDAO.existeProcessoNegocio() retornou falso.",
		existeProcessoNegocio);
    }

    @Test
    public void testeExistePNRegistroNaoExiste() throws Exception {
	BpelDAO dao = new BpelDAO();
	// System.currentTimeMillis() para garantir que o nome do processo de
	// negocio nao existe no banco de dados
	boolean existeProcessoNegocio = dao.existeProcessoNegocio(String
		.valueOf(System.currentTimeMillis()));

	assertFalse("BpelDAO.existeProcessoNegocio() retornou verdadeiro.",
		existeProcessoNegocio);
    }

    @Test
    public void testeObterPNs() throws Exception {
	BpelDAO dao = new BpelDAO();
	List<ProcessoNegocio> processosNegocio = dao.obterProcessosNegocio();

	assertTrue("BpelDAO.obterProcessosNegocio() retornou uma lista vazia.",
		processosNegocio.size() > 0);
	for (ProcessoNegocio pn2 : processosNegocio) {
	    if (pn2.getId() == pn.getId()) {
		Util.assertProcessoNegocioEquals(pn, pn2);
		assertEquals("parterLinks nao bate.", pn.getPartnerLinks()
			.size(), pn2.getPartnerLinks().size());
		assertEquals("id nao bate.", pn.getPartnerLinks().get(0)
			.getId(), pn2.getPartnerLinks().get(0).getId());
		assertEquals("processoNegocio.id nao bate.", pn
			.getPartnerLinks().get(0).getProcessoNegocio().getId(),
			pn2.getPartnerLinks().get(0).getProcessoNegocio()
				.getId());
		Util.assertPartnerLinkEquals(pn.getPartnerLinks().get(0), pn2
			.getPartnerLinks().get(0));
	    }
	}
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
	Util.excluirProcessoNegocio(pn.getId());
    }
}