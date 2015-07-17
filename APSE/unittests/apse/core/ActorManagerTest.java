package apse.core;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * Tests the ActorManager class
 * @author Matt Gorelik
 *
 */
public class ActorManagerTest extends TestCase {

	private ActorManager testManager;
	private Person testP1;
	private Person testP2;
	private Person testP3;
	private Person testP4;
	private Person testP5;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		testManager = new ActorManager();
		testP1 = new Person("PERSON");
		testP2 = new Person("PERSON");
		testP3 = new Person("OTHERPERSONTYPE");
		testP4 = new Person("OTHERPERSONTYPE");
		testP5 = new Person("ANOTHEROTHERPERSONTYPE");
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testManager = null;
		testP1 = null;
		testP2 = null;
		testP3 = null;
		testP4 = null;
		testP5 = null;
	}

	@Test
	public final void testDeclareActorType() {
		boolean caught = false;
		try {
			testManager.insertActor(testP1);
			testManager.update(null);	
		}
		catch (NullPointerException e)
		{
			caught = true;
		}
		assertTrue(caught);
		
		caught = false;
		try {
			testManager.declareActorType("PERSON");
			testManager.update(null);
			testManager.insertActor(testP1);
			testManager.update(null);			
		}
		catch (NullPointerException e)
		{
			caught = true;
		}
		
		assertFalse(caught);
	}

	@Test
	public final void testInsertActor() {
		testManager.declareActorType("PERSON");
		testManager.declareActorType("OTHERPERSONTYPE");
		testManager.update(null);
		testManager.insertActor(testP1);
		testManager.insertActor(testP2);
		testManager.insertActor(testP3);
		testManager.insertActor(testP4);
		testManager.update(null);
		assertEquals("Make sure we have 2 PERSONs",testManager.getActorsOfType("PERSON").size(),2);
		assertEquals("Make sure we have 2 OTHERPERSONTYPEs",testManager.getActorsOfType("OTHERPERSONTYPE").size(),2);
		boolean caught = false;
		try {
			testManager.insertActor(testP5);
			testManager.update(null);			
		}
		catch (NullPointerException e)
		{
			caught = true;
		}
		assertTrue(caught);
		caught = false;
		testManager.declareActorType("ANOTHEROTHERPERSONTYPE");
		testManager.update(null);
		testManager.insertActor(testP5);
		assertEquals("Make sure we now have 1 ANOTHEROTHERPERSONTYPE",testManager.getActorsOfType("ANOTHEROTHERPERSONTYPE").size(),1);
	}

	@Test
	public final void testUpdate() {
		testManager.declareActorType("PERSON");
		testManager.declareActorType("OTHERPERSONTYPE");
		testManager.update(null);
		testManager.insertActor(testP1);
		testManager.update(null);
		testManager.update(null);
		testManager.insertActor(testP2);
		testManager.update(null);
		testManager.insertActor(testP3);
		testManager.update(null);
		testManager.update(null);
		testManager.insertActor(testP4);
		testManager.update(null);
		assertEquals("Make sure we have updated P1 six times",testP1.age,6);
		assertEquals("Make sure we have updated P2 four times",testP2.age,4);
		assertEquals("Make sure we have updated P3 three times",testP3.age,3);
		assertEquals("Make sure we have updated P4 once",testP4.age,1);
	}

	@Test
	public final void testRemoveFlaggedActors() {
		testManager.declareActorType("PERSON");
		testManager.declareActorType("OTHERPERSONTYPE");
		testManager.update(null);
		testP3.setFlag(true);
		testP2.setFlag(true);
		testManager.insertActor(testP1);
		testManager.insertActor(testP2);
		testManager.insertActor(testP3);
		testManager.insertActor(testP4);
		testManager.update(null);
		testManager.removeFlaggedActors();
		assertEquals("Make sure we now have 1 PERSONs",testManager.getActorsOfType("PERSON").size(),1);
		assertEquals("Make sure we now have 1 OTHERPERSONTYPEs",testManager.getActorsOfType("OTHERPERSONTYPE").size(),1);
		assertEquals("Make sure we now have the right PERSON",testManager.getActorsOfType("PERSON").get(0),testP1);
		assertEquals("Make sure we now have 1 OTHERPERSONTYPEs",testManager.getActorsOfType("OTHERPERSONTYPE").get(0),testP4);
	}
}
