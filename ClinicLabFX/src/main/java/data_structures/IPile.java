package data_structures;

public interface IPile<T> {
	
	public void insert(T data);
	
	public Element<T> poll();
	
	public boolean isEmpty();

}
