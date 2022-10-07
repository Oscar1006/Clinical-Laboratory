package model;

import java.util.HashMap;
import data_structures.*;
import exception.PileException;

public class ClinicLab {

	
	Pile<Action> actionsToUndo = new Pile<>();
	
	HashMap<String, Patient> patients = new HashMap<>();
	
	public ClinicLab() {
		
	}
	
	public void addPatient(String name, String id, int age, String address, String email, 
			boolean pregnant, boolean several, boolean disabled, boolean oxigen) throws PileException {
		
		Patient p = new Patient(name, id, age, address, email, pregnant, several, disabled, oxigen);
		patients.put(id, p);
		
		Action act = new Action(Action.Type.ADD, p);
		
		addActionToUndo(act);
	}
	
	public void addActionToUndo (Action a) throws PileException {
		actionsToUndo.push(a);
	}
	
	public void undoAction () throws PileException {
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
