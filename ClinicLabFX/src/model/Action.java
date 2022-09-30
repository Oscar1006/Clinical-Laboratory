package model;
public class Action {

    private String info;
    private Action next;

    public Action() {
        info = null;
        next = null;
    }

    public Action(String info, Action a) {
        this.info = info;
        this.next = a;
    }


    public void setNext(Action a){
        next = a;
    }

    public Object getInfo(){
        return info;
    }

    public Action getNext(){
        return next;
    }
}