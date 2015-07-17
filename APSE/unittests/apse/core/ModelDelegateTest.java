package apse.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;

/**
 * Tests the Model Delegate Class
 * 
 * @author Matt Gorelik
 * 
 */
public class ModelDelegateTest extends TestCase {

	private Person testActor1;
	private Person testActor2;
	private Person testActor3;
	private APSESimulationModel testModel;
	private APSEModelDelegate testDelegate;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		testActor1 = new Person("TYPE");
		testActor2 = new Person("TYPE");
		testActor3 = new Person("ANOTHERTYPE");
		testModel = new APSESimulationModel();
		testDelegate = new APSEModelDelegate();
		testModel.setModelDelegate(testDelegate);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testActor1 = null;
		testActor2 = null;
		testActor3 = null;
		testModel = null;
		testDelegate = null;
	}

	@Test
	public final void testCanInsertActorsOfDeclaredType() {
		testModel.declareActorType("TYPE");
		testDelegate.insertActor(testActor1);
		testDelegate.insertActor(testActor2);
		testModel.update();
		assertEquals(
				"Inserting a testActor using the delegate should be reflected in the model",
				testModel.getActorsOfType("TYPE").size(), 2);
		assertEquals("Make sure it's the right actor", testModel
				.getActorsOfType("TYPE").get(0), testActor1);
		testModel.declareActorType("ANOTHERTYPE");
		testDelegate.insertActor(testActor3);
		testModel.update();
		assertEquals("Make sure we can access this other type of actor",
				testModel.getActorsOfType("ANOTHERTYPE").size(), 1);
	}

	@Test
	public final void testActorUpdatesWithModel() {
		testModel.declareActorType("TYPE");
		testDelegate.insertActor(testActor1);
		testModel.update();
		testDelegate.insertActor(testActor2);
		testModel.update();
		assertEquals("The first actor should have been updated twice",
				testActor1.age, 2);
		assertEquals("The second actor should have been updated once",
				testActor2.age, 1);
	}

	@Test
	public final void testCanNotInsertActorOfUndeclaredType() {
		boolean caught = false;
		testModel.declareActorType("TYPE");
		try {
			testDelegate.insertActor(testActor3);
			testModel.update();
		} catch (NullPointerException e) {
			caught = true;
		}
		assertTrue(caught);
	}
}
