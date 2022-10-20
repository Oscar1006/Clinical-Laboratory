package model;

import java.util.Calendar;
import java.util.Date;

public class Patient implements Comparable<Patient>{
	
	public static final double PRIORITY_MULTIPLIER = 10;
	
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
}
