package data_structures;

import exception.PileException;

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
    public void push(T info) throws PileException{
        
        Element<T> newAct = new Element<T>(info, null);
        
        newAct.setNext(first);
        
        first = newAct;
        
        if(!newAct.equals(first)) {
        	throw new PileException();
        }
    }

    @Override
    public Element<T> top() throws PileException {
    	Element<T> top = null;

        //if first is null, you cannot call its methods
        if (!isEmpty()) {
            top = first;
        }else {
        	throw new PileException();
        }

        //if the list is empty, null should be returned, but this is
        //the default value of "out"
        return top;
    }

    //extracts the first element
    @Override
    public Element<T> pop() throws PileException {
    	Element<T> top = null;
    	
    	//if first is null, you cannot call its methods
    	if (!isEmpty()) {
    		top = first;
    		first = first.getNext();
    	}else {
    		throw new PileException();
    	}
    	//if the list is empty, null should be returned, but this is
    	//the default value of "out"
    	return top;
    }

}