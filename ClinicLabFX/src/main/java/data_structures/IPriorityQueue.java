package data_structures;

import model.Patient;

public interface IPriorityQueue {
 
	public Patient maximum();
	
	public Patient extractMaximum();

	public boolean insert(Patient p);

	public boolean increase_Key(int i, int key);
}

