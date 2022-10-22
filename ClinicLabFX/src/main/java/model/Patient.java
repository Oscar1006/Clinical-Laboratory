package model;


public class Patient implements Comparable<Patient>{
	
	public static final double PRIORITY_MULTIPLIER = 1.5;
	
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
		
		this.name = name;
		this.id = id;
		this.age = age;
		this.address = address;
		this.email = email;
		this.pregnant = pregnant;
		this.severalDesease = severalDesease;
		this.disabled = disabled;
		this.oxigenDependent = oxigenDependent;
		
		waitingTime = 10;
		calcPriority();
	}



	public Patient(String[] atributes) {
		this.name = atributes[0];
		this.id = atributes[1];
		this.age = Integer.parseInt(atributes[2]);
		this.address = atributes[3];
		this.email = atributes[4];
		
		if (Integer.parseInt(atributes[5]) < 4) {
			this.pregnant = true;
		}else {
			this.pregnant = false;
		}
		
		if (Integer.parseInt(atributes[6]) < 4) {
			this.severalDesease = true;
		}else {
			this.severalDesease = false;
		}
		
		if (Integer.parseInt(atributes[7]) < 4) {
			this.disabled = true;
		}else {
			this.disabled = false;
		}
		
		if (Integer.parseInt(atributes[8]) < 4) {
			this.oxigenDependent = true;
		}else {
			this.oxigenDependent = false;
		}

	}



	public void calcPriority() {
		
		if (age < 5) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}else if (age > 60) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}
		
		if (disabled) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}
		
		if (oxigenDependent) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}
		
		if (pregnant) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}
		
		if (severalDesease) {
			waitingTime *= PRIORITY_MULTIPLIER;
		}
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
	
	public void setWaitingTime(int newW) {
		waitingTime=newW;
	}



	@Override
	public int compareTo(Patient o) {

		return this.waitingTime - o.waitingTime;
	}



	@Override
	public String toString() {
		int pregnantValue = 1;
		if (pregnant) {
			pregnantValue = 7;
		}
		
		int severalDeseaseValue = 1;
		if (severalDesease) {
			severalDeseaseValue = 7;
		}
		
		int disabledValue = 1;
		if (disabled) {
			disabledValue = 7;
		}
		
		int oxigenDependentValue = 1;
		if (oxigenDependent) {
			oxigenDependentValue = 7;
		}
		return name + "," + id + "," + age + "," + address + "," + email
					+ "," + pregnantValue + "," + severalDeseaseValue 
					+ "," + disabledValue + "," + oxigenDependentValue ;
	}
}
