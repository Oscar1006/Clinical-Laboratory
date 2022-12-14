package data_structures;

public class Element <T> {

    private T info;
    private Element<T> next;

    public Element() {
        info = null;
        next = null;
    }
    
    public Element( T data ) {
    	info = data;
    	next = null;
    }

    public Element(T info, Element<T> a) {
        this.info = info;
        this.next = a;
    }


    public void setNext(Element<T> a){
        next = a;
    }

    public T getInfo(){
        return info;
    }

    public Element<T> getNext(){
        return next;
    }
}