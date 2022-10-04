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
	
	
	
	public Patient(String name, String id, int age, String address, String email, boolean pregnant, 
			boolean severalDesease, boolean disabled, boolean oxigenDependent) {
		
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.address = address;
		this.email = email;
		this.pregnant = pregnant;
		this.severalDesease = severalDesease;
		this.disabled = disabled;
		this.oxigenDependent = oxigenDependent;
		
		waitingTime = 0;
	}



	public int calcPriority() {
		return 1;
	}



	public String getName() {
		return name;
	}



	public String getId() {
		return id;
	}



	public int getAge() {
		return age;
	}



	public String getAddress() {
		return address;
	}



	public String getEmail() {
		return email;
	}



	public boolean isPregnant() {
		return pregnant;
	}



	public boolean isSeveralDesease() {
		return severalDesease;
	}



	public boolean isDisabled() {
		return disabled;
	}



	public boolean isOxigenDependent() {
		return oxigenDependent;
	}



	public int getWaitingTime() {
		return waitingTime;
	}
}
