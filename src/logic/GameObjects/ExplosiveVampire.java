package logic.GameObjects;

import logic.Game;

public class ExplosiveVampire extends Vampire {

	public ExplosiveVampire(int x, int y, int life, Game game) {
		super(x, y, life, game);
	}

	public String symbol() {
		return "EV";
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
	public boolean receiveSlayerAttack(int harm) {
		this.life -= harm;
		if(!isAlive()) {
			for(int i = this.x-1;i<=this.x+1;i++) {
				for(int j = this.y-1;j<=this.y+1;j++) {
					IAttack other = game.getAttackableInPosition(i, j);
					if (other != null)
						if(i == this.x && j ==this.y);
						else other.receiveSlayerAttack(HARM);
						
				}
			}
			vampireOnBoard--;
		}
		return true;
	}
	
}
