package pburg.tsa.getSchooled.world;

import java.util.ArrayList;

public class World {

	private ArrayList<WorldObject> objects;
	
	@SuppressWarnings("unchecked")
	public World (ArrayList<WorldObject> initialObjs, int worldWidth, int worldHeight) {
		objects = (ArrayList<WorldObject>) initialObjs.clone();
	}
	
}
