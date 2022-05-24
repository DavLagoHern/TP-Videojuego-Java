package logic.GameObjects;

import logic.Game;

public class BloodBank extends Slayer {
private int coins;

	public BloodBank(int x, int y, int life, Game game) {
		super(x, y, life, game);
		this.coins = life;
	}

	@Override
	public void attack() {
			game.giveCoinsCommand(this.coins/10);
	}
	@Override
	public boolean receiveVampireAttack(int harm) {
		this.life = 0;
		slayerOnBoard--;
		return true;
	}
	@Override
	public String symbol() {
		return "B";
	}
}
