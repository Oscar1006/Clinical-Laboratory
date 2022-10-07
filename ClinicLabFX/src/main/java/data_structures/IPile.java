package data_structures;

import exception.PileException;

public interface IPile<T> {
	
	public boolean isEmpty();

	public void push(T item) throws PileException;
	
	public Element<T> top() throws PileException;
	
	public Element<T> pop() throws PileException;

}
