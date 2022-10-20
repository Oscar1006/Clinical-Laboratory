package data_strutures;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.PriorityQueue;
import model.Patient;

public class PriorityQueueTest {
	
	PriorityQueue<Patient> pq;
	
	public void setUp1() {
		pq= new PriorityQueue<>();
	}
	public void setUp2() {
		pq= new PriorityQueue<>();
		pq.insert(1, new Patient("a", "a", 30, "m", "m", true, false, false, false));
		pq.insert(2, new Patient("b", "b", 30, "m", "m", true, true, false, false));
		pq.insert(3, new Patient("c", "c", 30, "m", "m", true, true, true, false));
	}
	
	

	@Test
	public void testExtractMaximum() {
		setUp2();
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, true));
		pq.insert(5, new Patient("e", "c", 30, "m", "m", true, true, true, true));
		pq.insert(6, new Patient("f", "c", 30, "m", "m", true, true, true, true));
		pq.insert(7, new Patient("h", "c", 30, "m", "m", true, true, true, true));
		pq.insert(8, new Patient("i", "c", 30, "m", "m", true, true, true, true));
		assertEquals("No estas insertando bien",4,pq.extractMaximum().getEntery());
		assertEquals("No estas insertando bien",5,pq.extractMaximum().getEntery());
		assertEquals("No estas insertando bien",6,pq.extractMaximum().getEntery());
		assertEquals("No estas insertando bien",7,pq.extractMaximum().getEntery());
		
	}

	@Test
	public void testInsert() {
		setUp2();
		
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, true));
		pq.insert(5, new Patient("e", "c", 30, "m", "m", true, true, true, true));
		pq.insert(6, new Patient("f", "c", 30, "m", "m", true, true, true, true));
		pq.insert(7, new Patient("h", "c", 30, "m", "m", true, true, true, true));
		pq.insert(8, new Patient("i", "c", 30, "m", "m", true, true, true, true));
		
		assertEquals("No estas insertando bien","d",((Patient)pq.extractMaximum().getPatient()).getName());
		
	}
	/*
	@Test
	public void testIncrease_Key() {
		fail("Not yet implemented");
	}*/

}
