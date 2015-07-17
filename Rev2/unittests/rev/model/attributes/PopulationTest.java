package rev.model.attributes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class PopulationTest extends TestCase {
	private Population p1;

	@Before
	public void setUp() throws Exception {
		p1 = new Population(1000);
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
	}

	@Test
	public void testGetPopulation() {
		assertEquals("p1 getPopulation should be 1000",p1.getPopulation(),1000);
	}

	@Test
	public void testSetPopulation() {
		p1.setPopulation(3333);
		assertEquals("p1 getPopulation should be 3333", p1.getPopulation(),3333);
	}

	@Test
	public void testAddPopulation() {
		p1.addPopulation(500);
		assertEquals("p1 getPopulation should be 1500", p1.getPopulation(),1500);
	}

	@Test
	public void testRemovePopulation() {
		p1.removePopulation(525);
		assertEquals("p1 getPopulation should be 475", p1.getPopulation(),475);
		p1.removePopulation(1000);
		assertEquals("p1 getPopulation should be 0", p1.getPopulation(),0);
	}

}
