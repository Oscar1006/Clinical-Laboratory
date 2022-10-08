package data_strutures;

import static org.junit.Assert.*;
import org.junit.Test;
import data_structures.Queue;
import exception.StructureException;

public class QueueTest {
	
	Queue<String> qu;
	
	public void setUpStage1() {
		qu= new Queue<>();
	}
	
	public void setUpStage2() {
		setUpStage1();
		try {
			qu.enqueue("H");
			qu.enqueue("R");
		} catch (StructureException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testEnqueue1() {
		setUpStage1();
		
		try {
			qu.enqueue("S");
		} catch (StructureException e) {
			fail("No deberia lanzar esxcepcion");
		}
		
		assertFalse("El elemento no quedo insertado en la cola",qu.isEmpty());
		
	}
	
	@Test
	public void testEnqueue2() {
		setUpStage1();
		
		try {
			qu.enqueue("S");
		} catch (StructureException e) {
			fail("No deberia lanzar esxcepcion");
		}
		
		try {
			assertEquals("El elemento no quedo insertado en la cola","S", qu.front().getInfo());
		} catch (StructureException e) {
			fail("No deberia lanzar esxcepcion");
		}
		
	}
	
	@Test
	public void testEnqueue3() {
		setUpStage2();
		
		try {
			qu.enqueue("S");
		} catch (StructureException e) {
			fail("No deberia lanzar esxcepcion");
		}
		
		try {
			assertEquals("El elemento no quedo insertado en la cola","H", qu.front().getInfo());
		} catch (StructureException e) {
			fail("No deberia lanzar esxcepcion");
		}
		
	}
	
	
	
	@Test
	public void testDequeue1() {
		setUpStage1();
		
		try {
			 qu.dequeue();
			 fail("Debería lanzar la excepcion");
		} catch (StructureException e) {
			System.out.println("Good: "+e.getMessage());
		}
		assertTrue("No puede haber elementos en la cola",qu.isEmpty());
		
	}
	
	@Test
	public void testDequeue2() {
		setUpStage2();
		try {
			qu.dequeue();
		} catch (StructureException e) {
			fail("No debería lanzar la excepcion");
		}
		
		try {
			assertEquals("Debe tener el elemento con esa referencia","R", qu.front().getInfo());
		} catch (StructureException e) {
			fail("No debería lanzar la excepcion");
		}
		assertFalse("Debe tener un elemento",qu.isEmpty());
		
	}
	
	@Test
	public void testDequeue3() {
		setUpStage2();
		try {
			qu.dequeue();
			qu.dequeue();
		} catch (StructureException e) {
			fail("No debería lanzar la excepcion");
		}
		
		try {
			qu.front();
			fail("Debería lanzar la excepcion");
		} catch (StructureException e) {
			System.out.println("Good: "+e.getMessage());
		}
		assertTrue("No debe tener elementos",qu.isEmpty());
		
	}
	
	@Test
	public void testFront1() {
		setUpStage1();
		try {
			qu.front();
			fail("Debe lanzar la excepción");
		} catch (StructureException e) {
			System.out.println("Good "+e.getMessage());
		}	
		assertTrue("No puede tener elementos",qu.isEmpty());
	}
	
	@Test
	public void testFront2() {
		setUpStage2();
		try {
			assertEquals("Debe tener el elemento con esa referencia","H", qu.front().getInfo());
		} catch (StructureException e) {
			fail("No deberia tirar excepcion");
		}
	}
	
	@Test
	public void testFront3() {
		setUpStage2();
		try {
			qu.dequeue();
		} catch (StructureException e) {
			fail("No deberia tirar excepcion");
		}
		try {
			assertEquals("Debe ser el elemento con esta referencia","R", qu.front().getInfo());
		} catch (StructureException e) {
			fail("No deberia tirar excepcion");
		}
	}
	
	@Test
	public void testIsEmpty1() {
		setUpStage1();
		assertTrue("No puede haber elementos en la cola",qu.isEmpty());
		
	}
	
	@Test
	public void testIsEmpty2() {
		setUpStage2();
		assertFalse("La cola debe tener elementos",qu.isEmpty());
		
	}
	
	@Test
	public void testIsEmpty3() {
		setUpStage2();
		try {
			qu.dequeue();
			qu.dequeue();
		} catch (StructureException e) {
			fail("No deberia tirar excepcion");
		}
		assertTrue("No puede tener elementos",qu.isEmpty());
	}

}
