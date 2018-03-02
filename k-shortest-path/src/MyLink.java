
public class MyLink {
	
	private double weight; 
	String id;
	
	public MyLink(double weight, String id) {
		this.weight = weight;
		this.id = id;
		
	}
	
	public String toString() {
		return ""+this.weight;
	}
	
	public double getWeight() {
		return this.weight;
	}

}
