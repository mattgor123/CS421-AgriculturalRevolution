package rev.model.attributes;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MutablePositionTest extends TestCase {

	private MutablePosition mp1;
	private MutablePosition mp2;
	@Before
	public void setUp() throws Exception {
		super.setUp();
		mp1 = new MutablePosition(0,1);
		mp2 = new MutablePosition(6,3);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		mp1 = null;
		mp2 = null;
	}


	@Test
	public void testGetX() {
		assertEquals("mp1 getX should be 0",mp1.getX(),0);
		assertEquals("mp2 getX should be 6",mp2.getX(),6);
	}
	
	@Test
	public void testSetX() {
		mp1.setX(5);
		mp2.setX(9);
		assertEquals("mp1 getX should be 5",mp1.getX(),5);
		assertEquals("mp2 getX should be 9",mp2.getX(),9);
	}

	@Test
	public void testGetY() {
		assertEquals("mp1 getY should be 1",mp1.getY(),1);
		assertEquals("mp2 getY should be 3",mp2.getY(),3);
	}
	
	@Test
	public void testSetY() {
		mp1.setY(12);
		mp2.setY(8);
		assertEquals("mp1 getY should be 12",mp1.getY(),12);
		assertEquals("mp2 getY should be 8",mp2.getY(),8);
	}
	
	@Test
	public void testTranslate() {
		mp1.translate(1, 1);
		assertEquals("mp1 getX should be 1",mp1.getX(),1);
		assertEquals("mp1 getY should be 2",mp1.getY(),2);
		
	}
}
