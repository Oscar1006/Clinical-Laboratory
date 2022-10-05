package data_structures;

public class Queue<T> implements IQueue<T> {
	
	private Element<T> first;
	private Element<T> last;
	
	public Queue() {
		first = null;
		last = null;
	}
	
	public Element<T> getFirst() {
		return first;
	}
	
	public Element<T> getLast() {
		return last;
	}

	@Override
	public boolean isEmpty() {
		if( first == null && last == null )
			return true;
		else
			return false;
	}

	@Override
	public Element<T> front() {
		return first;
	}

	@Override
	public Element<T> dequeue() {
		Element<T> temporal = null;
		if( !isEmpty() ) {
			if( first.getNext() == null ) {
				temporal = first;
				first = null;
			} else {
				temporal = first;
				first = first.getNext();
			}
		}
		return temporal;
	}

	@Override
	public void enqueue(Element<T> element) {
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
	}

}
