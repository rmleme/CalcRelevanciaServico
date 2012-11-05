package br.ipt.servico.relevancia.selecao;

/**
 * Relevancia (e componentes) de um servico.
 * 
 * @author Rodrigo Mendes Leme
 */
public class Relevancia {

    protected String servicoOperacao;

    protected double relevanciaEstatica;

    protected double relevanciaDinamica;

    protected double relevanciaDeDesempenho;

    protected double relevancia;

    public String getServicoOperacao() {
	return this.servicoOperacao;
    }

    public void setServicoOperacao(String servicoOperacao) {
	this.servicoOperacao = servicoOperacao;
    }

    public double getRelevanciaEstatica() {
	return this.relevanciaEstatica;
    }

    public void setRelevanciaEstatica(double relevanciaEstatica) {
	this.relevanciaEstatica = relevanciaEstatica;
    }

    public double getRelevanciaDinamica() {
	return this.relevanciaDinamica;
    }

    public void setRelevanciaDinamica(double relevanciaDinamica) {
	this.relevanciaDinamica = relevanciaDinamica;
    }

    public double getRelevanciaDeDesempenho() {
	return this.relevanciaDeDesempenho;
    }

    public void setRelevanciaDeDesempenho(double relevanciaDeDesempenho) {
	this.relevanciaDeDesempenho = relevanciaDeDesempenho;
    }

    public double getRelevancia() {
	return this.relevancia;
    }

    public void setRelevancia(double relevancia) {
	this.relevancia = relevancia;
    }

    public boolean equals(Object obj) {
	if (obj == null || !(obj instanceof Relevancia)) {
	    return false;
	}

	Relevancia r = (Relevancia) obj;
	if (this.servicoOperacao == null && r.servicoOperacao == null) {
	    return true;
	}
	if (this.servicoOperacao == null && r.servicoOperacao != null) {
	    return false;
	}
	if (this.servicoOperacao != null && r.servicoOperacao == null) {
	    return false;
	}
	return this.servicoOperacao.equals(r.servicoOperacao);
    }

    public int hashCode() {
	return this.servicoOperacao == null ? super.hashCode()
		: this.servicoOperacao.hashCode();
    }
}