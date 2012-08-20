package br.ipt.servico.relevancia.multidigrafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.TransformerUtils;
import org.apache.log4j.Logger;

import br.ipt.servico.relevancia.selecao.DialogoTempoResposta;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * Define o layout visual, cores, estilo e politicas de renderizacao de um
 * multidigrafo em um container Swing.
 * 
 * @author Rodrigo Mendes Leme
 */
public class VisualizadorMultidigrafo {

    private static final Color[] CORES_PROCESSOS_NEGOCIO = new Color[] {
	    Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.BLACK,
	    Color.CYAN, Color.MAGENTA, Color.GRAY, Color.ORANGE, Color.PINK };

    private static Logger log = Logger
	    .getLogger(VisualizadorMultidigrafo.class);

    public static VisualizationViewer<Vertice, Arco> obterVisualizador(
	    Graph<Vertice, Arco> multidigrafo) throws IndexOutOfBoundsException {
	Layout<Vertice, Arco> layout = new StaticLayout<Vertice, Arco>(
		multidigrafo, TransformerUtils
			.mapTransformer(obterCoordenadasVertices(multidigrafo)));

	layout.setSize(new Dimension(300, 300));
	VisualizationViewer<Vertice, Arco> visualizador = new VisualizationViewer<Vertice, Arco>(
		layout);
	visualizador.setPreferredSize(new Dimension(350, 350));

	Map<String, Color> corProcessosNegocio = obterCores(multidigrafo);
	Transformer<Vertice, Paint> transformadorCorVertice = new TransformadorCorVertice(
		corProcessosNegocio);
	Transformer<Vertice, Paint> transformadorCorBordaVertice = new TransformadorCorBordaVertice(
		corProcessosNegocio);
	Transformer<Vertice, String> transformadorTextoRotuloVertice = new Transformer<Vertice, String>() {
	    public String transform(Vertice vertice) {
		return vertice instanceof VerticeInicio
			|| vertice instanceof VerticeFim ? ""
			: new ToStringLabeller<Vertice>().transform(vertice);
	    }
	};
	Transformer<Vertice, String> transformadorTextoDicaVertice = new Transformer<Vertice, String>() {
	    public String transform(Vertice vertice) {
		DecimalFormat df = new DecimalFormat("#.##");
		return vertice instanceof VerticeInicio
			|| vertice instanceof VerticeFim ? ""
			: "\u0394t = "
				+ df
					.format(Double
						.parseDouble(vertice
							.obterValorRotulo(Vertice.Rotulo.TEMPO_MEDIO_EXECUCAO)) / 1000)
				+ " s; SLA = "
				+ df
					.format(Double
						.parseDouble(vertice
							.obterValorRotulo(Vertice.Rotulo.TEMPO_RESPOSTA_ESPERADO)) / 1000)
				+ " s";
	    }
	};
	Transformer<Arco, Paint> transformadorCorArco = new TransformadorCorArco(
		corProcessosNegocio);
	Transformer<Arco, Stroke> transformadorEstiloArco = new Transformer<Arco, Stroke>() {
	    public Stroke transform(Arco arco) {
		return new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10.0f);
	    }
	};
	Transformer<Arco, String> transformadorTextoRotuloArco = new Transformer<Arco, String>() {
	    public String transform(Arco arco) {
		DecimalFormat df = new DecimalFormat("#.##");
		String textoRotulo = new ToStringLabeller<Arco>()
			.transform(arco);
		return "0".equals(textoRotulo) || "0.0".equals(textoRotulo) ? ""
			: df.format(Double.parseDouble(textoRotulo));
	    }
	};

	visualizador.getRenderContext().setVertexFillPaintTransformer(
		transformadorCorVertice);
	visualizador.getRenderContext().setVertexDrawPaintTransformer(
		transformadorCorBordaVertice);
	visualizador.getRenderContext().setVertexLabelTransformer(
		transformadorTextoRotuloVertice);
	visualizador.getRenderer().getVertexLabelRenderer().setPosition(
		Position.CNTR);
	visualizador.setVertexToolTipTransformer(transformadorTextoDicaVertice);
	visualizador.getRenderContext().setEdgeDrawPaintTransformer(
		transformadorCorArco);
	visualizador.getRenderContext().setEdgeStrokeTransformer(
		transformadorEstiloArco);
	visualizador.getRenderContext().setEdgeLabelTransformer(
		transformadorTextoRotuloArco);
	visualizador.getRenderContext().setArrowFillPaintTransformer(
		transformadorCorArco);
	visualizador.getRenderContext().setArrowDrawPaintTransformer(
		transformadorCorArco);

	PluggableGraphMouse pgm = new PluggableGraphMouse();
	pgm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON3_MASK));
	pgm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0,
		1.1f, 0.9f));
	pgm.add(new PickingGraphMousePlugin<Vertice, Arco>());
	pgm.add(new PopupGraphMousePlugin());
	visualizador.setGraphMouse(pgm);

	return visualizador;
    }

    private static Map<Vertice, Point2D> obterCoordenadasVertices(
	    Graph<Vertice, Arco> multidigrafo) {
	List<Vertice> verticesIniciais = new ArrayList<Vertice>();
	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeInicio) {
		verticesIniciais.add(vertice);
	    }
	}

	Map<Vertice, Point2D> coordenadasVertices = new HashMap<Vertice, Point2D>();
	double x = 100;
	for (Vertice vertice : verticesIniciais) {
	    definirCoordenadaVertice(multidigrafo, coordenadasVertices,
		    vertice, x, 20);
	    x += 120;
	}

	return coordenadasVertices;
    }

    private static void definirCoordenadaVertice(
	    Graph<Vertice, Arco> multidigrafo,
	    Map<Vertice, Point2D> coordenadasVertices, Vertice vertice,
	    double x, double y) {
	coordenadasVertices.put(vertice, new Point2D.Double(x, y));

	int i = 0;
	for (Vertice sucessor : multidigrafo.getSuccessors(vertice)) {
	    if (sucessor != vertice
		    && !coordenadasVertices.containsKey(sucessor)) {
		definirCoordenadaVertice(multidigrafo, coordenadasVertices,
			sucessor, x + (120 * i), y + 100);
		i++;
	    }
	}
    }

    private static Map<String, Color> obterCores(
	    Graph<Vertice, Arco> multidigrafo) throws IndexOutOfBoundsException {
	Map<String, Color> corProcessosNegocio = new HashMap<String, Color>();
	int cor = 0;

	for (Arco arco : multidigrafo.getEdges()) {
	    String idProcessoNegocio = arco
		    .obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO);
	    if (!corProcessosNegocio.containsKey(idProcessoNegocio)) {
		corProcessosNegocio.put(idProcessoNegocio,
			CORES_PROCESSOS_NEGOCIO[cor++]);
		if (cor >= CORES_PROCESSOS_NEGOCIO.length) {
		    throw new IndexOutOfBoundsException(
			    "Erro: excedeu o numero maximo de cores disponiveis ("
				    + CORES_PROCESSOS_NEGOCIO.length
				    + ") para os processos de negocio.");
		}
	    }
	}

	for (Vertice vertice : multidigrafo.getVertices()) {
	    if (vertice instanceof VerticeInicio
		    || vertice instanceof VerticeFim) {
		String idProcessoNegocio = vertice
			.obterValorRotulo(Vertice.Rotulo.ID_PROCESSO_NEGOCIO);
		if (!corProcessosNegocio.containsKey(idProcessoNegocio)) {
		    corProcessosNegocio.put(idProcessoNegocio,
			    CORES_PROCESSOS_NEGOCIO[cor++]);
		    if (cor >= CORES_PROCESSOS_NEGOCIO.length) {
			throw new IndexOutOfBoundsException(
				"Erro: excedeu o numero maximo de cores disponiveis ("
					+ CORES_PROCESSOS_NEGOCIO.length
					+ ") para os processos de negocio.");
		    }
		}
	    }
	}

	if (log.isDebugEnabled()) {
	    StringBuilder textoLog = new StringBuilder("Cores do multidigrafo:");
	    for (String idProcessoNegocio : corProcessosNegocio.keySet()) {
		textoLog.append("\n   PN " + idProcessoNegocio + " --> "
			+ corProcessosNegocio.get(idProcessoNegocio));
	    }
	    log.debug(textoLog);
	}

	return corProcessosNegocio;
    }
}

class TransformadorCorVertice implements Transformer<Vertice, Paint> {

    private Map<String, Color> corProcessosNegocio;

    public TransformadorCorVertice(Map<String, Color> corProcessosNegocio) {
	this.corProcessosNegocio = corProcessosNegocio;
    }

    public Paint transform(Vertice vertice) {
	return vertice instanceof VerticeInicio
		|| vertice instanceof VerticeFim ? this.corProcessosNegocio
		.get(vertice
			.obterValorRotulo(Vertice.Rotulo.ID_PROCESSO_NEGOCIO))
		: Color.WHITE;
    }
};

class TransformadorCorBordaVertice implements Transformer<Vertice, Paint> {

    private Map<String, Color> corProcessosNegocio;

    public TransformadorCorBordaVertice(Map<String, Color> corProcessosNegocio) {
	this.corProcessosNegocio = corProcessosNegocio;
    }

    public Paint transform(Vertice vertice) {
	return vertice instanceof VerticeInicio
		|| vertice instanceof VerticeFim ? this.corProcessosNegocio
		.get(vertice
			.obterValorRotulo(Vertice.Rotulo.ID_PROCESSO_NEGOCIO))
		: Color.BLACK;
    }
};

class TransformadorCorArco implements Transformer<Arco, Paint> {

    private Map<String, Color> corProcessosNegocio;

    public TransformadorCorArco(Map<String, Color> corProcessosNegocio) {
	this.corProcessosNegocio = corProcessosNegocio;
    }

    public Paint transform(Arco arco) {
	return this.corProcessosNegocio.get(arco
		.obterValorRotulo(Arco.Rotulo.ID_PROCESSO_NEGOCIO));
    }
};

class PopupGraphMousePlugin extends AbstractPopupGraphMousePlugin implements
	MouseListener {

    public PopupGraphMousePlugin() {
	this(MouseEvent.BUTTON3_MASK);
    }

    public PopupGraphMousePlugin(int modifiers) {
	super(modifiers);
    }

    protected void handlePopup(MouseEvent e) {
	if (e.getClickCount() == 2) {
	    VisualizationViewer<Vertice, Arco> visualizador = (VisualizationViewer<Vertice, Arco>) e
		    .getSource();
	    Point2D p = e.getPoint();
	    GraphElementAccessor<Vertice, Arco> pickSupport = visualizador
		    .getPickSupport();
	    if (pickSupport != null) {
		Vertice vertice = pickSupport.getVertex(visualizador
			.getGraphLayout(), p.getX(), p.getY());
		if (vertice != null
			&& !(vertice instanceof VerticeInicio || vertice instanceof VerticeFim)) {
		    Frame tela = (Frame) visualizador.getTopLevelAncestor();
		    DialogoTempoResposta dlgTempoResposta = new DialogoTempoResposta(
			    tela, vertice);
		    dlgTempoResposta.setLocation((int) p.getX() + tela.getX(),
			    (int) p.getY() + tela.getY());
		    dlgTempoResposta.setVisible(true);
		}
	    }
	}
    }
}