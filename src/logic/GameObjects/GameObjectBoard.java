package logic.GameObjects;

import logic.Game;

public class GameObjectBoard {
	
	private GameObjectList gameObjectList;
	private Game game;
	
	public GameObjectBoard(Game game) {
		gameObjectList = new GameObjectList();
		this.game = game; 
	}
	
	public String getPositionToString(int x, int y) {
		return gameObjectList.getPositionToString(x,y);
	}
	
	public void createVampire(Vampire v) {	
		gameObjectList.createVampire(v);
	}

	public void createSlayer(Slayer s) {
		gameObjectList.createSlayer(s);
	}
	
	public void move() {
		gameObjectList.move();
	}

	public void attack() {
		gameObjectList.attack();
	}
	
	public void remove() {
		gameObjectList.remove();
	}
	
	public boolean checkPositionEmpty(int x, int y) {
		return gameObjectList.checkPositionEmpty(x, y);
	}
	
	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectList.getAttackableInPosition(x, y);
	}
	
	public IAttack getAttackableInRow(int x,int y) {
		for(int i = x; i < game.TAB_LENGTH(); i++) {
			IAttack other = gameObjectList.getAttackableInPosition(i, y);
			if(other != null)return other;
		}
		return null;
	}

	public void garlicPush() {
		gameObjectList.garlicPush();
	}

	public void lightFlash() {
		gameObjectList.lightFlash();
	}


	

	

	

	

	

	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
/*
	public GameObjectBoard(Random rand, Game game) {
		vampireList = new VampireList(rand, game);
		slayerList = new SlayerList(rand, game);
	}

	// -GENERAL--------------
	public void updateGame() {
		vampireList.VampireListUpdate();
	}

	public String getPositionToString(int x, int y) {
		if (vampireList.isInPosition(x, y))
			return vampireList.isInPositionDraw(x, y);
		return slayerList.isInPositionDraw(x, y);
	}

	public boolean checkPostionEmpty(int x, int y) {
		return !(slayerList.isInPosition(x, y) || vampireList.isInPosition(x, y));
	}

	public void removeAll() {
		vampiresRemove();
		slayersRemove();
	}

	public void attackAll() {
		slayersAttackObj();
		vampiresAttackObj();
	}

	// -SLAYERS-------------

	public void slayersRemove() {
		slayerList.remove();
	}

	public void slayersAttackObj() {
		slayerList.slayersAttack();
	}

	public boolean hitSlayer(int x, int y) {
		return slayerList.makeHitSlayer(x, y);
	}

	public void createSlayerObj(Slayer s) {
		slayerList.createSlayer(s);
	}

	public int giveSlayerRow(int i) {
		return slayerList.giveRow(i);
	}

	public int giveSlayerCol(int i) {
		return slayerList.giveCol(i);
	}

	// -VAMPIRES-------------
	
	public void vampiresRemove() {
		vampireList.remove();
	}

	public void vampiresAttackObj() {
		vampireList.vampiresAttack();
	}

	public boolean vampireIsAliveObj(int i) {
		return vampireList.isAlive(i);
	}

	public void createVampireObj(Vampire v) {
		vampireList.createVampire(v);
	}

	public int findVampireObj(int j, int row) {
		return vampireList.findVampire(j, row);
	}

	public boolean hitVamp(int x, int y) {
		return vampireList.makeHitVampire(x, y);
	}

	public boolean isInPositionVampires(int x, int y) {
		return vampireList.isInPosition(x, y);
	}*/

