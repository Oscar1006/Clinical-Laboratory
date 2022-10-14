package data_structures;

public class HashTable<K, V> implements IHashTable<K, V>{
	
    private final double A = (Math.sqrt(5) - 1) / 2;

    private final int SIZE = 31;

    private HashNode<K, V>[] nodes;

    public HashTable() {
        nodes =new HashNode[SIZE];
    }

    public HashNode<K, V>[] getNodes() {
        return nodes;
    }

    public int hashFunctionByDivisionMethod(K key) {
        int values = convertStringToNumbers(key);
        return values % SIZE;
    }

    public int hashFunctionByMultiplicationMethod(K key) {
        int values = convertStringToNumbers(key);
        double expression = SIZE * ((values * A) % 1);
        return (int)expression;
    }

    public int convertStringToNumbers(K key) {
        int values = 0;
        String s = key.toString();
        for (int i = 0; i < s.length() ; i++)
            values += s.charAt(i) * (i + 1);
        return values;
    }

    @Override
    public void insert(K key, V value){
        HashNode<K, V> node = new HashNode<>(key, value);
        int hash = hashFunctionByDivisionMethod(key);
        if (nodes[hash] != null) {
            HashNode<K, V> current = nodes[hash];
            node.setNext(current);
            current.setPrevious(node);
        }
        nodes[hash] = node;
    }

    @Override
    public HashNode<K, V> search(K key) {
        HashNode<K, V> temporal = null;
        int hash = hashFunctionByDivisionMethod(key);
        if (nodes[hash] != null) {
            if (nodes[hash].getPrevious() == null && nodes[hash].getNext() == null) {
                if (nodes[hash].getKey().equals(key))
                    temporal = nodes[hash];
            } else {
                temporal = nodes[hash];
                boolean found = false;
                while( temporal != null && !found ) {
                    if (temporal.getKey().equals(key))
                        found = true;
                    else
                    	temporal = temporal.getNext();
                }
            }
        }
        return temporal;
    }

    @Override
    public boolean delete(K key) {
    	boolean deleted = false;
        int hash = hashFunctionByDivisionMethod(key);
        if (nodes[hash] != null) {
            if (nodes[hash].getNext() == null && nodes[hash].getPrevious() == null) {
                if (nodes[hash].getKey().equals(key)) {
                    nodes[hash] = null;
                    deleted = true;
                }
            } else {
                if(nodes[hash].getPrevious() == null && nodes[hash].getKey().equals(key)) {
                    nodes[hash] = nodes[hash].getNext();
                    nodes[hash].setPrevious(null);
                    deleted = true;
                } else {
                    HashNode<K, V> currentNode = nodes[hash];
                    while(currentNode.getNext() != null) {
                        if (currentNode.getKey().equals(key)) {
                            currentNode.getPrevious().setNext(currentNode.getNext());
                            currentNode.getNext().setPrevious(currentNode.getPrevious());
                            deleted = true;
                        }
                        currentNode = currentNode.getNext();
                    }
                    if ( currentNode.getKey().equals(key) ) {
                        currentNode.getPrevious().setNext(null);
                        deleted = true;
                    }
                }
            }
        }
        return deleted;
    }

}
