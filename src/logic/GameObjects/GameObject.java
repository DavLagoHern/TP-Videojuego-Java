package logic.GameObjects;

import logic.Game;

public abstract class GameObject implements IAttack {

	protected int x;
	protected int y;
	protected int life;
	protected Game game;
	

	public GameObject(int x, int y, int life, Game game) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.game = game;

	}

	public abstract String symbol();
	
	public abstract void move();

	public String toString() {
		if (isAlive()) 
			return symbol() + " [" + giveLife() + "]";
		else
			return "";
	}

	public int giveRow() {
		return this.y;
	}

	public int giveCol() {
		return this.x;
	}

	public int giveLife() {
		return this.life;
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public boolean isAlive() {
		return this.life > 0;
	}


}
