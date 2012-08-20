package br.ipt.servico.relevancia.bpel;

/**
 * Documento XML descrevendo um web service.
 * 
 * @author Rodrigo Mendes Leme
 */
public class DocumentoWSDL {

    protected String conteudo;

    public DocumentoWSDL(String conteudo) {
	this.conteudo = conteudo;
    }

    public String getConteudo() {
	return conteudo;
    }

    public String toString() {
	return this.getConteudo();
    }
}