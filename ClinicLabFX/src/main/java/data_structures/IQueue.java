package data_structures;

import exception.StructureException;

public interface IQueue<T> {
	
	public boolean isEmpty();
	
	public Element<T> front() throws StructureException;
	
	public Element<T> dequeue() throws StructureException;
	
	public void enqueue(T data) throws StructureException;

}
