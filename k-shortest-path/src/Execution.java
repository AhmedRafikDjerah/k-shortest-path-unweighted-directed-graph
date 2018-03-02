import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Execution {

	public static void main(String[] args) {
		Graph<Integer, MyLink> g = new UndirectedSparseGraph<Integer, MyLink>();
		
		
		// Add Vertices
		g.addVertex((Integer)1);
		g.addVertex((Integer)2);
		g.addVertex((Integer)3);
		g.addVertex((Integer)4);
		
		
		// Add Edges
		g.addEdge(new MyLink(1.0, 1), 1,2,EdgeType.UNDIRECTED);
		g.addEdge(new MyLink(4.0, 2), 1,3,EdgeType.UNDIRECTED);
		g.addEdge(new MyLink(6.0, 3), 1,4,EdgeType.UNDIRECTED);
		g.addEdge(new MyLink(4.0, 5), 3,4,EdgeType.UNDIRECTED);
		
		
		
		// Graph Visualization
		Layout<Integer, MyLink> layout = new CircleLayout<Integer, MyLink>(g);
		layout.setSize(new Dimension(300,300));;
		BasicVisualizationServer<Integer, MyLink> vv = new BasicVisualizationServer<Integer, MyLink>(layout);
		
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

		
		vv.setPreferredSize(new Dimension(350, 350));
		JFrame frame = new JFrame("Graph Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}
