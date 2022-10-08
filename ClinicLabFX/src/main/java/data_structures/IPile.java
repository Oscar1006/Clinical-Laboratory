package data_structures;

import exception.StructureException;

public interface IPile<T> {
	
	public boolean isEmpty();

	public void push(T item) throws StructureException;
	
	public Element<T> top() throws StructureException;
	
	public Element<T> pop() throws StructureException;

}
