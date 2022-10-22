package data_strutures;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.PriorityQueue;
import exception.KeySmallerException;
import exception.StructureException;
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
	public void testExtractMaximum1() {
		setUp1();
		try {
			pq.extractMaximum();
			fail("Deberia lanzar una excepcion");
		} catch (StructureException e) {
			e.getMessage();
		}
		
	}
	
	@Test
	public void testExtractMaximum2() {
		setUp2();
		try {
			assertEquals("No esta extrayendo bien",3,pq.extractMaximum().getEntery());
		} catch (StructureException e) {
			fail("No deberia lanzar una excepcion");
		}
		
	}
	
	@Test
	public void testExtractMaximum3() {
		setUp2();
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, true));
		try {
			assertEquals("No esta extrayendo bien",4,pq.extractMaximum().getEntery());
		} catch (StructureException e) {
			fail("No deberia lanzar una excepcion");
		}
		
	}

	@Test
	public void testInsert1() {
		setUp1();
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, true));
		try {
			assertEquals("No esta insertando bien","d",((Patient)pq.extractMaximum().getPatient()).getName());
		} catch (StructureException e) {
			fail("No deberia lanzar una excepcion");
		}
		
	}
	
	@Test
	public void testInsert2() {
		setUp2();
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, false));
		try {
			pq.extractMaximum();
			assertEquals("No esta insertando bien","d",((Patient)pq.extractMaximum().getPatient()).getName());
		} catch (StructureException e) {
			fail("No deberia lanzar una excepcion");
		}
		
	}
	
	@Test
	public void testInsert3() {
		setUp2();
		
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, false, false));
		try {
			pq.extractMaximum();
			pq.extractMaximum();
			assertEquals("No esta insertando bien","d",((Patient)pq.extractMaximum().getPatient()).getName());
		} catch (StructureException e) {
			e.getMessage();
		}
		
	}
	
	@Test
	public void testIncrease_Key1() {
		setUp1();
		try {
			pq.increase_Key(1, 12);
			fail("Deberia fallar");
		} catch (IndexOutOfBoundsException | KeySmallerException | StructureException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testIncrease_Key2() {
		setUp2();
		try {
			pq.increase_Key(1, 0);
			fail("Deberia fallar");
		} catch (IndexOutOfBoundsException | KeySmallerException | StructureException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testIncrease_Key3() {
		setUp2();
		try {
			pq.increase_Key(3,50);
			assertEquals("No esta incrementando bien","b",((Patient)pq.extractMaximum().getPatient()).getName());
		} catch (IndexOutOfBoundsException | KeySmallerException | StructureException e) {
			fail("No deberia fallar");
		}
	}
	
	@Test
	public void testMaximum1() {
		setUp1();
		assertEquals("No estas trayendo el nulo",null,pq.maximum());
	}
	@Test
	public void testMaximum2() {
		setUp2();
		assertEquals("No estas trayendo el primer elemento","c",((Patient)pq.maximum().getPatient()).getName());
	}
	@Test
	public void testMaximum3() {
		setUp2();
		pq.insert(4, new Patient("d", "c", 30, "m", "m", true, true, true, true));
		assertEquals("No estas trayendo el primer elemento","d",((Patient)pq.maximum().getPatient()).getName());
	}
	
}
