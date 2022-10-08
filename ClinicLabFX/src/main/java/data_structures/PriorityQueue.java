package data_structures;

import java.util.ArrayList;

import model.Patient;


public class PriorityQueue implements IPriorityQueue {
	
	private ArrayList<Patient> list;
	
	public PriorityQueue() {
		list= new ArrayList<>();
	}
	
	@Override
	public Patient maximum(){
		return list.get(0);
	}
	
	@Override
	public Patient extractMaximum(){
		Patient exit=list.remove(0);
		maxHeapify(0);
		return exit;
		
	}
	
	private void changeIncElement(int i) {
		if(list.size()>1) {
			int parent=i/2;
			while(i>1 || list.get(parent).compareTo(list.get(i))>0) {
				Patient temp= list.get(parent);
				list.set(parent, list.get(i));
				list.set(i, temp);

				i=parent;
				parent=i/2;
			}
		}
		
	}
	
	@Override
	public boolean insert(Patient p) {
		if(list.add(p)) {
			changeIncElement(list.size()-1);
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean increase_Key(int i, int key) {
		if(key<0) {
			return false;
		}
		else {
			list.get(i).setWaitingTime(key);
			changeIncElement(i);
			return true;
		}
			
	}
	
	private void maxHeapify(int i) {
		int r= i*2+1;
		int l= i*2;
		int shortest=i;
		if(l<=list.size()-1 && list.get(i).compareTo(list.get(l))>0) 
			shortest=l;
		
		if(r<=list.size()-1 && list.get(shortest).compareTo(list.get(r))>0)
			shortest=r;
		
		if(shortest!=i) {
			Patient temp=list.get(i);
			list.set(i, list.get(shortest));
			list.set(shortest, temp);
			maxHeapify(shortest);
		}
	}
	
}
