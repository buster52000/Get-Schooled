package pburg.tsa.getSchooled.world;

import pburg.tsa.getSchooled.Objects.PhysicsObject;

public class WorldObject {

	private PhysicsObject physObj;
	private int x, y;
	
	public WorldObject(PhysicsObject physObj) {
		this.physObj = physObj;
		x = 0;
		y = 0;
	}
	
	public PhysicsObject getPhysicsObject() {
		return physObj;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
