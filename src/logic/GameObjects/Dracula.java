package logic.GameObjects;

import logic.Game;

public class Dracula extends Vampire {
	public static int draculaOnBoard;
	public static boolean draculaIsAlive;
	
	public Dracula(int x, int y, int life, Game game) {
		super(x, y, life, game);
		draculaOnBoard += 1;
		draculaIsAlive = true;
	}
	
	public String symbol() {
		return "D";
	}
	
	@Override
	public boolean receiveSlayerAttack(int harm) {
		this.life -= harm;
		if(!isAlive()) {
			vampireOnBoard--;
			draculaIsAlive = false;
		}
		return true;
	}

	@Override
	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null)
				other.receiveDraculaAttack();
		}
	}
	
	@Override
	public boolean receiveLightFlash() {
		return false;
	}
	
}
