package br.ipt.servico.relevancia.bpel;

import java.util.ArrayList;
import java.util.List;

/**
 * Definicao de um <i>partnerLink</i> de um processo de negocio, proveniente de
 * um processo de negocio BPEL e tipicamente associado a <i>um web service</i>
 * (por meio de um arquivo XML com extensao <code>.wsdl</code>).
 * 
 * @author Rodrigo Mendes Leme
 */
public class PartnerLink implements Comparable<PartnerLink> {

    protected int id;

    protected ProcessoNegocio processoNegocio;

    protected String nome;

    String perfil;

    String tipo;

    protected DocumentoWSDL wsdl;

    protected String urlServico;

    protected List<String> operacoes;

    PartnerLink(ProcessoNegocio processoNegocio, String nome, String perfil,
	    String tipo) {
	this.processoNegocio = processoNegocio;
	this.nome = nome;
	this.perfil = perfil;
	this.tipo = tipo;
	this.operacoes = new ArrayList<String>();
    }

    public PartnerLink(int id, ProcessoNegocio processoNegocio, String nome,
	    DocumentoWSDL wsdl, String urlServico, List<String> operacoes) {
	this.id = id;
	this.processoNegocio = processoNegocio;
	this.nome = nome;
	this.wsdl = wsdl;
	this.urlServico = urlServico;
	this.operacoes = operacoes;
    }

    public int getId() {
	return this.id;
    }

    void setId(int id) {
	this.id = id;
    }

    public ProcessoNegocio getProcessoNegocio() {
	return this.processoNegocio;
    }

    public String getNome() {
	return this.nome;
    }

    String getPerfil() {
	return this.perfil;
    }

    String getTipo() {
	return this.tipo;
    }

    public DocumentoWSDL getWsdl() {
	return this.wsdl;
    }

    void setWsdl(DocumentoWSDL wsdl) {
	this.wsdl = wsdl;
    }

    public String getURLServico() {
	return this.urlServico;
    }

    void setURLServico(String urlServico) {
	this.urlServico = urlServico;
    }

    public List<String> getOperacoes() {
	return this.operacoes;
    }

    public String toString() {
	return this.getNome();
    }

    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof PartnerLink)) {
	    return false;
	}

	return this.id == ((PartnerLink) obj).id;
    }

    public int hashCode() {
	return this.id;
    }

    public int compareTo(PartnerLink partnerLink) {
	return new Integer(this.id).compareTo(partnerLink.getId());
    }

    String serializaOperacoes() {
	if (this.getOperacoes() == null || this.getOperacoes().isEmpty()) {
	    return null;
	}

	StringBuilder operacoes = new StringBuilder();
	for (int i = 0; i < this.getOperacoes().size(); i++) {
	    operacoes.append(this.getOperacoes().get(i));
	    if (i < this.getOperacoes().size() - 1) {
		operacoes.append("|");
	    }
	}

	return operacoes.toString();
    }

    boolean equals(String nome, String perfil, String tipo) {
	return ((this.getNome() == null && nome == null) || this.getNome()
		.equals(nome))
		&& ((this.getPerfil() == null && perfil == null) || this
			.getPerfil().equals(perfil))
		&& ((this.getTipo() == null && tipo == null) || this.getTipo()
			.equals(tipo));
    }
}