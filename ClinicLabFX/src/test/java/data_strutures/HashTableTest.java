package data_strutures;

import model.Patient;
// import data_structures.HashNode;
import data_structures.HashTable;
import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest {

	private HashTable<String, Patient> patients;
	
	private void stage1() {
		patients = new HashTable<>();
		// HashNode<String, Patient> node = new HashNode<String, Patient>("1006048521", null);
		// System.out.println(node);
	}
	
	@Test
	void testInsert1() {
		stage1();
		patients.insert("1006048521", null);
		fail("Fallo xd");
	}

}
