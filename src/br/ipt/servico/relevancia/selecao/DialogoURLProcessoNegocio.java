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

/**
 * Dialogo para inclusao da URL de um processo de negocio.
 * 
 * @author Rodrigo Mendes Leme
 */
public class DialogoURLProcessoNegocio extends JDialog {

    private static final long serialVersionUID = 6198671811220023045L;

    private String urlProcessoNegocio;

    private JButton btnOK;
    private JLabel lblURLProcessoNegocio;
    private JTextField txtURLProcessoNegocio;

    public DialogoURLProcessoNegocio(Frame parent, String urlProcessoNegocio) {
	super(parent, true);
	initComponents();
	this.urlProcessoNegocio = urlProcessoNegocio;
	this.txtURLProcessoNegocio.setText(urlProcessoNegocio == null ? ""
		: urlProcessoNegocio);
    }

    public String getURLProcessoNegocio() {
	return this.urlProcessoNegocio;
    }

    private void initComponents() {
	lblURLProcessoNegocio = new JLabel();
	btnOK = new JButton();
	txtURLProcessoNegocio = new JTextField();

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setTitle("URL do Processo de Negócio");
	setModal(true);

	lblURLProcessoNegocio.setText("URL:");

	btnOK.setText("OK");
	btnOK.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnOKActionPerformed(evt);
	    }
	});

	txtURLProcessoNegocio.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		txtURLProcessoNegocioActionPerformed(evt);
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
					.addGroup(
						layout
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								layout
									.createSequentialGroup()
									.addComponent(
										lblURLProcessoNegocio)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										txtURLProcessoNegocio))
							.addGroup(
								Alignment.TRAILING,
								layout
									.createSequentialGroup()
									.addGap(
										0,
										350,
										Short.MAX_VALUE)
									.addComponent(
										btnOK,
										GroupLayout.PREFERRED_SIZE,
										59,
										GroupLayout.PREFERRED_SIZE)))
					.addContainerGap()));
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
								lblURLProcessoNegocio)
							.addComponent(
								txtURLProcessoNegocio,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(
						ComponentPlacement.RELATED,
						GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE).addComponent(
						btnOK).addContainerGap()));

	pack();
    }

    private void btnOKActionPerformed(ActionEvent evt) {
	if (this.txtURLProcessoNegocio.getText() == null
		|| this.txtURLProcessoNegocio.getText().trim().equals("")) {
	    JOptionPane
		    .showMessageDialog(
			    this,
			    "URL do processo de neg\u00F3cio \u00E9 um campo obrigat\u00F3rio.",
			    "Erro", JOptionPane.ERROR_MESSAGE);
	    this.txtURLProcessoNegocio.requestFocusInWindow();
	} else {
	    this.urlProcessoNegocio = this.txtURLProcessoNegocio.getText();
	    this.dispose();
	}
    }

    private void txtURLProcessoNegocioActionPerformed(ActionEvent evt) {
	this.btnOKActionPerformed(evt);
    }
}