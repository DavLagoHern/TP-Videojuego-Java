package logic.GameObjects;

import java.util.Random;

public class Player {
	private int coins;

	public Player() {
		this.coins = 50;

	}

	public void giveCoins(Random rand) {
		double x = rand.nextFloat();
		if (x > 0.5)
			this.coins += 10;
	}
	
	public void giveCoinsCommand(int i) {
			this.coins += i;
	}
	

	public void payCoins(int i) {
		this.coins -= i;
	}

	public int nCoins() {
		return this.coins;
	}

}
