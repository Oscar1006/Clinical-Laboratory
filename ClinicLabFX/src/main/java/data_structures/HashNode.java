package data_structures;

public class HashNode<K, V> {

    private K key;
    private V value;

    private HashNode<K, V> previous;
    private HashNode<K, V> next;

    public HashNode(K newKey, V newValue) {
        key = newKey;
        value = newValue;
        previous = null;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(HashNode<K, V> previous) {
        this.previous = previous;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    public int numberOfElements() {
        if ( next == null )
            return 1;
        else
            return 1 + next.numberOfElements();
    }

}
