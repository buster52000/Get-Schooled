package pburg.tsa.testing;

import java.awt.geom.Rectangle2D;

import org.junit.Test;

import pburg.tsa.getSchooled.Objects.PhysicsObject;
import pburg.tsa.getSchooled.physics.PhysCollision;
import pburg.tsa.getSchooled.world.WorldObject;
import static org.junit.Assert.*;

public class CollisionTests {
	
	@Test
	public void testIsColliding() {
		WorldObject obj1 = new WorldObject(new PhysicsObject(null, true, true, 1, 0, new Rectangle2D.Double(10, 10, 50, 50), 0));
		WorldObject obj2 = new WorldObject(new PhysicsObject(null, true, true, 1, 0, new Rectangle2D.Double(40,40,50,50), 1));
		assertTrue(PhysCollision.checkCollision(obj1, obj2));
	}
	
	@Test
	public void testNotColliding() {
		WorldObject obj1 = new WorldObject(new PhysicsObject(null, true, true, 1, 0, new Rectangle2D.Double(10, 10, 50, 50), 0));
		WorldObject obj3 = new WorldObject(new PhysicsObject(null, true, true, 1, 0, new Rectangle2D.Double(30,70,50,50), 1));
		assertFalse(PhysCollision.checkCollision(obj1, obj3));
	}
	
}
