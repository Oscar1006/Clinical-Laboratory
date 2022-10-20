package data_structures;

import java.util.ArrayList;
import model.Patient;

public class PriorityQueue<K> implements IPriorityQueue<K> {
	
	private ArrayList<PriorityNode<K>> list;
	
	public PriorityQueue() {
		list= new ArrayList<>();
	}
	
	@Override
	public PriorityNode<K> maximum(){
		return list.get(0);
	}
	
	@Override
	public PriorityNode<K> extractMaximum(){
		if(list.get(0)==null)
			return null;
		else if(list.size() == 1){
			return list.remove(0);
		}
		
		else {
			PriorityNode<K> max = list.get(0);
 			list.set(0, list.get(list.size()-1));
 			list.set(list.size()-1, max);
 			max=list.remove(list.size()-1);
			maxHeapify(0);
			return max;
		}
	}
	
	private void changeIncElement(int i) {
		if(list.size()>1) {
			int parent=(i/2)-1;
			while((i-1>0) && ((Patient) list.get(parent).getPatient()).compareTo((Patient) list.get(i-1).getPatient())<0) {
				PriorityNode<K> temp= list.get(parent);
				list.set(parent, list.get(i-1));
				list.set(i-1, temp);

				i=parent;
			}
		}
		
	}
	
	@Override
	public boolean insert(int i, K p) {
		if(list.add(new PriorityNode<K>(i, p))) {
			changeIncElement(list.size());
			
			
			
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
			((Patient) list.get(i).getPatient()).setWaitingTime(key);
			changeIncElement(i);
			return true;
		}
			
	}
	
	private void maxHeapify(int i) {
		int r= i*2+1;
		int l= i*2;
		int largest=i;
		if(l<=list.size()-1 && ((Patient) list.get(i).getPatient()).compareTo((Patient) list.get(l).getPatient())<0) 
			largest=l;
		else if(((Patient) list.get(i).getPatient()).compareTo((Patient) list.get(l).getPatient())==0 && list.get(i).getEntery()-list.get(l).getEntery()>0)
			largest=l;
		
		if(r<=list.size()-1 && ((Patient) list.get(largest).getPatient()).compareTo((Patient) list.get(r).getPatient())<0)
			largest=r;
		else if(((Patient) list.get(i).getPatient()).compareTo((Patient) list.get(r).getPatient())==0 && list.get(i).getEntery()-list.get(r).getEntery()>0)
			largest=l;
		
		if(largest!=i) {
			PriorityNode<K> temp=list.get(i);
			list.set(i, list.get(largest));
			list.set(largest, temp);
			maxHeapify(largest);
		}
	}
	
	public ArrayList<PriorityNode<K>> toArray() {
		return list;
	}
}
