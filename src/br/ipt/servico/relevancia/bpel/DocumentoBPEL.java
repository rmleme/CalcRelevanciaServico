package br.ipt.servico.relevancia.bpel;

/**
 * Documento XML descrevendo um processo de negocio.
 * 
 * @author Rodrigo Mendes Leme
 */
public class DocumentoBPEL {

    protected String conteudo;

    public DocumentoBPEL(String conteudo) {
	this.conteudo = conteudo;
    }

    public String getConteudo() {
	return this.conteudo;
    }

    public String toString() {
	return this.getConteudo();
    }
}