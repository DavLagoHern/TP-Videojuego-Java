package logic.GameObjects;

import logic.Game;

public class Slayer extends GameObject {

	private static final int HARM = 1;
	public static int slayerOnBoard;

	public Slayer(int x, int y, int life, Game game) {
		super(x, y, life, game);
		slayerOnBoard += 1;
	}

	@Override
	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInRow(x+1,y);
			if (other != null)
				other.receiveSlayerAttack(HARM);
		}
	}

	@Override
	public boolean receiveVampireAttack(int harm) {
		this.life -= harm;
		if(!isAlive())slayerOnBoard--;
		return true;
	}
	
	@Override
	public boolean receiveDraculaAttack() {
		this.life =0;
		slayerOnBoard--;
		return true;
	}

	@Override
	public String symbol() {
		return "S";
	}

	@Override
	public void move() {}

}
