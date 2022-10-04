package data_structures;

public class Pile <T> implements IPile<T> {

    //the reference to the first node is enough
    //to characterize a list
    private Element<T> first;

    public Pile() {
        first = null;
    }

    @Override
    public void insert(T info) {
        
        Element<T> newAct = new Element<T>(info, null);
        
        newAct.setNext(first);
        
        first = newAct;
    }

    //extracts the first element
    @Override
    public Element<T> poll() {
        Element<T> top = null;

        //if first is null, you cannot call its methods
        if (!isEmpty()) {
            top = first;
            first = first.getNext();
        }

        //if the list is empty, null should be returned, but this is
        //the default value of "out"
        return top;
    }

    @Override
    public boolean isEmpty() {
        if (first == null)
            return true;
        else 
            return false;
    }
}