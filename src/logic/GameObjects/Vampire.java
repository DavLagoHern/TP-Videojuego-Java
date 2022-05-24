package logic.GameObjects;

import logic.Game;

public class Vampire extends GameObject {

	protected static final int HARM = 1;
	private int turn;
	public static int vampireOnBoard;
	public static int vampireRemaining;
	public static boolean hasArrived;

	public Vampire(int x, int y, int life,Game game) {
		super(x, y, life, game);
		this.turn = 2;
		vampireOnBoard += 1;
		vampireRemaining -= 1;
	}

	@Override
	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null)
				other.receiveVampireAttack(HARM);
		}
	}
	
	
	@Override
	public void move() {
			this.turn--;
			if (!game.checkPositionEmpty(this.x - 1, this.y));
			else nextPosition();
	}

	@Override
	public boolean receiveSlayerAttack(int harm) {
		this.life -= harm;
		if(!isAlive())vampireOnBoard--;
		return true;
	}
	
	@Override
	public boolean receiveGarlicPush() {
		if(game.checkPositionEmpty(this.x + 1, this.y)) {
		this.x += 1;
			if(!game.checkLimits(this.x-1, this.y)) {
				this.life = 0;
				vampireOnBoard--;
			}
		}
		this.turn =2;
		return true;
		
	}
	
	@Override
	public boolean receiveLightFlash() {
		this.life = 0;
		vampireOnBoard--;
		return true;
	}

	protected void nextPosition() {
		if (this.turn <= 0) {
			this.x--;
			this.turn = 2;
		}
		if (this.x < 0) hasArrived = true;
	}

	public String symbol() {
		return "V";
	}

	

	

}
