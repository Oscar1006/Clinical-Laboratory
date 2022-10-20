package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import data_structures.Pile;
import data_structures.PriorityNode;
import data_structures.HashTable;
import data_structures.PriorityQueue;
import data_structures.Queue;
import data_structures.Element;
import data_structures.HashNode;
import exception.StructureException;

public class ClinicLab {
	
	private Pile<Action> actionsToUndo;
	
	
	private HashTable<String, Patient> patients; 
	
	private PriorityQueue<Patient> waitList;
	
	private Queue<Patient> normalPatients;
	
	private int count;
	
	private int lastTurn;
	
	public ClinicLab() {
		actionsToUndo = new Pile<>();
		
		
		patients = new HashTable<>();
		/*
		try {
			chargeDoc();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}*/
		
		//Priority patients
		waitList = new PriorityQueue<>();
		
		normalPatients = new Queue<>();
		
		count=1;
		lastTurn=0;
	}
	
	public HashTable<String, Patient> getPatients() {
		return patients;
	}
	
	public PriorityQueue<Patient> getWaitList() {
		return waitList;
	}
	
	public Queue<Patient> getNormalWaitList() {
		return normalPatients;
	}
	public void setNormalPatients(Queue<Patient> normalPatients) {
		this.normalPatients = normalPatients;
	}
	
	public void setWaitList(PriorityQueue<Patient> waitList) {
		this.waitList = waitList;
	}
	
	

	//Jun was here
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen) throws StructureException, NullPointerException, IOException 
	{
		Patient patient = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen);
		
		//patients.insert(id, patient);
		
		//saveDoc();
		
		if (pregnant | several | disabled | oxigen ) {
			waitList.insert(count, patient);
			count++;
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
			waitList.insert(lastTurn, toUndo.getPatient());
		default:
			break;
		}
		
	}
	
	public void saveDoc() throws IOException,NullPointerException {
		File file= new File(".\\files\\storage.data");
		FileOutputStream fos= new FileOutputStream(file);
		ObjectOutputStream oos= new ObjectOutputStream(fos);
		oos.writeObject(patients.getNodes());
		
		oos.close();
		fos.close();
		
	}
	public void chargeDoc()throws IOException, ClassNotFoundException {
		File file= new File(".\\files\\storage.data");
		FileInputStream fos= new FileInputStream(file);
		ObjectInputStream oos= new ObjectInputStream(fos);
		
		patients=(HashTable<String, Patient>) oos.readObject();
		
		oos.close();
		fos.close();
	}
	
	public void extractFromPQ() throws StructureException {
		PriorityNode<Patient> elim=waitList.extractMaximum();
		addActionToUndo(new Action(Action.Type.DELETE, elim.getPatient()));
		lastTurn=elim.getEntery();
	}

}
