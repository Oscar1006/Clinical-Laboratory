package model;

import java.util.Calendar;
// import java.util.HashMap;
import data_structures.Pile;
import data_structures.HashTable;
import data_structures.PriorityQueue;
import data_structures.Element;
import data_structures.HashNode;
import exception.StructureException;

public class ClinicLab {
	
	private Pile<Action> actionsToUndo;
	
	// HashMap<String, Patient> patients = new HashMap<>();
	
	private HashTable<String, Patient> patients; 
	
	private PriorityQueue waitList;
	
	public ClinicLab() {
		actionsToUndo = new Pile<>();
		patients = new HashTable<>();
		waitList = new PriorityQueue();
	}
	
	public HashTable<String, Patient> getPatients() {
		return patients;
	}
	
	public PriorityQueue getWaitList() {
		return waitList;
	}
	
	/*
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen, Calendar entryTime) throws StructureException {
		
		Patient p = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen, entryTime);
		patients.put(id, p);
		waitList.insert(p);
		
		Action act = new Action(Action.Type.ADD, p);
		
		addActionToUndo(act);
	}
	*/

	//Jun was here
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen, Calendar entryTime) 
	{
		Patient patient = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen, entryTime);
		patients.insert(id, patient);
		waitList.insert(patient);

		// Action act = new Action(Action.Type.ADD, patient);
		// addActionToUndo(act);
	}
	
	public Patient searchPatient( String id ) {
		HashNode<String, Patient> myNode = patients.search(id);
		if( myNode != null ) 
			return myNode.getValue();
		 else 
			return null;
	}
	
	public boolean deletePatient( String id ) {
		return patients.delete(id);
	}
	
	public void addActionToUndo (Action a) throws StructureException {
		actionsToUndo.push(a);
	}
	
	public void undoAction () throws StructureException {
		Element<Action> element = actionsToUndo.pop();
		Action toUndo = (Action)element.getInfo();
		
		switch (toUndo.getType()) {
		case ADD:
			// patients.remove(toUndo.getPatient().getId());
			break;

		default:
			break;
		}
		
		
	}

}
