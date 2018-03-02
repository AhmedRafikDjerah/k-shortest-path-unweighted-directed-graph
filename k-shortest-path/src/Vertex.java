import java.util.List;


public class Vertex implements Comparable<Vertex> {

	/**
	 * Unique ID for every node.
	 */
	private int id;
	/**
	 * Label to be displayed on the vertex (Optional).
	 */
	private String label;
	/**
	 * To hold a previous node in the shortest path. (This would hold a single
	 * node only in one of the possible shortest paths.)
	 */
	private Vertex previous;
	/**
	 * A distance measure for this vertex from source vertex.
	 */
	public double sourceDistance = Double.POSITIVE_INFINITY;
	/**
	 * A List of all previous nodes in all the possible shortest paths.
	 */
	private List<Vertex> prev;

	public Vertex(int id) {
		this(id, id + "");
	}

	public Vertex(int id, String label) {
		this.id = id;
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the prev
	 */
	public List<Vertex> getPrev() {
		return prev;
	}

	/**
	 * @param prev
	 *            the prev to set
	 */
	public void setPrev(List<Vertex> prev) {
		this.prev = prev;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the previous
	 */
	public Vertex getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	/**
	 * @return the minDistance
	 */
	public double getMinDistance() {
		return sourceDistance;
	}

	/**
	 * @param minDistance
	 *            the minDistance to set
	 */
	public void setMinDistance(double minDistance) {
		this.sourceDistance = minDistance;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(sourceDistance, other.sourceDistance);
	}

}