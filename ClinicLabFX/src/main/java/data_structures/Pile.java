package data_structures;

import exception.StructureException;

public class Pile <T> implements IPile<T> {

    //the reference to the first node is enough
    //to characterize a list
    private Element<T> first;

    public Pile() {
        first = null;
    }

    @Override
    public boolean isEmpty() {
    	if (first == null)
    		return true;
    	else 
    		return false;
    }
    
    @Override
    public void push(T info) throws StructureException{
        
        Element<T> newAct = new Element<T>(info, null);
        
        newAct.setNext(first);
        
        first = newAct;
        
        if(!newAct.equals(first)) {
        	throw new StructureException();
        }
    }

    @Override
    public Element<T> top() throws StructureException {
    	Element<T> top = null;

        //if first is null, you cannot call its methods
        if (!isEmpty()) {
            top = first;
        }else {
        	throw new StructureException();
        }

        //if the list is empty, null should be returned, but this is
        //the default value of "out"
        return top;
    }

    //extracts the first element
    @Override
    public Element<T> pop() throws StructureException {
    	Element<T> top = null;
    	
    	//if first is null, you cannot call its methods
    	if (!isEmpty()) {
    		top = first;
    		first = first.getNext();
    	}else {
    		throw new StructureException();
    	}
    	//if the list is empty, null should be returned, but this is
    	//the default value of "out"
    	return top;
    }

}