package data_structures;

public class PriorityNode<K> {
	
	int entery;
	K p;
	public PriorityNode(int entery, K p) {
		this.entery = entery;
		this.p=p;
	}
	public int getEntery() {
		return entery;
	}
	public K getPatient() {
		return p;
	}
	
}
