package br.ipt.servico.relevancia.selecao;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.bpel.BpelDAO;
import br.ipt.servico.relevancia.bpel.ProcessoNegocio;
import br.ipt.servico.relevancia.excecao.BPAlreadyExistsException;
import br.ipt.servico.relevancia.metricas.ColetorMetricasDesempenho;
import br.ipt.servico.relevancia.multidigrafo.Arco;
import br.ipt.servico.relevancia.multidigrafo.MultidigrafoDAO;
import br.ipt.servico.relevancia.multidigrafo.MultidigrafoUtil;
import br.ipt.servico.relevancia.multidigrafo.Vertice;
import br.ipt.servico.relevancia.multidigrafo.VerticeFim;
import br.ipt.servico.relevancia.multidigrafo.VerticeInicio;
import br.ipt.servico.relevancia.multidigrafo.VisualizadorMultidigrafo;
import br.ipt.servico.relevancia.transformacao.ParserBPEL;
import br.ipt.servico.relevancia.util.Arquivo;
import edu.uci.ics.jung.graph.Graph;

/**
 * Tela principal do sistema, para interacao com usuario e visualizacao dos
 * servicos e do multidigrafo dos processos de negocio.
 * 
 * @author Rodrigo Mendes Leme
 */
public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = -5045491813412667946L;

    private static final String LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    private static final int NUMERO_MAXIMO_PROCESSOS_NEGOCIO = 10;

    private static Logger log = Logger.getLogger(TelaPrincipal.class);

    private Graph<Vertice, Arco> multidigrafo;

    private JButton btnCalcularRelevancia;
    private JButton btnColetarMetricas;
    private JButton btnExportarListaServicos;
    private JButton btnGerarMultidigrafo;
    private JButton btnImportarPN;
    private JButton btnSair;
    private JButton btnSalvarMultidigrafo;
    private JFileChooser flcExportarListaServicos;
    private JFileChooser flcImportarPN;
    private JLabel lblPN;
    private JList lstPN;
    private JScrollPane scpMultidigrafo;
    private JScrollPane scpPN;
    private JScrollPane scpServicos;
    private JTable tblServicos;

    public TelaPrincipal() {
	this.initComponents();
	this.carregarProcessosNegocio();
	this.carregarMultidigrafo();
	if (this.multidigrafo != null) {
	    this.lstPN
		    .setCellRenderer(new SelecaoPNRenderer(this.multidigrafo));
	}
    }

    private void initComponents() {
	flcImportarPN = new JFileChooser();
	flcExportarListaServicos = new JFileChooser();
	btnImportarPN = new JButton();
	lblPN = new JLabel();
	scpPN = new JScrollPane();
	lstPN = new JList();
	btnGerarMultidigrafo = new JButton();
	btnColetarMetricas = new JButton();
	scpMultidigrafo = new JScrollPane();
	scpServicos = new JScrollPane();
	tblServicos = new JTable();
	btnCalcularRelevancia = new JButton();
	btnExportarListaServicos = new JButton();
	btnSair = new JButton();
	btnSalvarMultidigrafo = new JButton();

	flcImportarPN.setAcceptAllFileFilterUsed(false);
	flcImportarPN.setCurrentDirectory(new java.io.File("C:\\"));
	flcImportarPN.setDialogTitle("Importar Processo de Negócio");
	flcImportarPN.setFileFilter(new BPELFileFilter());

	flcExportarListaServicos.setAcceptAllFileFilterUsed(false);
	flcExportarListaServicos.setDialogType(JFileChooser.SAVE_DIALOG);
	flcExportarListaServicos.setCurrentDirectory(new java.io.File("C:\\"));
	flcExportarListaServicos.setDialogTitle("Exportar Lista de Serviços");
	flcExportarListaServicos.setFileFilter(new CSVFileFilter());

	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setTitle("SOA - IPT - Ferramenta para Cálculo do Índice de Relevância dos Serviços");
	setName("frmTelaPrincipal");

	btnImportarPN.setText("Importar PN");
	btnImportarPN.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnImportarPNActionPerformed(evt);
	    }
	});

	lblPN.setText("Processos de negócio");

	lstPN.setModel(new DefaultListModel());
	scpPN.setViewportView(lstPN);

	btnGerarMultidigrafo.setText("Gerar multidigrafo");
	btnGerarMultidigrafo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnGerarMultidigrafoActionPerformed(evt);
	    }
	});

	btnColetarMetricas.setText("Coletar métricas");
	btnColetarMetricas.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnColetarMetricasActionPerformed(evt);
	    }
	});

	tblServicos.setModel(new ListaServicosTableModel());
	tblServicos.setEnabled(false);
	tblServicos.getTableHeader().setReorderingAllowed(false);
	scpServicos.setViewportView(tblServicos);

	btnCalcularRelevancia.setText("Calcular relevância");
	btnCalcularRelevancia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnCalcularRelevanciaActionPerformed(evt);
	    }
	});

	btnExportarListaServicos.setText("Exportar lista de serviços");
	btnExportarListaServicos.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnExportarListaServicosActionPerformed(evt);
	    }
	});

	btnSair.setText("Sair");
	btnSair.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnSairActionPerformed(evt);
	    }
	});

	btnSalvarMultidigrafo.setText("Salvar multidigrafo");
	btnSalvarMultidigrafo.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		btnSalvarMultidigrafoActionPerformed(evt);
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
									.createParallelGroup(
										Alignment.TRAILING,
										false)
									.addComponent(
										btnImportarPN,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addComponent(
										scpPN,
										GroupLayout.PREFERRED_SIZE,
										139,
										GroupLayout.PREFERRED_SIZE)
									.addComponent(
										lblPN,
										Alignment.LEADING))
							.addComponent(btnSair))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						layout.createParallelGroup(
							Alignment.LEADING,
							false).addComponent(
							scpMultidigrafo)
							.addComponent(
								scpServicos))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						layout
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								Alignment.TRAILING,
								layout
									.createSequentialGroup()
									.addGap(
										0,
										0,
										Short.MAX_VALUE)
									.addGroup(
										layout
											.createParallelGroup(
												Alignment.LEADING,
												false)
											.addComponent(
												btnCalcularRelevancia,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
											.addComponent(
												btnExportarListaServicos)))
							.addGroup(
								layout
									.createSequentialGroup()
									.addGroup(
										layout
											.createParallelGroup(
												Alignment.TRAILING,
												false)
											.addComponent(
												btnGerarMultidigrafo,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
											.addComponent(
												btnSalvarMultidigrafo,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
											.addComponent(
												btnColetarMetricas,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
									.addGap(
										0,
										0,
										Short.MAX_VALUE)))
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
								Alignment.LEADING)
							.addGroup(
								layout
									.createSequentialGroup()
									.addGroup(
										layout
											.createParallelGroup(
												Alignment.LEADING)
											.addComponent(
												btnGerarMultidigrafo)
											.addGroup(
												layout
													.createSequentialGroup()
													.addComponent(
														btnImportarPN)
													.addGap(
														18,
														18,
														18)
													.addComponent(
														lblPN)))
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addGroup(
										layout
											.createParallelGroup(
												Alignment.LEADING)
											.addComponent(
												scpPN,
												GroupLayout.DEFAULT_SIZE,
												650,
												Short.MAX_VALUE)
											.addGroup(
												layout
													.createSequentialGroup()
													.addComponent(
														btnColetarMetricas)
													.addPreferredGap(
														ComponentPlacement.RELATED)
													.addComponent(
														btnSalvarMultidigrafo)
													.addGap(
														0,
														0,
														Short.MAX_VALUE))))
							.addComponent(
								scpMultidigrafo))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						layout
							.createParallelGroup(
								Alignment.LEADING,
								false)
							.addGroup(
								layout
									.createSequentialGroup()
									.addComponent(
										btnCalcularRelevancia)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										btnExportarListaServicos)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addComponent(
										btnSair))
							.addComponent(
								scpServicos,
								GroupLayout.PREFERRED_SIZE,
								89,
								GroupLayout.PREFERRED_SIZE))
					.addGap(9, 9, 9)));

	lblPN.getAccessibleContext().setAccessibleName(
		"Processos de neg\\u00F3cio");
	btnColetarMetricas.getAccessibleContext().setAccessibleName(
		"Coletar m\\u00E9tricas");
	btnCalcularRelevancia.getAccessibleContext().setAccessibleName(
		"Calcular relev\\u00E2ncia");
	btnExportarListaServicos.getAccessibleContext().setAccessibleName(
		"Exportar lista de servi\\u00E7os");

	pack();
    }

    private void btnImportarPNActionPerformed(ActionEvent evt) {
	if (this.lstPN.getModel().getSize() == NUMERO_MAXIMO_PROCESSOS_NEGOCIO) {
	    JOptionPane
		    .showMessageDialog(
			    null,
			    "N\u00E3o \u00E9 poss\u00EDvel importar mais processos de neg\u00F3cio. Limite m\u00E1ximo atingido ("
				    + NUMERO_MAXIMO_PROCESSOS_NEGOCIO + ").",
			    "Importar Processo de Neg\u00F3cio",
			    JOptionPane.WARNING_MESSAGE);
	    return;
	}

	if (evt.getSource() == this.btnImportarPN) {
	    int retorno = this.flcImportarPN.showOpenDialog(this);
	    while (retorno == JFileChooser.APPROVE_OPTION
		    && !this.flcImportarPN.getSelectedFile().getName()
			    .endsWith(".bpel")) {
		JOptionPane.showMessageDialog(null, "O arquivo "
			+ this.flcImportarPN.getSelectedFile()
			+ " n\u00E3o \u00E9 um processo de neg\u00F3cio BPEL.",
			"Importar Processo de Neg\u00F3cio",
			JOptionPane.ERROR_MESSAGE);
		retorno = this.flcImportarPN.showOpenDialog(this);
	    }

	    if (retorno == JFileChooser.APPROVE_OPTION) {
		try {
		    this.setCursor(Cursor
			    .getPredefinedCursor(Cursor.WAIT_CURSOR));

		    DialogoURLProcessoNegocio dlgURLProcessonegocio = new DialogoURLProcessoNegocio(
			    this, null);
		    dlgURLProcessonegocio.setLocationRelativeTo(null);
		    dlgURLProcessonegocio.setVisible(true);

		    ProcessoNegocio processoNegocio = ProcessoNegocio.importar(
			    this.flcImportarPN.getSelectedFile(),
			    dlgURLProcessonegocio.getURLProcessoNegocio());

		    DefaultListModel modelo = (DefaultListModel) this.lstPN
			    .getModel();
		    int indice = Arrays.binarySearch(modelo.toArray(),
			    processoNegocio);
		    modelo.add(indice >= 0 ? indice : -indice - 1,
			    processoNegocio);
		    this.lstPN.setModel(modelo);

		    JOptionPane.showMessageDialog(null, "Arquivo "
			    + this.flcImportarPN.getSelectedFile()
			    + " importado com sucesso.",
			    "Importar Processo de Neg\u00F3cio",
			    JOptionPane.INFORMATION_MESSAGE);
		} catch (BPAlreadyExistsException e) {
		    log.warn("Erro: o arquivo "
			    + this.flcImportarPN.getSelectedFile()
			    + " nao pode ser importado.", e);
		    JOptionPane
			    .showMessageDialog(
				    null,
				    "N\u00E3o foi poss\u00EDvel importar o arquivo "
					    + this.flcImportarPN
						    .getSelectedFile()
					    + ". Processo de neg\u00F3cio j\u00E1 existe no sistema.",
				    "Importar Processo de Neg\u00F3cio",
				    JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
		    log.error("Erro: o arquivo "
			    + this.flcImportarPN.getSelectedFile()
			    + " nao pode ser importado.", e);
		    JOptionPane.showMessageDialog(null, "O arquivo "
			    + this.flcImportarPN.getSelectedFile()
			    + " n\u00E3o p\u00F4de ser importado.",
			    "Importar Processo de Neg\u00F3cio",
			    JOptionPane.ERROR_MESSAGE);
		} finally {
		    this.setCursor(Cursor.getDefaultCursor());
		}
	    }
	}
    }

    private void btnExportarListaServicosActionPerformed(ActionEvent evt) {
	if (evt.getSource() == this.btnExportarListaServicos) {
	    int retorno = this.flcExportarListaServicos.showSaveDialog(this);
	    while (retorno == JFileChooser.APPROVE_OPTION
		    && (!this.flcExportarListaServicos.getSelectedFile()
			    .getName().endsWith(".csv") && this.flcExportarListaServicos
			    .getSelectedFile().getName().contains("."))) {
		JOptionPane.showMessageDialog(null, "O arquivo "
			+ this.flcExportarListaServicos.getSelectedFile()
			+ " n\u00E3o tem a extens\u00E3o \".csv\".",
			"Exportar Lista de Servi\u00E7os",
			JOptionPane.ERROR_MESSAGE);
		retorno = this.flcExportarListaServicos.showSaveDialog(this);
	    }

	    if (retorno == JFileChooser.APPROVE_OPTION) {
		try {
		    this.setCursor(Cursor
			    .getPredefinedCursor(Cursor.WAIT_CURSOR));

		    String nomeArquivo = this.flcExportarListaServicos
			    .getSelectedFile().getCanonicalPath();
		    BufferedWriter bw = new BufferedWriter(new FileWriter(
			    new File(nomeArquivo.endsWith(".csv") ? nomeArquivo
				    : nomeArquivo + ".csv")));
		    ListaServicosTableModel modelo = (ListaServicosTableModel) this.tblServicos
			    .getModel();
		    for (int i = 0; i < modelo.getRowCount(); i++) {
			for (int j = 0; j < modelo.getColumnCount(); j++) {
			    bw.append(modelo.getStringValueAt(i, j));
			    if (j < modelo.getColumnCount() - 1) {
				bw.append(";");
			    } else {
				if (i < modelo.getRowCount() - 1) {
				    bw.append(System
					    .getProperty("line.separator"));
				}
			    }
			}
		    }
		    bw.close();

		    JOptionPane.showMessageDialog(null,
			    "Lista de servi\u00E7os exportada com sucesso.",
			    "Exportar Lista de Servi\u00E7os",
			    JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		    log
			    .error(
				    "Erro: a lista de servicos nao pode ser exportada.",
				    e);
		    JOptionPane
			    .showMessageDialog(
				    null,
				    "A lista de servi\u00E7os n\u00E3o p\u00F4de ser exportada.",
				    "Exportar Lista de Servi\u00E7os",
				    JOptionPane.ERROR_MESSAGE);
		} finally {
		    this.setCursor(Cursor.getDefaultCursor());
		}
	    }
	}
    }

    private void btnSairActionPerformed(ActionEvent evt) {
	if (evt.getSource() == this.btnSair) {
	    System.exit(0);
	}
    }

    private void btnGerarMultidigrafoActionPerformed(ActionEvent evt) {
	this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	if (this.lstPN.getSelectedValues().length > 0) {
	    List<ProcessoNegocio> processosNegocio = new ArrayList<ProcessoNegocio>();
	    for (Object obj : this.lstPN.getSelectedValues()) {
		processosNegocio.add((ProcessoNegocio) obj);
	    }
	    ParserBPEL parser = new ParserBPEL();
	    this.multidigrafo = parser.parse(processosNegocio);
	    this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo
		    .obterVisualizador(this.multidigrafo));
	}
	this.lstPN.setCellRenderer(new SelecaoPNRenderer(this.multidigrafo));
	this.setCursor(Cursor.getDefaultCursor());
    }

    private void btnColetarMetricasActionPerformed(ActionEvent evt) {
	this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	if (this.multidigrafo != null) {
	    ColetorMetricasDesempenho coletor = new ColetorMetricasDesempenho();

	    // Tempo medio de execucao (vertice)
	    for (Vertice vertice : this.multidigrafo.getVertices()) {
		if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
		    Set<Integer> idsProcessosNegocio = new HashSet<Integer>();
		    for (Arco arco : this.multidigrafo.getInEdges(vertice)) {
			idsProcessosNegocio
				.add(new Integer(
					arco
						.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO)));
		    }
		    for (Arco arco : this.multidigrafo.getOutEdges(vertice)) {
			idsProcessosNegocio
				.add(new Integer(
					arco
						.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO)));
		    }

		    double somaTemposMedios = 0;
		    int n = 0;
		    for (int idProcessoNegocio : idsProcessosNegocio) {
			ProcessoNegocio processoNegocio = this
				.obterProcessoNegocio(this.lstPN,
					idProcessoNegocio);
			if (processoNegocio != null) {
			    String[] servicoOperacao = vertice
				    .obterValorRotulo(
					    Vertice.Rotulo.SERVICO_OPERACAO)
				    .split("\\.");
			    Map<Integer, Double> temposMedios = coletor
				    .coletarTempoMedioExecucao(processoNegocio
					    .getNome(), servicoOperacao[1]);
			    for (Double tempoMedio : temposMedios.values()) {
				n++;
				somaTemposMedios += tempoMedio;
			    }
			}
		    }

		    if (n != 0) {
			double tempoMedio = somaTemposMedios / n;
			log
				.debug("Vertice \""
					+ vertice
						.obterValorRotulo(Vertice.Rotulo.SERVICO_OPERACAO)
					+ "\": tempo medio de execucao = "
					+ tempoMedio);
			vertice.adicionarRotulo(
				Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO, String
					.valueOf(tempoMedio));
		    }
		}
	    }

	    // Numero de invocacoes (arco)
	    for (Vertice vertice : this.multidigrafo.getVertices()) {
		if (!(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
		    Map<Integer, Arco> arcos = new HashMap<Integer, Arco>();
		    for (Arco arco : this.multidigrafo.getInEdges(vertice)) {
			Integer idProcessoNegocio = new Integer(
				arco
					.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO));
			if (!arcos.containsKey(idProcessoNegocio)) {
			    arcos.put(idProcessoNegocio, arco);
			}
		    }

		    for (Integer idProcessoNegocio : arcos.keySet()) {
			ProcessoNegocio processoNegocio = this
				.obterProcessoNegocio(this.lstPN,
					idProcessoNegocio);
			if (processoNegocio != null) {
			    String[] servicoOperacao = vertice
				    .obterValorRotulo(
					    Vertice.Rotulo.SERVICO_OPERACAO)
				    .split("\\.");
			    Map<Integer, Integer> numerosInvocacoes = coletor
				    .coletarNumeroInvocacoes(processoNegocio
					    .getNome(), servicoOperacao[1]);

			    if (!numerosInvocacoes.isEmpty()) {
				double somaNumerosInvocacoes = 0;
				for (Integer numeroInvocacoes : numerosInvocacoes
					.values()) {
				    somaNumerosInvocacoes += numeroInvocacoes;
				}

				Arco arco = arcos.get(idProcessoNegocio);
				log
					.debug("Arco \""
						+ this.multidigrafo
							.getSource(arco)
							.obterValorRotulo(
								Vertice.Rotulo.SERVICO_OPERACAO)
						+ "\" --> \""
						+ this.multidigrafo
							.getDest(arco)
							.obterValorRotulo(
								Vertice.Rotulo.SERVICO_OPERACAO)
						+ "\" (processo de negocio \""
						+ processoNegocio.getNome()
						+ "\"): numero de invocacoes = "
						+ somaNumerosInvocacoes);
				arco
					.adicionarRotulo(
						Arco.Rotulo.NUMERO_INVOCACOES,
						String
							.valueOf(somaNumerosInvocacoes));
			    }
			}
		    }
		}
	    }

	    this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo
		    .obterVisualizador(this.multidigrafo));

	    JOptionPane.showMessageDialog(null,
		    "M\u00E9tricas de desempenho coletadas com sucesso.",
		    "Coletar m\u00E9tricas de desempenho",
		    JOptionPane.INFORMATION_MESSAGE);
	}
	this.setCursor(Cursor.getDefaultCursor());
    }

    private void btnCalcularRelevanciaActionPerformed(ActionEvent evt) {
	this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	ListaServicosTableModel modelo = (ListaServicosTableModel) this.tblServicos
		.getModel();
	CalculadoraRelevanciaServicos calculadora = new CalculadoraRelevanciaServicos();
	modelo.adicionarItens(calculadora.calcular(this.multidigrafo));
	this.setCursor(Cursor.getDefaultCursor());
    }

    private void btnSalvarMultidigrafoActionPerformed(ActionEvent evt) {
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
    }

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
	    this.scpMultidigrafo.setViewportView(VisualizadorMultidigrafo
		    .obterVisualizador(this.multidigrafo));
	}
    }

    private ProcessoNegocio obterProcessoNegocio(JList listaProcessosNegocio,
	    int idProcessoNegocio) {
	DefaultListModel modelo = (DefaultListModel) listaProcessosNegocio
		.getModel();
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
	    log.warn("Erro: nao conseguiu setar o look & feel da janela para "
		    + LOOK_AND_FEEL, e);
	}

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new TelaPrincipal().setVisible(true);
	    }
	});
    }
}

class BPELFileFilter extends FileFilter {

    public boolean accept(File arquivo) {
	return arquivo.isDirectory()
		|| "bpel".equals(Arquivo.obterExtensao(arquivo));
    }

    public String getDescription() {
	return "BPEL (*.bpel)";
    }
}

class CSVFileFilter extends FileFilter {

    public boolean accept(File arquivo) {
	return arquivo.isDirectory()
		|| "csv".equals(Arquivo.obterExtensao(arquivo));
    }

    public String getDescription() {
	return "CSV (*.csv)";
    }
}

class SelecaoPNRenderer extends JLabel implements ListCellRenderer {

    private static final long serialVersionUID = -1640292307032838581L;

    private Graph<Vertice, Arco> multidigrafo;

    public SelecaoPNRenderer(Graph<Vertice, Arco> multidigrafo) {
	this.multidigrafo = multidigrafo;
    }

    public Component getListCellRendererComponent(JList list, Object value,
	    int index, boolean isSelected, boolean cellHasFocus) {
	this.setText(value.toString());
	if (MultidigrafoUtil.contemProcessoNegocio(this.multidigrafo,
		(ProcessoNegocio) value)) {
	    this.setFont(new Font(list.getFont().getName(), Font.BOLD, list
		    .getFont().getSize()));
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

    private static final long serialVersionUID = -1214957022204410639L;

    private String[] nomesColunas;

    private List<Relevancia> listaServicos;

    public ListaServicosTableModel() {
	this.nomesColunas = new String[] { "Servi\u00E7o",
		"Rel. est\u00E1tica", "Rel. din\u00E2mica",
		"Rel. de desempenho", "Relev\u00E2ncia" };
	this.listaServicos = new ArrayList<Relevancia>();
    }

    public void adicionarItens(Set<Relevancia> relevanciaServicos) {
	this.listaServicos.clear();
	this.fireTableRowsDeleted(0, this.listaServicos.isEmpty() ? 0
		: this.listaServicos.size() - 1);

	this.listaServicos.addAll(relevanciaServicos);
	Collections.sort(this.listaServicos, Collections
		.reverseOrder(new ComparatorRelevancia()));
	this.fireTableRowsInserted(0, this.listaServicos.isEmpty() ? 0
		: this.listaServicos.size() - 1);
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
	    throw new IllegalArgumentException(
		    "indice de coluna invalido (columnIndex = " + columnIndex
			    + ").");
	}
	switch (columnIndex) {
	case 0:
	    return this.listaServicos.get(rowIndex).getServicoOperacao();
	case 1:
	    return this.listaServicos.get(rowIndex).getRelevanciaEstatica();
	case 2:
	    return this.listaServicos.get(rowIndex).getRelevanciaDinamica();
	case 3:
	    return this.listaServicos.get(rowIndex).getRelevanciaDeDesempenho();
	case 4:
	    return this.listaServicos.get(rowIndex).getRelevancia();
	default:
	    return "";
	}
    }

    public String getStringValueAt(int rowIndex, int columnIndex) {
	if (columnIndex < 0 || columnIndex > this.nomesColunas.length - 1) {
	    throw new IllegalArgumentException(
		    "indice de coluna invalido (columnIndex = " + columnIndex
			    + ").");
	}
	switch (columnIndex) {
	case 0:
	    return this.listaServicos.get(rowIndex).getServicoOperacao();
	case 1:
	    return String.valueOf(this.listaServicos.get(rowIndex)
		    .getRelevanciaEstatica());
	case 2:
	    return String.valueOf(this.listaServicos.get(rowIndex)
		    .getRelevanciaDinamica());
	case 3:
	    return String.valueOf(this.listaServicos.get(rowIndex)
		    .getRelevanciaDeDesempenho());
	case 4:
	    return String.valueOf(this.listaServicos.get(rowIndex)
		    .getRelevancia());
	default:
	    return "";
	}
    }

    public Class<?> getColumnClass(int columnIndex) {
	return this.getValueAt(0, columnIndex).getClass();
    }
}

class ComparatorRelevancia implements Comparator<Relevancia> {

    public int compare(Relevancia item1, Relevancia item2) {
	return Double.compare(item1.getRelevancia(), item2.getRelevancia());
    }
}