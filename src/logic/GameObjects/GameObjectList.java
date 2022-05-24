package logic.GameObjects;

import java.util.ArrayList;

public class GameObjectList {
	ArrayList<GameObject> entities = new ArrayList<GameObject>();
	

	private GameObject getObjectInPosition(int x, int y) {
		for(GameObject o: entities){
			if(o.isInPosition(x, y)) return o;
		}
		return null;
	}

	public String getPositionToString(int x, int y) {
			if(getObjectInPosition(x, y) != null)return getObjectInPosition(x, y).toString();
		return "";
	}

	public void createVampire(Vampire v) {
		entities.add(v);
	}

	public void createSlayer(Slayer s) {
		entities.add(s);
	}

	public void move() {
		for(GameObject o: entities){
			o.move();
		}
	}

	public void attack() {
		for(GameObject o: entities){
			o.attack();
		}
	}

	public void remove() {
		ArrayList<GameObject> entitiesAux = new ArrayList<GameObject>();
		for(GameObject o: entities){
			if (o.isAlive())
				entitiesAux.add(o);
		}
		entities = entitiesAux;
	}

	public IAttack getAttackableInPosition(int x, int y) {
		return getObjectInPosition(x, y);
	}
	
	public boolean checkPositionEmpty(int x, int y) {
		if(getObjectInPosition(x, y) != null) return false;
		return true;
	}

	public void garlicPush() {
		for(GameObject o: entities){
			o.receiveGarlicPush();
		}
	}

	public void lightFlash() {
		for(GameObject o: entities){
			o.receiveLightFlash();
		}
	}


}
