import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		Graph<Vertex, MyLink> g = new UndirectedSparseGraph<Vertex, MyLink>();
		
		Dijkstra d = new Dijkstra(g);
		
		Vertex node1 = new Vertex(1, "node 1");
		Vertex node2 = new Vertex(2, "node 2");
		Vertex node3 = new Vertex(3, "node 3");
		Vertex node4 = new Vertex(4, "node 4");
		
		g.addVertex(node1);
		g.addVertex(node2);
		g.addVertex(node3);
		g.addVertex(node4);
		
		
		g.addEdge(new MyLink(3.0, "1-2"), node1,node2);
		g.addEdge(new MyLink(1.0, "1-3"), node1,node3);
		g.addEdge(new MyLink(2.0, "3-2"), node3,node2);
		g.addEdge(new MyLink(1.0, "3-4"), node3,node4);
		g.addEdge(new MyLink(1.0, "2-4"), node2,node4);
		
		Vertex source = node1;
		d.computeAllShortestPaths(source.getId());
		
		//d.getShortestPathTo(node2);
		
		Collection<Vertex> vertices = g.getVertices();
		Vertex v;
		int i = 1;

		for (Iterator<Vertex> iterator = vertices.iterator(); iterator.hasNext();) {
			v = (Vertex) iterator.next();
			if(v == node2) {
				System.out.println("Distance to " + v.getId() + ": " + v.sourceDistance);
				List<Vertex> path = d.getShortestPathTo(v);
				System.out.println("Path: " + path);

				System.out.println("---- All Possible Paths ----");
				Set<List<Vertex>> allShortestPaths = d.getAllShortestPathsTo(v);

				for (Iterator<List<Vertex>> iter = allShortestPaths.iterator(); iter.hasNext(); i++) {
					List<Vertex> p = (List<Vertex>) iter.next();
					
					System.out.println("Path " + i + ": " + p + ": " + v.sourceDistance);
					
				}
				
				i = 1;
				
				System.out.print("#####################################################\n\n");
				
			}
			
			
		}

	
		

		
		
		// Graph Visualization
		Layout<Vertex, MyLink> layout = new CircleLayout<Vertex, MyLink>(g);
		layout.setSize(new Dimension(300,300));;
		BasicVisualizationServer<Vertex, MyLink> vv = new BasicVisualizationServer<Vertex, MyLink>(layout);
		
		
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
