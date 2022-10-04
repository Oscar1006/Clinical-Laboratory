package model;

public class Patient  implements Comparable<Patient>{
	
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
		
		waitingTime = 1;
	}



	public int calcPriority() {
		int prio = waitingTime;
		
		if (age < 5) {
			prio += 10;
		}else if (age > 60) {
			prio += 10;
		}
		
		if (disabled) {
			prio += 10;
		}
		
		if (oxigenDependent) {
			prio += 10;
		}
		
		if (pregnant) {
			prio += 10;
		}
		
		if (severalDesease) {
			prio += 10;
		}
		
		
		return prio;
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



	@Override
	public int compareTo(Patient o) {

		return this.calcPriority() - o.calcPriority();
	}
}
