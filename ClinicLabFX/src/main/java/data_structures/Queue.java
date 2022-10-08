package data_structures;

import exception.StructureException;

public class Queue<T> implements IQueue<T> {
	
	private Element<T> first;
	private Element<T> last;
	
	public Queue() {
		first = null;
		last = null;
	}

	@Override
	public boolean isEmpty() {
		if( first == null && last == null )
			return true;
		else
			return false;
	}

	@Override
	public Element<T> front() throws StructureException {
		if(first == null)
			throw new StructureException();
		return first;
	}

	@Override
	public Element<T> dequeue() throws StructureException{
		Element<T> temporal = null;
		
		if( isEmpty() == false ) {
			if( first == last ) {
				temporal = first;
				first = null;
				last = null;
			} else {
				temporal = first;
				first = first.getNext();
				
			}
		}
		else
			throw new StructureException();
		
		return temporal;
	}

	@Override
	public void enqueue(T data) throws StructureException {
		Element<T> element = new Element<>(data);
		if( isEmpty() ) {
			first = element;
			last = first;
		} else {
			Element<T> temporal = first;
			
			while( temporal.getNext() != null )
				temporal = temporal.getNext();
			
			temporal.setNext(element);
			last = element;
		}
		
		if(first != element && last!= element) {
			throw new StructureException();
			
		}
	}

}
