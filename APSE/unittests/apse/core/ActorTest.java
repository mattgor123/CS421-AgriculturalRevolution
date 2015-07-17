/**
 * 
 */
package apse.core;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * @author Matt Gorelik
 *
 */
public class ActorTest extends TestCase {

	private Person testActor1;
	private Person testActor2;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		testActor1 = new Person("TEST");
		testActor2 = new Person("TEST2");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		testActor1 = null;
		testActor2 = null;
	}

	/**
	 * Test method for {@link apse.core.APSEActor#getType()}.
	 */
	@Test
	public final void testGetType() {
		assertEquals("testActor1 should be of type TEST",testActor1.getType(),"TEST");
		assertEquals("testActor2 should be of type TEST2",testActor2.getType(),"TEST2");
	}

	/**
	 * Test method for {@link apse.core.APSEActor#setFlag(boolean)}.
	 * Test method for {@link apse.core.APSEActor#isFlagged()}.
	 */
	@Test
	public final void testSetAndIsFlag() {
		assertEquals("testActor1 should not have the flag set at first",testActor1.isFlagged(),false);
		testActor1.setFlag(true);
		assertEquals("testActor1 should not have set flag true", testActor1.isFlagged(),true);
	}
	
}
