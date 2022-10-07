package data_structures;

public interface IQueue<T> {
	
	public boolean isEmpty();
	
	public Element<T> front();
	
	public Element<T> dequeue();
	
	public void enqueue(T data);

}
