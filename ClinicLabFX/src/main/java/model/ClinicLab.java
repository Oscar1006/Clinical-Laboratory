package model;

import java.util.Calendar;
import java.util.HashMap;
import data_structures.*;
import exception.StructureException;

public class ClinicLab {

	
	Pile<Action> actionsToUndo = new Pile<>();
	
	HashMap<String, Patient> patients = new HashMap<>();
	
	PriorityQueue waitList= new PriorityQueue();
	
	public ClinicLab() {
		
	}
	
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen, Calendar entryTime) throws StructureException {
		
		Patient p = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen, entryTime);
		patients.put(id, p);
		waitList.insert(p);
		
		Action act = new Action(Action.Type.ADD, p);
		
		addActionToUndo(act);
	}
	
	public void addActionToUndo (Action a) throws StructureException {
		actionsToUndo.push(a);
	}
	
	public void undoAction () throws StructureException {
		Element<Action> element = actionsToUndo.pop();
		Action toUndo = (Action)element.getInfo();
		
		switch (toUndo.getType()) {
		case ADD:
			patients.remove(toUndo.getPatient().getId());
			break;

		default:
			break;
		}
		
		
	}

}
