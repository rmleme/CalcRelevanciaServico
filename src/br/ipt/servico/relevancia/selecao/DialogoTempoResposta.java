package br.ipt.servico.relevancia.selecao;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.multidigrafo.Vertice;

/**
 * Dialogo para edicao do tempo de resposta esperado de um servico.
 * 
 * @author Rodrigo Mendes Leme
 */
public class DialogoTempoResposta extends JDialog {

    private static final long serialVersionUID = -1057879653469270864L;

    private Logger log = Logger.getLogger(DialogoTempoResposta.class);

    private Vertice vertice;

    private JButton btnOK;
    private JLabel lblTempoResposta;
    private JTextField txtTempoResposta;

    public DialogoTempoResposta(Frame parent, Vertice vertice) {
	super(parent, true);
	this.vertice = vertice;
	initComponents();
	this.txtTempoResposta.setText(vertice
		.obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO));
    }

    private void initComponents() {
	lblTempoResposta = new JLabel();
	btnOK = new JButton();
	txtTempoResposta = new JTextField();

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setTitle(this.vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO));
	setModal(true);

	lblTempoResposta.setText("Tempo de resposta esperado (ms):");

	btnOK.setText("OK");
	btnOK.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnOKActionPerformed(evt);
	    }
	});

	txtTempoResposta.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		txtTempoRespostaActionPerformed(evt);
	    }
	});

	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout
		.setHorizontalGroup(layout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				layout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTempoResposta)
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						layout
							.createParallelGroup(
								Alignment.LEADING,
								false)
							.addComponent(
								btnOK,
								GroupLayout.DEFAULT_SIZE,
								59,
								Short.MAX_VALUE)
							.addComponent(
								txtTempoResposta))
					.addContainerGap(
						GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)));
	layout
		.setVerticalGroup(layout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				layout
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						layout
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								lblTempoResposta)
							.addComponent(
								txtTempoResposta,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(btnOK).addContainerGap(
						GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)));

	pack();
    }

    private void btnOKActionPerformed(ActionEvent evt) {
	int tempoResposta = Integer.MIN_VALUE;
	try {
	    tempoResposta = Integer.parseInt(this.txtTempoResposta.getText());
	} catch (NumberFormatException e) {
	    this.log.warn("Erro: tempo de resposta esperado invalido ("
		    + this.txtTempoResposta.getText() + ").", e);
	}
	if (tempoResposta < 0) {
	    JOptionPane
		    .showMessageDialog(
			    this,
			    "Tempo de resposta esperado deve ser um n\u00famero inteiro >= 0.",
			    "Erro", JOptionPane.ERROR_MESSAGE);
	    this.txtTempoResposta.setText(vertice
		    .obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO));
	    this.txtTempoResposta.requestFocusInWindow();
	} else {
	    this.vertice.adicionarRotulo(
		    Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO,
		    this.txtTempoResposta.getText());
	    this.dispose();
	}
    }

    private void txtTempoRespostaActionPerformed(ActionEvent evt) {
	this.btnOKActionPerformed(evt);
    }
}