package apse.core;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * Tests the ActionLayer Class
 * @author Matt Gorelik
 *
 */
public class ActionLayerTest extends TestCase {

	private Person testPerson1;
	private Person testPerson2;
	private PeopleLayer testLayer;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		testPerson1 = new Person("PERSON");
		testPerson1.age = 10;
		testPerson2 = new Person("PERSON");
		testLayer = new PeopleLayer();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testPerson1 = null;
		testPerson2 = null;
		testLayer = null;
	}

	@Test
	public final void testInsertActor() {
		testLayer.insertActor(testPerson1);
		assertEquals("Ensure we have successfully added one person",testLayer.peopleList.size(),1);
		testLayer.insertActor(testPerson2);
		assertEquals("Ensure we have successfully added two people to our PeopleLayer",testLayer.peopleList.size(),2);
	}

	@Test
	public final void testRemoveFlaggedActors() {
		testLayer.insertActor(testPerson1);
		testLayer.insertActor(testPerson2);
		assertEquals("Just to be safe, ensure adding is still working",testLayer.peopleList.size(),2);
		testPerson1.setFlag(true);
		testLayer.removeFlaggedActors();
		assertEquals("Make sure we have removed the actor from our layer",testLayer.peopleList.get(0),testPerson2);
		assertEquals("Just to be safe, somewhat redundant test",testLayer.peopleList.size(),1);
	}

}
