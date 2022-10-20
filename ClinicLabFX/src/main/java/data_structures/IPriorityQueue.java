package data_structures;

public interface IPriorityQueue<K> {
	 
	public PriorityNode<K> maximum();
	
	public PriorityNode<K> extractMaximum();

	public boolean insert(int i,K p);

	public boolean increase_Key(int i, int key);
}

