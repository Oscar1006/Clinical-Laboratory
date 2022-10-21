package data_strutures;

import model.Patient;
import data_structures.HashNode;
import data_structures.HashTable;
import static org.junit.Assert.*;
<<<<<<< HEAD
import java.util.Calendar;
=======
>>>>>>> 891e99e1a5de896426d6f34ef4e2b1f23cc3fe90

import org.junit.Test;

public class HashTableTest {

	private HashTable<String, Patient> patients;
	
	private HashTable<String, String> randomNames;
	
	private void stage1() {
		patients = new HashTable<>();
	}
	
	private void stage2() {
		patients = new HashTable<>();
<<<<<<< HEAD
		patients.insert("9334234323", new Patient("Jun", "9334234323", 21, "Candelaria", "david_jhun@outlook.es", false, false, false, false, Calendar.getInstance())); 
		patients.insert("1234567890", new Patient("Pepe", "123456789", 30, "Cali", "pepe@gmail.com", false, true, false, true, Calendar.getInstance()));
		patients.insert("1122334455", new Patient("Cristian", "1122334455", 18, "Pasto", "cristian@outlook.com", false, false, false, true, Calendar.getInstance()));
		patients.insert("5566778899", new Patient("Jose", "5566778899", 40, "Bogota", "jose@gmail.com", false, false, false, false, Calendar.getInstance()));
		patients.insert("1011121314", new Patient("Luigi", "1011121314", 25, "Santander", "luigi@hotmail.com", false, false, false, true, Calendar.getInstance()));
	}
	
	private void stage3() {
		randomNames = new HashTable<>();
		randomNames.insert("456", "Maria");
		randomNames.insert("789", "Gonzalo");
		randomNames.insert("mario", "Casimiro");
		randomNames.insert("luigi", "Jose");
		randomNames.insert("135", "Mario");
		randomNames.insert("pt", "Jaime");
		randomNames.insert("p1", "David");
		randomNames.insert("p2", "Valery");
		randomNames.insert("David", "Jun");
		randomNames.insert("Oclivia", "Oclivia");
		randomNames.insert("Laura", "Laura");
		randomNames.insert("Valentina", "Valentina");
		randomNames.insert("Myeong gu", "Myeong gu");
		randomNames.insert("Cubides", "cubides");
		randomNames.insert("oscar", "oscar");
		randomNames.insert("sebastian", "sebastian");
		randomNames.insert("gonzalo", "Varona");
		randomNames.insert("ramiro", "ramiro");
		randomNames.insert("luisa", "luisa");
		randomNames.insert("maine", "maine");
		randomNames.insert("lucy", "lucy");
		randomNames.insert("dorio", "dorio");
		randomNames.insert("faraday", "faraday");
		randomNames.insert("Adam", "Adam Smasher");
		randomNames.insert("cortana", "Cortana");
		randomNames.insert("117", "John");
=======
		patients.insert("1006048521", new Patient("Jun", "1006048521", 21, "Candelaria", "david_jhun@outlook.es", false, false, false, false)); 
<<<<<<< HEAD
		patients.insert("1234567890", new Patient("Pepe", "123456789", 30, "Cali", "pepe@gmail.com", false, true, false, true));
=======
		patients.insert("1234567890", new Patient("Pepe", "123456789", 30, "Cali", "pepe@gmail.com", false, true, false, true ));
>>>>>>> branch 'master' of https://github.com/Oscar1006/Clinical-Laboratory.git
		patients.insert("1122334455", new Patient("Cristian", "1122334455", 18, "Pasto", "cristian@outlook.com", false, false, false, true));
		patients.insert("5566778899", new Patient("Jose", "5566778899", 40, "Bogota", "jose@gmail.com", false, false, false, false));
		patients.insert("1011121314", new Patient("Luigi", "1011121314", 25, "Santander", "luigi@hotmail.com", false, false, false, true));
		patients.insert("1617181920", new Patient("Mario", "1617181920", 50, "Italia", "mario@gmail.com", false, false, false, false));
		patients.insert("2021222324", new Patient("Julio", "2021222324", 25, "Cesar", "julio@hotmail.com", false, true, true, true));
>>>>>>> 891e99e1a5de896426d6f34ef4e2b1f23cc3fe90
	}
	
	@Test
	public void testInsert1() {
		stage1();
		int hashIndex = patients.hashFunctionByDivisionMethod("1020304050");
		boolean inserted = patients.insert("1020304050", new Patient("Gloria", "1020304050", 34, "Medellin", "gloria@gmail.com", true, false, false, false, Calendar.getInstance()));
		
		if(inserted) 
			assertEquals("1020304050", patients.getNodes()[hashIndex].getKey());
		else
			fail("The keys are not the same.");
	}
	
	@Test
	public void testInsert2() {
		stage2();
		int hashIndex = patients.hashFunctionByDivisionMethod("2030405060");
		boolean inserted = patients.insert("2030405060", new Patient(
				"Kevin", "2030405060", 17, "Villagorgona", "Ninguno",
				false, true, false, true, Calendar.getInstance()));
		if (inserted)
			assertEquals("Kevin", patients.getNodes()[hashIndex].getValue().getName());
		else 
			fail("The hashNode was not added");	
	}
	
	@Test 
	public void testInsert3() {
		stage3();
		randomNames.insert("123", "Luigi");
		randomNames.insert("Kim", "Kim");
		randomNames.insert("rebecca", "rebecca");
		int hashIndex = randomNames.hashFunctionByDivisionMethod("pilar");
		boolean inserted = randomNames.insert("pilar", "pilar");
		
		if (randomNames.getNodes()[hashIndex].getNext() != null) {
			if( inserted ) 
				assertEquals("pilar", randomNames.getNodes()[hashIndex].getValue());
			else
				fail("The hashNode was not added");
		} else {
			fail("The hash table does not manage collisions.");
		}
			
	}
	
	@Test
	public void testSearch1() {
		stage2();
		HashNode<String, Patient> hashNode = patients.search("1011121314");
		if( hashNode != null ) {
			assertEquals("Luigi", hashNode.getValue().getName());
		} else {
			fail("The hashNode was not found.");
		}
	}
	
	@Test
	public void testSearch2() {
		stage3();
		HashNode<String, String> hashNode = randomNames.search("sebastian");
		if( hashNode != null ) {
			if( hashNode.getPrevious() != null ) {
				assertEquals("sebastian", hashNode.getValue());
			} else {
				fail("The hashNode was not in the middle of the double linked list.");
			}
		} else {
			fail("The hashNode was not found.");
		}
	}
	
	@Test
	public void testSearch3() {
		stage3();
		randomNames.insert("123", "Luigi");
		randomNames.insert("Kim", "Kim");
		randomNames.insert("rebecca", "rebecca");
		randomNames.insert("pilar", "pilar");
		randomNames.insert("Viviana", "Pera");
		randomNames.insert("Jesus", "Duvan");
		HashNode<String, String> hashNode = randomNames.search("rebecca");
		if( hashNode != null ) {
			assertEquals("rebecca", hashNode.getKey());
		} else {
			fail("The hashNode was not found.");
		}
	}
	
	@Test
	public void testDelete1() {
		stage2();
		boolean deleted = patients.delete("9334234323");
		
		if ( deleted )
			assertTrue(deleted);
		else
			fail("The hashNode was not deleted.");
	}
	
	@Test
	public void testDelete2() {
		stage3();
		randomNames.delete("ramiro");
		HashNode<String, String> node = randomNames.search("ramiro");
		
		assertNull(node);
	}
	
	@Test
	public void testDelete3() {
		stage3();
		randomNames.insert("123", "Luigi");
		randomNames.insert("Kim", "Kim");
		randomNames.insert("rebecca", "rebecca");
		randomNames.insert("pilar", "pilar");
		randomNames.insert("Viviana", "Pera");
		randomNames.insert("Jesus", "Duvan");
		
		boolean deleted = randomNames.delete("Kim");
		assertTrue(deleted);
	}

}
