package br.ipt.servico.relevancia.selecao;

import br.ipt.servico.relevancia.bpel.BpelDAO;
import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.excecao.BPAlreadyExistsException;
import br.ipt.servico.relevancia.metricas.ColetorMetricasDesempenho;
import br.ipt.servico.relevancia.multidigrafo.*;
import br.ipt.servico.relevancia.transformacao.ParserBPEL;
import br.ipt.servico.relevancia.util.Arquivo;
import edu.uci.ics.jung.graph.Graph;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;

/**
 * Tela principal do sistema, para interacao com usuario e visualizacao dos
 * servicos e do multidigrafo dos processos de negocio.
 *
 * @author Rodrigo Mendes Leme
 */
public class TelaPrincipal extends JFrame {

    private static final String LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static final int NUMERO_MAXIMO_PROCESSOS_NEGOCIO = 10;
    private static Logger log = Logger.getLogger(TelaPrincipal.class);
    private Graph<Vertice, Arco> multidigrafo;

    public TelaPrincipal() {
        this.initComponents();
        this.carregarProcessosNegocio();
        this.carregarMultidigrafo();
        if (this.multidigrafo != null) {
            this.lstPN.setCellRenderer(new SelecaoPNRenderer(this.multidigrafo));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flcImportarPN = new javax.swing.JFileChooser();
        flcExportarListaServicos = new javax.swing.JFileChooser();
        btnImportarPN = new javax.swing.JButton();
        lblPN = new javax.swing.JLabel();
        scpPN = new javax.swing.JScrollPane();
        lstPN = new javax.swing.JList();
        btnGerarMultidigrafo = new javax.swing.JButton();
        btnColetarMetricas = new javax.swing.JButton();
        scpMultidigrafo = new javax.swing.JScrollPane();
        scpServicos = new javax.swing.JScrollPane();
        tblServicos = new javax.swing.JTable();
        btnCalcularRelevancia = new javax.swing.JButton();
        btnExportarListaServicos = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnSalvarMultidigrafo = new javax.swing.JButton();

        flcImportarPN.setAcceptAllFileFilterUsed(false);
        flcImportarPN.setCurrentDirectory(new java.io.File("C:\\"));
            flcImportarPN.setDialogTitle("Importar Processo de Negócio");
            flcImportarPN.setFileFilter(new BPELFileFilter());

            flcExportarListaServicos.setAcceptAllFileFilterUsed(false);
            flcExportarListaServicos.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
            flcExportarListaServicos.setCurrentDirectory(new java.io.File("C:\\"));
                flcExportarListaServicos.setDialogTitle("Exportar Lista de Serviços");
                flcExportarListaServicos.setFileFilter(new CSVFileFilter());

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("SOA - IPT - Ferramenta para Cálculo do Índice de Relevância dos Serviços");
                setName("frmTelaPrincipal");

                btnImportarPN.setText("Importar PN");
                btnImportarPN.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnImportarPNActionPerformed(evt);
                    }
                });

                lblPN.setText("Processos de negócio");

                lstPN.setModel(new DefaultListModel());
                scpPN.setViewportView(lstPN);

                btnGerarMultidigrafo.setText("Gerar multidigrafo");
                btnGerarMultidigrafo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGerarMultidigrafoActionPerformed(evt);
                    }
                });

                btnColetarMetricas.setText("Coletar métricas");
                btnColetarMetricas.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnColetarMetricasActionPerformed(evt);
                    }
                });

                tblServicos.setModel(new ListaServicosTableModel());
                tblServicos.setEnabled(false);
                tblServicos.getTableHeader().setReorderingAllowed(false);
                scpServicos.setViewportView(tblServicos);

                btnCalcularRelevancia.setText("Calcular relevância");
                btnCalcularRelevancia.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnCalcularRelevanciaActionPerformed(evt);
                    }
                });

                btnExportarListaServicos.setText("Exportar lista de serviços");
                btnExportarListaServicos.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnExportarListaServicosActionPerformed(evt);
                    }
                });

                btnSair.setText("Sair");
                btnSair.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnSairActionPerformed(evt);
                    }
                });

                btnSalvarMultidigrafo.setText("Salvar multidigrafo");
                btnSalvarMultidigrafo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnSalvarMultidigrafoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnImportarPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scpPN, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPN, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(btnSair))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scpMultidigrafo)
                            .addComponent(scpServicos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCalcularRelevancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExportarListaServicos)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnGerarMultidigrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSalvarMultidigrafo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnColetarMetricas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGerarMultidigrafo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnImportarPN)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblPN)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scpPN, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnColetarMetricas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalvarMultidigrafo)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(scpMultidigrafo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCalcularRelevancia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExportarListaServicos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSair))
                            .addComponent(scpServicos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                );

                lblPN.getAccessibleContext().setAccessibleName("Processos de neg\\u00F3cio");
                btnColetarMetricas.getAccessibleContext().setAccessibleName("Coletar m\\u00E9tricas");
                btnCalcularRelevancia.getAccessibleContext().setAccessibleName("Calcular relev\\u00E2ncia");
                btnExportarListaServicos.getAccessibleContext().setAccessibleName("Exportar lista de servi\\u00E7os");

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnImportarPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarPNActionPerformed
        if (this.lstPN.getModel().getSize() == NUMERO_MAXIMO_PROCESSOS_NEGOCIO) {
            JOptionPane.showMessageDialog(null,
                    "N\u00E3o \u00E9 poss\u00EDvel importar mais processos de neg\u00F3cio. Limite m\u00E1ximo atingido (" + NUMERO_MAXIMO_PROCESSOS_NEGOCIO + ").", "Importar Processo de Neg\u00F3cio",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (evt.getSource() == this.btnImportarPN) {
            int retorno = this.flcImportarPN.showOpenDialog(this);
            while (retorno == JFileChooser.APPROVE_OPTION
                    && !this.flcImportarPN.getSelectedFile().getName().endsWith(".bpel")) {
                JOptionPane.showMessageDialog(null,
                        "O arquivo " + this.flcImportarPN.getSelectedFile()
                        + " n\u00E3o \u00E9 um processo de neg\u00F3cio BPEL.", "Importar Processo de Neg\u00F3cio",
                        JOptionPane.ERROR_MESSAGE);
                retorno = this.flcImportarPN.showOpenDialog(this);
            }

            if (retorno == JFileChooser.APPROVE_OPTION) {
                try {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    DialogoURLProcessoNegocio dlgURLProcessonegocio = new DialogoURLProcessoNegocio(
                            this, null);
                    dlgURLProcessonegocio.setLocationRelativeTo(null);
                    dlgURLProcessonegocio.setVisible(true);

                    ProcessoNegocio processoNegocio = ProcessoNegocio.importar(this.flcImportarPN.getSelectedFile(), dlgURLProcessonegocio.getURLProcessoNegocio());

                    DefaultListModel modelo = (DefaultListModel) this.lstPN.getModel();
                    int indice = Arrays.binarySearch(modelo.toArray(), processoNegocio);
                    modelo.add(indice >= 0 ? indice : -indice - 1, processoNegocio);
                    this.lstPN.setModel(modelo);

                    JOptionPane.showMessageDialog(null,
                            "Arquivo " + this.flcImportarPN.getSelectedFile()
                            + " importado com sucesso.", "Importar Processo de Neg\u00F3cio",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (BPAlreadyExistsException e) {
                    log.warn("Erro: o arquivo " + this.flcImportarPN.getSelectedFile() + " nao pode ser importado.", e);
                    JOptionPane.showMessageDialog(null,
                            "N\u00E3o foi poss\u00EDvel importar o arquivo " + this.flcImportarPN.getSelectedFile()
                            + ". Processo de neg\u00F3cio j\u00E1 existe no sistema.", "Importar Processo de Neg\u00F3cio",
                            JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
                    log.error("Erro: o arquivo " + this.flcImportarPN.getSelectedFile() + " nao pode ser importado.", e);
                    JOptionPane.showMessageDialog(null,
                            "O arquivo " + this.flcImportarPN.getSelectedFile()
                            + " n\u00E3o p\u00F4de ser importado.", "Importar Processo de Neg\u00F3cio",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_btnImportarPNActionPerformed

    private void btnExportarListaServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarListaServicosActionPerformed
        if (evt.getSource() == this.btnExportarListaServicos) {
            int retorno = this.flcExportarListaServicos.showSaveDialog(this);
            while (retorno == JFileChooser.APPROVE_OPTION
                    && (!this.flcExportarListaServicos.getSelectedFile().getName().endsWith(".csv") && this.flcExportarListaServicos.getSelectedFile().getName().contains("."))) {
                JOptionPane.showMessageDialog(null,
                        "O arquivo " + this.flcExportarListaServicos.getSelectedFile()
                        + " n\u00E3o tem a extens\u00E3o \".csv\".", "Exportar Lista de Servi\u00E7os",
                        JOptionPane.ERROR_MESSAGE);
                retorno = this.flcExportarListaServicos.showSaveDialog(this);
            }

            if (retorno == JFileChooser.APPROVE_OPTION) {
                try {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                    String nomeArquivo = this.flcExportarListaServicos.getSelectedFile().getCanonicalPath();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nomeArquivo.endsWith(".csv") ? nomeArquivo : nomeArquivo + ".csv")));
                    ListaServicosTableModel modelo = (ListaServicosTableModel) this.tblServicos.getModel();
                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        for (int j = 0; j < modelo.getColumnCount(); j++) {
                            bw.append(modelo.getStringValueAt(i, j));
                            if (j < modelo.getColumnCount() - 1) {
                                bw.append(";");
                            } else {
                                if (i < modelo.getRowCount() - 1) {
                                    bw.append(System.getProperty("line.separator"));
                                }
                            }
                        }
                    }
                    bw.close();

                    JOptionPane.showMessageDialog(null,
                            "Lista de servi\u00E7os exportada com sucesso.", "Exportar Lista de Servi\u00E7os",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    log.error("Erro: a lista de servicos nao pode ser exportada.", e);
                    JOptionPane.showMessageDialog(null,
                            "A lista de servi\u00E7os n\u00E3o p\u00F4de ser exportada.", "Exportar Lista de Servi\u00E7os",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_btnExportarListaServicosActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        if (evt.getSource() == this.btnSair) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnGerarMultidigrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarMultidigrafoActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (this.lstPN.getSelectedValues().length > 0) {
            List<ProcessoNegocio> processosNegocio = new ArrayList<ProcessoNegocio>();
            for (Object obj : this.lstPN.getSelectedValues()) {
                processosNegocio.add((ProcessoNegocio) obj);
            }
            ParserBPEL parser = new ParserBPEL();
            this.multidigrafo = parser.parse(processosNegocio);
            this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo.obterVisualizador(this.multidigrafo));
        }
        this.lstPN.setCellRenderer(new SelecaoPNRenderer(this.multidigrafo));
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnGerarMultidigrafoActionPerformed

    private void btnColetarMetricasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColetarMetricasActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (this.multidigrafo != null) {
            ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

            // Tempo medio de execucao (vertice)
            for (Vertice vertice : this.multidigrafo.getVertices()) {
                if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
                    Set<Integer> idsProcessosNegocio = new HashSet<Integer>();
                    for (Arco arco : this.multidigrafo.getInEdges(vertice)) {
                        idsProcessosNegocio.add(new Integer(arco.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO)));
                    }
                    for (Arco arco : this.multidigrafo.getOutEdges(vertice)) {
                        idsProcessosNegocio.add(new Integer(arco.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO)));
                    }

                    double somaTemposMedios = 0;
                    int n = 0;
                    for (int idProcessoNegocio : idsProcessosNegocio) {
                        ProcessoNegocio processoNegocio = this.obterProcessoNegocio(this.lstPN, idProcessoNegocio);
                        if (processoNegocio != null) {
                            String[] servicoOperacao = vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO).split("\\.");
                            Map<Integer, Double> temposMedios = coletor.coletarTempoMedioExecucao(processoNegocio.getNome(), servicoOperacao[1]);
                            for (Double tempoMedio : temposMedios.values()) {
                                n++;
                                somaTemposMedios += tempoMedio;
                            }
                        }
                    }

                    if (n != 0) {
                        double tempoMedio = somaTemposMedios / n;
                        log.debug("Vertice \"" + vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO) + "\": tempo medio de execucao = " + tempoMedio);
                        vertice.adicionarRotulo(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, String.valueOf(tempoMedio));
                    }
                }
            }

            // Numero de invocacoes (arco)
            for (Vertice vertice : this.multidigrafo.getVertices()) {
                if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
                    Map<Integer, Arco> arcos = new HashMap<Integer, Arco>();
                    for (Arco arco : this.multidigrafo.getInEdges(vertice)) {
                        Integer idProcessoNegocio = new Integer(arco.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO));
                        if (!arcos.containsKey(idProcessoNegocio)) {
                            arcos.put(idProcessoNegocio, arco);
                        }
                    }

                    for (Integer idProcessoNegocio : arcos.keySet()) {
                        ProcessoNegocio processoNegocio = this.obterProcessoNegocio(this.lstPN, idProcessoNegocio);
                        if (processoNegocio != null) {
                            String[] servicoOperacao = vertice.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO).split("\\.");
                            Map<Integer, Integer> numerosInvocacoes = coletor.coletarNumeroInvocacoes(processoNegocio.getNome(), servicoOperacao[1]);

                            if (!numerosInvocacoes.isEmpty()) {
                                double somaNumerosInvocacoes = 0;
                                for (Integer numeroInvocacoes : numerosInvocacoes.values()) {
                                    somaNumerosInvocacoes += numeroInvocacoes;
                                }

                                Arco arco = arcos.get(idProcessoNegocio);
                                log.debug("Arco \"" + this.multidigrafo.getSource(arco).obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO) + "\" --> \"" + this.multidigrafo.getDest(arco).obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO) + "\" (processo de negocio \"" + processoNegocio.getNome() + "\"): numero de invocacoes = " + somaNumerosInvocacoes);
                                arco.adicionarRotulo(Arco.Rotulo.NUMERO_INVOCACOES, String.valueOf(somaNumerosInvocacoes));
                            }
                        }
                    }
                }
            }

            this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo.obterVisualizador(this.multidigrafo));

            JOptionPane.showMessageDialog(null,
                    "M\u00E9tricas de desempenho coletadas com sucesso.", "Coletar m\u00E9tricas de desempenho",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnColetarMetricasActionPerformed

    private void btnCalcularRelevanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularRelevanciaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ListaServicosTableModel modelo = (ListaServicosTableModel) this.tblServicos.getModel();
        CalculadoraRelevanciaServicos calculadora = new CalculadoraRelevanciaServicos();
        modelo.adicionarItens(calculadora.calcular(this.multidigrafo));
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnCalcularRelevanciaActionPerformed

    private void btnSalvarMultidigrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMultidigrafoActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (this.multidigrafo != null) {
            MultidigrafoDAO dao = new MultidigrafoDAO();
            try {
                dao.criarMultidigrafo(this.multidigrafo);
            } catch (SQLException e) {
                log.error("Erro: nao pode salvar o multidigrafo.", e);
            }
            JOptionPane.showMessageDialog(null,
                    "Multidigrafo salvo com sucesso.", "Salvar multidigrafo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnSalvarMultidigrafoActionPerformed

    private void carregarProcessosNegocio() {
        BpelDAO dao = new BpelDAO();
        List<ProcessoNegocio> processosNegocio = dao.obterProcessosNegocio();
        Collections.sort(processosNegocio);
        DefaultListModel modelo = (DefaultListModel) this.lstPN.getModel();
        for (ProcessoNegocio processoNegocio : processosNegocio) {
            modelo.addElement(processoNegocio);
        }
        this.lstPN.setModel(modelo);
    }

    private void carregarMultidigrafo() {
        MultidigrafoDAO dao = new MultidigrafoDAO();
        this.multidigrafo = dao.obterMultidigrafo();
        if (this.multidigrafo != null) {
            this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo.obterVisualizador(this.multidigrafo));
        }
    }

    private ProcessoNegocio obterProcessoNegocio(JList listaProcessosNegocio, int idProcessoNegocio) {
        DefaultListModel modelo = (DefaultListModel) listaProcessosNegocio.getModel();
        for (int i = 0; i < modelo.size(); i++) {
            ProcessoNegocio processoNegocio = (ProcessoNegocio) modelo.get(i);
            if (processoNegocio.getId() == idProcessoNegocio) {
                return processoNegocio;
            }
        }
        return null;
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(LOOK_AND_FEEL);
        } catch (Exception e) {
            log.warn("Erro: nao conseguiu setar o look & feel da janela para " + LOOK_AND_FEEL, e);
        }

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcularRelevancia;
    private javax.swing.JButton btnColetarMetricas;
    private javax.swing.JButton btnExportarListaServicos;
    private javax.swing.JButton btnGerarMultidigrafo;
    private javax.swing.JButton btnImportarPN;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvarMultidigrafo;
    private javax.swing.JFileChooser flcExportarListaServicos;
    private javax.swing.JFileChooser flcImportarPN;
    private javax.swing.JLabel lblPN;
    private javax.swing.JList lstPN;
    private javax.swing.JScrollPane scpMultidigrafo;
    private javax.swing.JScrollPane scpPN;
    private javax.swing.JScrollPane scpServicos;
    private javax.swing.JTable tblServicos;
    // End of variables declaration//GEN-END:variables
}

class BPELFileFilter extends FileFilter {

    public boolean accept(File arquivo) {
        return arquivo.isDirectory() || "bpel".equals(Arquivo.obterExtensao(arquivo));
    }

    public String getDescription() {
        return "BPEL (*.bpel)";
    }
}

class CSVFileFilter extends FileFilter {

    public boolean accept(File arquivo) {
        return arquivo.isDirectory() || "csv".equals(Arquivo.obterExtensao(arquivo));
    }

    public String getDescription() {
        return "CSV (*.csv)";
    }
}

class SelecaoPNRenderer extends JLabel implements ListCellRenderer {

    private Graph<Vertice, Arco> multidigrafo;

    public SelecaoPNRenderer(Graph<Vertice, Arco> multidigrafo) {
        this.multidigrafo = multidigrafo;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText(value.toString());
        if (MultidigrafoUtil.contemProcessoNegocio(this.multidigrafo, (ProcessoNegocio) value)) {
            this.setFont(new Font(list.getFont().getName(), Font.BOLD, list.getFont().getSize()));
        } else {
            this.setFont(list.getFont());
        }
        if (isSelected) {
            this.setForeground(list.getSelectionForeground());
            this.setBackground(list.getSelectionBackground());
        } else {
            this.setForeground(list.getForeground());
            this.setBackground(list.getBackground());
        }
        this.setOpaque(true);
        return this;
    }
}

class ListaServicosTableModel extends AbstractTableModel {

    private String[] nomesColunas;
    private List<Entry<String, Double>> listaServicos;

    public ListaServicosTableModel() {
        this.nomesColunas = new String[]{"Servi\u00E7o", "Relev\u00E2ncia"};
        this.listaServicos = new ArrayList<Entry<String, Double>>();
    }

    public void adicionarItens(Map<String, Double> relevanciaServicos) {
        this.listaServicos.clear();
        this.fireTableRowsDeleted(0, this.listaServicos.isEmpty() ? 0 : this.listaServicos.size() - 1);

        this.listaServicos.addAll(relevanciaServicos.entrySet());
        Collections.sort(this.listaServicos, Collections.reverseOrder(new ComparatorRelevancia()));
        this.fireTableRowsInserted(0, this.listaServicos.isEmpty() ? 0 : this.listaServicos.size() - 1);
    }

    public int getColumnCount() {
        return this.nomesColunas.length;
    }

    public int getRowCount() {
        return this.listaServicos.size();
    }

    public String getColumnName(int column) {
        return this.nomesColunas[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex < 0 || columnIndex > this.nomesColunas.length - 1) {
            throw new IllegalArgumentException("indice de coluna invalido (columnIndex = " + columnIndex + ").");
        }
        return columnIndex == 0 ? this.listaServicos.get(rowIndex).getKey() : this.listaServicos.get(rowIndex).getValue();
    }

    public String getStringValueAt(int rowIndex, int columnIndex) {
        if (columnIndex < 0 || columnIndex > this.nomesColunas.length - 1) {
            throw new IllegalArgumentException("indice de coluna invalido (columnIndex = " + columnIndex + ").");
        }
        return columnIndex == 0 ? this.listaServicos.get(rowIndex).getKey() : this.listaServicos.get(rowIndex).getValue().toString();
    }

    public Class getColumnClass(int columnIndex) {
        return this.getValueAt(0, columnIndex).getClass();
    }
}

class ComparatorRelevancia implements Comparator<Entry<String, Double>> {

    public int compare(Entry<String, Double> item1, Entry<String, Double> item2) {
        return Double.compare(item1.getValue(), item2.getValue());
    }
}