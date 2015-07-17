package apse.core;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * Tests the LayerManager class
 * @author Matt Gorelik
 *
 */
public class LayerManagerTest extends TestCase {

	private LayerManager testManager;
	private PeopleLayer testLayer1;
	private PeopleLayer testLayer2;
	private Person testP1;
	private Person testP2;
	private Person testP3;
	private Person testP4;
	private Person testP5;;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		testManager = new LayerManager();
		testLayer1 = new PeopleLayer();
		testLayer2 = new PeopleLayer();
		testP1 = new Person("PERSON");
		testP2 = new Person("PERSON");
		testP3 = new Person("OTHERPERSONTYPE");
		testP4 = new Person("OTHERPERSONTYPE");
		testP5 = new Person("OTHERPERSONTYPE");
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testManager = null;
		testLayer1 = null;
		testLayer2 = null;
		testP1 = null;
		testP2 = null;
		testP3 = null;
		testP4 = null;
		testP5 = null;
	}

	@Test
	public final void testAddAndGetLayer() {
		testManager.addLayer(testLayer1, "Layer1");
		testManager.update(null);
		assertEquals("Ensure Layer1 has been added",testManager.getLayer("Layer1"),testLayer1);
		assertNull("Ensure Layer2 has not yet been", testManager.getLayer("Layer2"));
		testManager.addLayer(testLayer2, "Layer2");
		testManager.update(null);
		assertEquals("Ensure we have added the two layers as desired",testManager.getLayer("Layer2"),testLayer2);
		assertEquals("Make sure we haven't lost Layer1",testManager.getLayer("Layer1"),testLayer1);
	}

	@Test
	public final void testAddDefaultLayerAndInsertActorForActorType() {
		testManager.addLayer(testLayer1, "Layer1");
		testManager.addLayer(testLayer2, "Layer2");
		testManager.update(null);
		testManager.addDefaultLayerForActorType("PERSON", "Layer1");
		testManager.addDefaultLayerForActorType("OTHERPERSONTYPE", "Layer2");
		testManager.update(null);
		testManager.insertActor(testP1);
		testManager.insertActor(testP2);
		testManager.insertActor(testP3);
		testManager.insertActor(testP4);
		testManager.insertActor(testP5);
		testManager.update(null);
		assertEquals("Ensure Layer1 has two people, P1 and P2",testLayer1.peopleList.size(),2);
		assertEquals("Ensure Layer2 has three people, P3 P4 and P5",testLayer2.peopleList.size(),3);
	}

	@Test
	public final void testUpdate() {
		testManager.addLayer(testLayer1, "Layer1");
		testManager.addLayer(testLayer2, "Layer2");
		testManager.update(null);
		testManager.addDefaultLayerForActorType("PERSON", "Layer1");
		testManager.addDefaultLayerForActorType("OTHERPERSONTYPE", "Layer2");
		testManager.update(null);
		testManager.insertActor(testP1);
		testManager.update(null);
		testManager.insertActor(testP2);
		testManager.update(null);
		testManager.insertActor(testP3);
		testManager.update(null);
		assertEquals("Ensure testP1 has been updated 3 times",testP1.age,3);
		assertEquals("Ensure testLayer2 has been updated",testLayer2.updated, true);
		assertEquals("Ensure testLayer2's player (P3) has been updated once",testLayer2.peopleList.get(0).age,1);
	}

	@Test
	public final void testGetDefaultLayersForAssetType() {
		testManager.addLayer(testLayer1, "Layer1");
		testManager.addLayer(testLayer2, "Layer2");
		testManager.update(null);
		testManager.addDefaultLayerForActorType("PERSON", "Layer1");
		testManager.addDefaultLayerForActorType("OTHERPERSONTYPE", "Layer2");
		testManager.update(null);
		assertEquals("Ensure adding a PERSON goes into Layer 1",testManager.getDefaultLayersForAssetType("PERSON").get(0),testLayer1);
		assertEquals("Ensure adding an OTHERPERSONTYPE goes into Layer 2",testManager.getDefaultLayersForAssetType("OTHERPERSONTYPE").get(0),testLayer2);
	}

	@Test
	public final void testRemoveFlaggedActors() {
		assertEquals("Ensure testLayer1's had removeFlaggedActors not yet called",testLayer1.removeHasBeenCalled,false);
		assertEquals("Ensure testLayer2's had removeFlaggedActors not yet called",testLayer2.removeHasBeenCalled,false);
		testManager.addLayer(testLayer1, "Layer1");
		testManager.addLayer(testLayer2, "Layer2");
		testManager.update(null);
		testManager.removeFlaggedActors();
		assertEquals("Ensure testLayer1's had removeFlaggedActors called",testLayer1.removeHasBeenCalled,true);
		assertEquals("Ensure testLayer2's had removeFlaggedActors called",testLayer2.removeHasBeenCalled,true);		
	}

}
