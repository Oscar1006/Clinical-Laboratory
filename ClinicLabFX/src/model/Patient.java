package model;

public class Patient {
	
	private String name;
	private String id;
	
	private int age;
	
	private String address;
	private String email;
	
	private boolean pregnant;
	private boolean severalDesease;
	private boolean disabled;
	private boolean oxigenDependent;
	
	private int waitingTime;
	
	public Patient() {
	
	}
	
	public int calcPriority() {
		return 1;
	}
}
