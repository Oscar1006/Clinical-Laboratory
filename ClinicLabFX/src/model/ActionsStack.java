package model;

public class ActionsStack {

    //the reference to the first node is enough
    //to characterize a list
    private Action first;

    public ActionsStack() {
        first = null;
    }

    public void insert(String info) {
        
        Action newAct = new Action(info, null);
        
        newAct.setNext(first);
        
        first = newAct;
    }

    //extracts the first element
    public Action poll() {
        Action top = null;

        //if first is null, you cannot call its methods
        if (!isEmpty()) {
            top = first;
            first = first.getNext();
        }

        //if the list is empty, null should be returned, but this is
        //the default value of "out"
        return top;
    }

    public boolean isEmpty() {
        if (first == null)
            return true;
        else 
            return false;
    }
}