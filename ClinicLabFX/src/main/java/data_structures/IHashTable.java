package data_structures;

public interface IHashTable<K, V> {
	
    public boolean insert(K key, V value);

    public HashNode<K, V> search(K key);

    public boolean delete(K key);

}
