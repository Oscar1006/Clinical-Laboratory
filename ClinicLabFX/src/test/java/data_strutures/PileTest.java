package data_strutures;

import static org.junit.Assert.*;

import org.junit.Test;

import data_structures.Element;
import data_structures.Pile;
import exception.PileException;


public class PileTest {
	
	private Pile<String> pile;
	
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
			System.out.println(e.getMessage());
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
			fail("Should throw exception");
		}
		assertTrue(pile.isEmpty());
		assertSame("Hello", expected.getInfo());
	}
	
	@Test
	public void testPop3() {
		Element<String> expected = new Element<>();
		try {
			setUpStage2();
			expected = pile.pop();
		} catch (PileException e) {
			System.out.println(e.getMessage());
			fail("Should throw exception");
		}
		assertTrue(pile.isEmpty());
		assertSame("Hello", expected.getInfo());
	}

}
