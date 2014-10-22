package pburg.tsa.getSchooled.physics;

import java.awt.geom.Rectangle2D;

import pburg.tsa.getSchooled.Objects.PhysicsObject;
import pburg.tsa.getSchooled.world.WorldObject;

public class PhysCollision {

	public static boolean checkGeneralCollision(Rectangle2D r1, Rectangle2D r2) {
		if (r1.getMaxX() < r2.getMinX() || r1.getMinX() > r2.getMaxX())
			return false;
		if (r1.getMaxY() < r2.getMinY() || r1.getMinY() > r2.getMaxY())
			return false;
		return true;
	}

	public static boolean advancedObjectCollision(WorldObject o1, WorldObject o2) {
		return true;
	}

	public static boolean checkCollision(WorldObject o1, WorldObject o2) {
		PhysicsObject p1 = o1.getPhysicsObject();
		PhysicsObject p2 = o2.getPhysicsObject();
		if (!p1.isSolid() || !p2.isSolid() || !checkGeneralCollision(p1.getCollisionRectangle(o1.getX(), o1.getY()), p2.getCollisionRectangle(o2.getX(), o2.getY())))
			return false;
		return advancedObjectCollision(o1, o2);
	}

}
