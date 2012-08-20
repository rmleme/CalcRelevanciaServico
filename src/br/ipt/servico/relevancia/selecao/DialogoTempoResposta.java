package br.ipt.servico.relevancia.selecao;

import br.ipt.servico.relevancia.multidigrafo.Vertice;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 * Dialogo para edicao do tempo de resposta esperado de um servico.
 *
 * @author Rodrigo Mendes Leme
 */
public class DialogoTempoResposta extends javax.swing.JDialog {

    private Logger log = Logger.getLogger(DialogoTempoResposta.class);
    private Vertice vertice;

    public DialogoTempoResposta(java.awt.Frame parent, Vertice vertice) {
        super(parent, true);
        this.vertice = vertice;
        initComponents();
        this.txtTempoResposta.setText(vertice.obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTempoResposta = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        txtTempoResposta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(this.vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO));
        setModal(true);

        lblTempoResposta.setText("Tempo de resposta esperado (ms):");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        txtTempoResposta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoRespostaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTempoResposta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(txtTempoResposta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTempoResposta)
                    .addComponent(txtTempoResposta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        int tempoResposta = Integer.MIN_VALUE;
        try {
            tempoResposta = Integer.parseInt(this.txtTempoResposta.getText());
        } catch (NumberFormatException e) {
            this.log.warn("Erro: tempo de resposta esperado invalido (" + this.txtTempoResposta.getText() + ").", e);
        }
        if (tempoResposta < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tempo de resposta esperado deve ser um n\u00famero inteiro >= 0.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            this.txtTempoResposta.setText(vertice.obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO));
            this.txtTempoResposta.requestFocusInWindow();
        } else {
            this.vertice.adicionarRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO, this.txtTempoResposta.getText());
            this.dispose();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtTempoRespostaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoRespostaActionPerformed
        this.btnOKActionPerformed(evt);
    }//GEN-LAST:event_txtTempoRespostaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel lblTempoResposta;
    private javax.swing.JTextField txtTempoResposta;
    // End of variables declaration//GEN-END:variables
}