package data_strutures;

import model.Patient;
import data_structures.HashTable;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class HashTableTest {

	private HashTable<String, Patient> patients;
	
	private void stage1() {
		patients = new HashTable<>();
	}
	
	private void stage2() {
		patients = new HashTable<>();
		patients.insert("1006048521", new Patient("Jun", "1006048521", 21, "Candelaria", "david_jhun@outlook.es", false, false, false, false)); 
		patients.insert("1234567890", new Patient("Pepe", "123456789", 30, "Cali", "pepe@gmail.com", false, true, false, true ));
		patients.insert("1122334455", new Patient("Cristian", "1122334455", 18, "Pasto", "cristian@outlook.com", false, false, false, true));
		patients.insert("5566778899", new Patient("Jose", "5566778899", 40, "Bogota", "jose@gmail.com", false, false, false, false));
		patients.insert("1011121314", new Patient("Luigi", "1011121314", 25, "Santander", "luigi@hotmail.com", false, false, false, true));
		patients.insert("1617181920", new Patient("Mario", "1617181920", 50, "Italia", "mario@gmail.com", false, false, false, false));
		patients.insert("2021222324", new Patient("Julio", "2021222324", 25, "Cesar", "julio@hotmail.com", false, true, true, true));
	}
	
	@Test
	public void testInsert1() {
		stage1();
		patients.insert("1006048521", null);
		fail("Fallo xd");
	}
	
	@Test
	public void testInsert2() {
		stage2();
		fail("Fallo xd");
	}

}
