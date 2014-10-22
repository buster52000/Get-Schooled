package pburg.tsa.getSchooled.Objects;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class PhysicsObject {

	// whether the object will be tested for collision
	private boolean isSolid;
	// whether the object will be affected by gravity and other forces
	private boolean isStatic;
	// The gravity modifier to make it fall slower or faster (should be 1 by
	// default)
	private double gravityModifier;
	// The group that the object will test collisions with
	private int physicsGroup;
	// The rectangle parameters used for general collision checking
	private int collisionRectOffsetX, collisionRectOffsetY, collisionRectHeight, collisionRectWidth;
	// The image this object will use
	private Image img;
	// The object's ID assigned by the world
	private int objId;

	/**
	 * Creates a PhysicsObject using the image supplied and the default
	 * parameters
	 * 
	 * Default Values: isStatic - true isSolid - true gravityModifier - 1
	 * physicsGroup - 1 collisionRectangle - entire image
	 * 
	 * @param img
	 *            - the image to create the object with
	 * @param id
	 *            - the object's id that is assigned by the world
	 */
	public PhysicsObject(Image img, int id) {
		this.objId = id;
		this.img = img;
		isStatic = true;
		isSolid = true;
		gravityModifier = 1;
		physicsGroup = 0;
		collisionRectOffsetX = 0;
		collisionRectOffsetY = 0;
		collisionRectHeight = 0;
		collisionRectWidth = 0;
	}

	/**
	 * Creates a PhysicsObject using the given parameters
	 * 
	 * @param img
	 *            - the image to create the object with
	 * @param isStatic
	 *            - whether the object will be affected by gravity and other
	 *            forces
	 * @param isSolid
	 *            - whether the object will be tested for collisions
	 * @param gravityModifier
	 *            - The gravity modifier to make it fall slower or faster
	 *            (should be 1 by default)
	 * @param physicsGroup
	 *            - The group that the object will test collisions with
	 * @param collisionRect
	 *            - The rectangle used for general collision checking (the x and y coordinates should be the offset from the top left corner of the image)
	 * @param id - the object's id that is assigned by the world
	 */
	public PhysicsObject(Image img, boolean isStatic, boolean isSolid, double gravityModifier, int physicsGroup, Rectangle2D collisionRect, int id) {
		this.img = img;
		this.isStatic = isStatic;
		this.isSolid = isSolid;
		this.gravityModifier = gravityModifier;
		this.physicsGroup = physicsGroup;
		objId = id;
		collisionRectOffsetX = (int) collisionRect.getX();
		collisionRectOffsetY = (int) collisionRect.getY();
		collisionRectHeight = (int) collisionRect.getHeight();
		collisionRectWidth = (int) collisionRect.getWidth();
	}
	
	/**
	 * Gets the image that is assigned to this object
	 * 
	 * @return img - the image
	 */
	public Image getImage() {
		return img;
	}
	
	/**
	 * Changes the object's image
	 * 
	 * @param img - the new image for the object
	 */
	public void setImage(Image img) {
		this.img = img;
	}
	
	/**
	 * Gets the object's id
	 * 
	 * @return objId - the ID
	 */
	public int getObjectID() {
		return objId;
	}
	
	/**
	 * Gets if the object is static
	 * 
	 * @return isStatic - if the object is static
	 */
	public boolean isStatic() {
		return isStatic;
	}
	
	/**
	 * Sets the value of isStatic
	 * 
	 * @param isStatic - the value to set isStatic to
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	/**
	 * returns whether or not the object is solid
	 * @return isSolid - the solidity of the object
	 */
	public boolean isSolid() {
		return isSolid;
	}
	
	/**
	 * Sets the solidity of the object
	 * 
	 * @param solid - what to set isSolid to
	 */
	public void setSolid(boolean solid) {
		this.isSolid = solid;
	}
	
	/**
	 * Gets the gravity modifier of the object
	 * 
	 * @return gravityModifier - the gravity modifier
	 */
	public double getGravityModifier() {
		return gravityModifier;
	}

	/**
	 * Sets the gravity modifier
	 * 
	 * @param modifier - the modifier to set the gravity to
	 */
	public void setGravityModifier(double modifier) {
		this.gravityModifier = modifier;
	}
	
	/**
	 * Gets the physics group this object is a part of
	 * 
	 * @return physicsGroup - the physics group
	 */
	public int getPhysicsGroup() {
		return physicsGroup;
	}
	
	/**
	 * sets the physics group this object is a part of
	 * 
	 * @param group - the physics group
	 */
	public void setPhysicsGroup(int group) {
		this.physicsGroup = group;
	}
	
	/**
	 * Creates and returns the rectangle used for general collision checking
	 * given the current x and y coordinates for the object
	 * 
	 * @return collisionRect - the general collision rectangle
	 */
	public Rectangle2D getCollisionRectangle(int x, int y) {
		return new Rectangle2D.Double(x + collisionRectOffsetX, y + collisionRectOffsetY, collisionRectWidth, collisionRectHeight);
	}

}
