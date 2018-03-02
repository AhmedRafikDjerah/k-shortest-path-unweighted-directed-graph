import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


import edu.uci.ics.jung.graph.Graph;


public class Dijkstra {

	private Graph<Vertex, MyLink> g;
	private Set<List<Vertex>> allShortestPaths;

	public Dijkstra(Graph<Vertex, MyLink> g) {
		this.g = g;
	}

	private Vertex getSourceFromId(Integer sourceId) {
		Collection<Vertex> vertices = g.getVertices();
		for (Iterator<Vertex> iterator = vertices.iterator(); iterator
				.hasNext();) {
			Vertex vertex = (Vertex) iterator.next();
			if (vertex.getId() == sourceId)
				return vertex;
		}
		return null;
	}


	public void computeAllShortestPaths(Integer sourceId) {
		Vertex source = getSourceFromId(sourceId);
		source.sourceDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);
		List<Vertex> prev = null;

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			Collection<Vertex> neighbs = g.getNeighbors(u);
			for (Iterator<Vertex> iterator = neighbs.iterator(); iterator
					.hasNext();) {
				Vertex nv = (Vertex) iterator.next();
				prev = nv.getPrev();
				double weight = g.findEdge(u, nv).getWeight();
				double distanceThroughU = u.sourceDistance + weight;
				if (distanceThroughU < nv.sourceDistance) {
					vertexQueue.remove(nv);
					nv.sourceDistance = distanceThroughU;
					nv.setPrevious(u);
					vertexQueue.add(nv);
					prev = new ArrayList<Vertex>();
					prev.add(u);
					nv.setPrev(prev);
				} else if (distanceThroughU == nv.sourceDistance) {
					if (prev != null)
						prev.add(u);
						
					else {
						prev = new ArrayList<Vertex>();
						prev.add(u);
						nv.setPrev(prev);
					}
				}
				
			}
		}
	}

	
	public List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex
				.getPrevious())
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}

	
	public List<List<Vertex>> getAllShortestPathsTo(Vertex target, int k) {
		allShortestPaths = new HashSet<List<Vertex>>();

		getShortestPath(new ArrayList<Vertex>(), target);
		
		List<List<Vertex>> converting = new ArrayList<List<Vertex>>();
		
		for(List<Vertex> v:allShortestPaths) {
			converting.add(new ArrayList<Vertex> (v));
		}
		
		List<List<Vertex>> sub = converting.subList(0, Math.min(converting.size(), k));
		
		return sub;
		
	}

	
	private List<Vertex> getShortestPath(List<Vertex> shortestPath, Vertex target) {
		
		List<Vertex> prev = target.getPrev();
		if (prev == null) {
			shortestPath.add(target);
			Collections.reverse(shortestPath);
			allShortestPaths.add(shortestPath);
		} else {
			List<Vertex> updatedPath = new ArrayList<Vertex>(shortestPath);
			updatedPath.add(target);

			for (Iterator<Vertex> iterator = prev.iterator(); iterator
					.hasNext();) {
				Vertex vertex = (Vertex) iterator.next();
				getShortestPath(updatedPath, vertex);
			}
		}
		return shortestPath;
	}
}