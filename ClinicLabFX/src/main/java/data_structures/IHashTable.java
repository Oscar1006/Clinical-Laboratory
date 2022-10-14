package data_structures;

public interface IHashTable<K, V> {
	
    public void insert(K key, V value) throws Exception;

    public HashNode<K, V> search(K key);

    public boolean delete(K key);

}
