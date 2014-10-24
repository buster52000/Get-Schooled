package pburg.tsa.getSchooled.world;

import pburg.tsa.getSchooled.Objects.PhysicsObject;

public class WorldObject {

	private PhysicsObject physObj;
	private int x, y, id;
	
	/**
	 * Creates a new WorldObject with the given {@link PhysicsObject}
	 * 
	 * @param physObj
	 */
	public WorldObject(PhysicsObject physObj) {
		this.physObj = physObj;
		x = 0;
		y = 0;
		id = -1;
	}
	
	public WorldObject(PhysicsObject physObj, int x, int y) {
		this.physObj = physObj;
		this.x = x;
		this.y = y;
		id = -1;
	}
	
	/**
	 * returns the PhysicsObject
	 * 
	 * @return physObj
	 */
	public PhysicsObject getPhysicsObject() {
		return physObj;
	}
	
	/**
	 * Gets the x coordinate of the world object
	 * 
	 * @return x - the x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x coordinate of the world object
	 * 
	 * @param x - the x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Gets the y coordinate of the world object
	 * 
	 * @return y - the y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the y coordinate of the world object
	 * 
	 * @param y - the y coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the ID of the object if it has not already been set.
	 * If the ID has been set it will throw an exception
	 * 
	 * @param id - the id to set
	 * @throws Exception - thrown if id has already been set
	 */
	public void setID(int id) throws IllegalStateException {
		if(this.id == -1)
			this.id = id;
		else
			throw new IllegalStateException("The id has already been set for this object");
	}
	
	/**
	 * Gets the id of the object
	 * 
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	
}
