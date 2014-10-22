package pburg.tsa.testing;

import org.junit.Test;

import pburg.tsa.getSchooled.physics.PhysCollision;
import static org.junit.Assert.*;

public class CollisionTests {
	
	@Test
	public void test1() {
		assertTrue(PhysCollision.checkCollision(null, null));
	}
	
}
