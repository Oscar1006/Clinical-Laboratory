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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element<T> dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(Element<T> element) {
		
	}

}
