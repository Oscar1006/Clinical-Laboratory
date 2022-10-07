package data_strutures;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.Element;
import data_structures.Pile;
import exception.PileException;


public class PileTest {
	
	private Pile<String> pile;
	private Pile<Object> pile2;
	
	public void setUpStage1() {
		pile = new Pile<>();
	}
	
	public void setUpStage2() throws PileException {
		pile = new Pile<>();
		pile.push("Hello");
	}
	
	public void setUpStage3() throws PileException {
		pile = new Pile<>();
		pile.push("Hello");
		pile.pop();
		
	}
	public void setUpStage4() throws PileException {
		pile = new Pile<>();
		pile.push("Hello");
		pile.push("World");
	}
	
	private void setUpStage5() {
		pile = new Pile<String>();
		
	}
	
	@Test
	public void testIsEmpty() {
		setUpStage1();
		
		assertTrue(pile.isEmpty());
	}
	
	@Test
	public void testIsEmpty2() {
		try {
			setUpStage2();
		} catch (PileException e) {
			fail("No added");
		}
		
		assertTrue(!pile.isEmpty());
	}
	
	@Test
	public void testIsEmpty3() {
		try {
			setUpStage3();
		} catch (PileException e) {
			fail("No added or not popped");
		}
		
		assertTrue(pile.isEmpty());
	}
	
	@Test
	public void testPop() {
		setUpStage1();
	
		try {
			pile.pop();
			fail("Should throw exception");
		} catch (PileException e) {
			System.out.println("Nice: " + e.getMessage());
		}
		assertTrue(pile.isEmpty());
	}
	
	@Test
	public void testPop2() {
		Element<String> expected = new Element<>();
		try {
			setUpStage2();
			expected = pile.pop();
		} catch (PileException e) {
			System.out.println(e.getMessage());
			fail("Should not throw exception");
		}
		assertTrue(pile.isEmpty());
		assertSame("Hello", expected.getInfo());
	}
	
	@Test
	public void testPop3() {
		Element<String> expected = new Element<>();
		try {
			setUpStage4();
			expected = pile.pop();
		} catch (PileException e) {
			System.out.println(e.getMessage());
			fail("Should not throw exception");
		}
		try {
			assertSame("Hello", pile.pop().getInfo());
		} catch (PileException e) {
			e.printStackTrace();
		}
		assertSame("World", expected.getInfo());
		assertSame("Hello", expected.getNext().getInfo());
	}

	@Test
	public void testPush() {
		setUpStage1();
	
		try {
			pile.push("Nice job");
		} catch (PileException e) {
			System.out.println( e.getMessage());
			fail("Should not throw exception");
		}
		assertTrue(!pile.isEmpty());
		try {
			assertEquals("Wrong", "Nice job", pile.top().getInfo());
		} catch (PileException e) {
			fail("Should not throw exception");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPush2() {
		setUpStage5();
	
		try {
			pile2.push(1);
			pile2.push("One");
			fail("Should  throw exception");
		} catch (Exception e) {
			System.out.println( e.getMessage());
		}
		assertTrue(pile.isEmpty());
		
	}
	
	@Test
	public void testPush3() {
		setUpStage5();
	
		try {
			pile2.push(1);
			pile2.push("One");
			fail("Should  throw exception");
		} catch (Exception e) {
			System.out.println("Nice: " + e.getMessage());
		}
		assertTrue(pile.isEmpty());
		
	}
	
	@Test
	public void testTop() {
		setUpStage1();
	
		try {
			pile.top();
			fail("Should  throw exception");
		} catch (Exception e) {
			System.out.println("Nice: " + e.getMessage());
		}
		assertTrue(pile.isEmpty());
	}
	
	@Test
	public void testTop2() {
		setUpStage1();
		String expected = "";
		try {
			pile.push("Hello");
			expected= pile.top().getInfo();
		} catch (Exception e) {
			fail("Should  throw exception");
			System.out.println(e.getMessage());
		}
		assertEquals("Hello", expected);
	}

	@Test
	public void testTop3() {
		try {
			setUpStage2();
			pile.pop();
			pile.top();
			fail("Should throw exception");
		} catch (PileException pe) {
			System.out.println("Nice: " + pe.getMessage());
			
		}
		assertTrue(pile.isEmpty());
		
	}
	
}
