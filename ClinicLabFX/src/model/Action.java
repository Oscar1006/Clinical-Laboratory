package model;

public class Action {
	enum Type { ADD, DELETE}

	
	private Type type;
	private Patient patient;
	
	public Action(Type type, Patient patient) {
		
		this.type = type;
		this.patient = patient;
	}

	public Type getType() {
		return type;
	}

	public Patient getPatient() {
		return patient;
	}
	
}
