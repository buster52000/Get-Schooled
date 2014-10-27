package pburg.tsa.getSchooled.world;

import java.util.ArrayList;

public class World {

	private ArrayList<WorldObject> objects;
	private int nextID;

	/**
	 * Create new instance of World
	 * 
	 * @param initialObjs
	 * @param worldWidth
	 * @param worldHeight
	 * @throws IllegalStateException
	 */
	public World(ArrayList<WorldObject> initialObjs, int worldWidth, int worldHeight) throws IllegalStateException {
		objects = new ArrayList<WorldObject>();
		nextID = 0;
		addObjectsToWorld(initialObjs);
	}

	public boolean addObjectToWorld(WorldObject obj) {
		if (objects.contains(obj))
			return false;
		obj.setID(nextID);
		nextID++;
		return objects.add(obj);
	}

	public void addObjectsToWorld(ArrayList<WorldObject> newObjs) {
		for (WorldObject obj : newObjs)
			addObjectToWorld(obj);
	}

	public int getNumObjectsInWorld() {
		return objects.size();
	}

	public WorldObject getObject(int id) {
		for (WorldObject obj : objects)
			if (obj.getID() == id)
				return obj;
		return null;
	}

	public boolean removeObject(WorldObject obj) {
		return objects.remove(obj);
	}

	public boolean removeObject(int objID) {
		for(WorldObject obj : objects)
			if(objID == obj.getID())
				return objects.remove(obj);
		return false;
	}

}
