package data_structures;

import exception.*;

public interface IPriorityQueue<K> {
	 
	public PriorityNode<K> maximum();
	
	public PriorityNode<K> extractMaximum() throws StructureException;

	public boolean insert(int i,K p);

	public void increase_Key(int i, int key) throws KeySmallerException, StructureException, IndexOutOfBoundsException;
}

