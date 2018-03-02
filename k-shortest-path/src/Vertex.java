import java.util.List;


public class Vertex implements Comparable<Vertex> {


	private int id;
	
	private String label;
	
	private Vertex previous;
	
	public double sourceDistance = Double.POSITIVE_INFINITY;
	
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

	
	public int getId() {
		return id;
	}

	
	public List<Vertex> getPrev() {
		return prev;
	}

	
	public void setPrev(List<Vertex> prev) {
		this.prev = prev;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getLabel() {
		return label;
	}

	
	public void setLabel(String label) {
		this.label = label;
	}

	
	public Vertex getPrevious() {
		return previous;
	}

	
	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	
	public double getMinDistance() {
		return sourceDistance;
	}

	
	public void setMinDistance(double minDistance) {
		this.sourceDistance = minDistance;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(sourceDistance, other.sourceDistance);
	}
	
	@Override
	public boolean equals(Object that) {
		if(this == that) return true;
		if(!(that instanceof Vertex)) return false;
		
		Vertex other = (Vertex) that;
		
		return this.id == other.id && this.label.equals(other.label);
	}

}