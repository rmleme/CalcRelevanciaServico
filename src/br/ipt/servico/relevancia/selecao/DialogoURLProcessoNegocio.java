package br.ipt.servico.relevancia.selecao;

import javax.swing.JOptionPane;

/**
 * Dialogo para inclusao da URL de um processo de negocio.
 *
 * @author Rodrigo Mendes Leme
 */
public class DialogoURLProcessoNegocio extends javax.swing.JDialog {

    private String urlProcessoNegocio;

    public DialogoURLProcessoNegocio(java.awt.Frame parent, String urlProcessoNegocio) {
        super(parent, true);
        initComponents();
        this.urlProcessoNegocio = urlProcessoNegocio;
        this.txtURLProcessoNegocio.setText(urlProcessoNegocio == null ? "" : urlProcessoNegocio);
    }

    public String getURLProcessoNegocio() {
        return this.urlProcessoNegocio;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblURLProcessoNegocio = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        txtURLProcessoNegocio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("URL do Processo de Negócio");
        setModal(true);

        lblURLProcessoNegocio.setText("URL:");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        txtURLProcessoNegocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtURLProcessoNegocioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblURLProcessoNegocio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtURLProcessoNegocio))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 350, Short.MAX_VALUE)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblURLProcessoNegocio)
                    .addComponent(txtURLProcessoNegocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (this.txtURLProcessoNegocio.getText() == null || this.txtURLProcessoNegocio.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(
                    this,
                    "URL do processo de neg\u00F3cio \u00E9 um campo obrigat\u00F3rio.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            this.txtURLProcessoNegocio.requestFocusInWindow();
        } else {
            this.urlProcessoNegocio = this.txtURLProcessoNegocio.getText();
            this.dispose();
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtURLProcessoNegocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtURLProcessoNegocioActionPerformed
        this.btnOKActionPerformed(evt);
    }//GEN-LAST:event_txtURLProcessoNegocioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel lblURLProcessoNegocio;
    private javax.swing.JTextField txtURLProcessoNegocio;
    // End of variables declaration//GEN-END:variables
}