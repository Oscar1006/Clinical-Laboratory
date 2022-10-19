package model;

import java.util.Calendar;
import data_structures.Pile;
import data_structures.HashTable;
import data_structures.PriorityQueue;
import data_structures.Queue;
import data_structures.Element;
import data_structures.HashNode;
import exception.StructureException;

public class ClinicLab {
	
	private Pile<Action> actionsToUndo;
	
	
	private HashTable<String, Patient> patients; 
	
	private PriorityQueue waitList;
	
	private Queue<Patient> normalPatients;
	
	public ClinicLab() {
		actionsToUndo = new Pile<>();
		
		patients = new HashTable<>();
		
		//Priority patients
		waitList = new PriorityQueue();
		
		normalPatients = new Queue<>();
	}
	
	public HashTable<String, Patient> getPatients() {
		return patients;
	}
	
	public PriorityQueue getWaitList() {
		return waitList;
	}
	
	public Queue<Patient> getNormalWaitList() {
		return normalPatients;
	}
	public void setNormalPatients(Queue<Patient> normalPatients) {
		this.normalPatients = normalPatients;
	}
	
	

	//Jun was here
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen, Calendar entryTime) throws StructureException 
	{
		Patient patient = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen, entryTime);
		
		patients.insert(id, patient);
		
		if (pregnant | several | disabled | oxigen ) {
			waitList.insert(patient);			
		}else {
			normalPatients.enqueue(patient);
		}

		Action act = new Action(Action.Type.ADD, patient);
		addActionToUndo(act);
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
			patients.delete(toUndo.getPatient().getId());
			break;
		case DELETE:

		default:
			break;
		}
		
	}

}
