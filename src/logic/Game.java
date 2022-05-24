package logic;

import java.util.Random;
import logic.GameObjects.Player;
import logic.GameObjects.Slayer;
import logic.GameObjects.Vampire;
import logic.GameObjects.BloodBank;
import logic.GameObjects.Dracula;
import logic.GameObjects.ExplosiveVampire;
import logic.GameObjects.GameObjectBoard;
import logic.GameObjects.IAttack;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable {

	private final static int slayerCost = 50;
	private final static int lightFlashCost = 50;
	private final static int garlicCost = 10;
	private final static int vampireLife = 5;
	private final static int slayerLife = 3;
	
	
	private Random rand;
	private GamePrinter printer;
	private GameObjectBoard gameObjectBoard;
	private Player player;
	private static int cycles;
	private static int TAB_LENGTH;
	private static int TAB_WIDTH;
	private static int VAMPIRES_MAX;
	private static double VAMPIRES_PROB;
	private Long gameSeed;
	private Level gameLevel;
	private boolean userExit;

	public Game(Long seed, Level level) {
		inicial(seed, level);
	}

	// ---------------------------------------------------------
	// ------RESET / INICIAL------------------------------------
	// ---------------------------------------------------------

	public void inicial(Long seed, Level level) {
		TAB_LENGTH = level.dim_x();
		TAB_WIDTH = level.dim_y();
		VAMPIRES_MAX = level.numberOfVampires();
		VAMPIRES_PROB = level.vampireFrequency();
		player = new Player();
		rand = new Random(seed);
		gameObjectBoard = new GameObjectBoard(this);
		printer = new GamePrinter(this, level.dim_x(), level.dim_y());
		cycles = 0;
		gameSeed = seed;
		gameLevel = level;
		userExit = false;
		inicialEntities();
	}

	public void inicialEntities() {
		Vampire.hasArrived = false;
		Slayer.slayerOnBoard = 0;
		Vampire.vampireRemaining = VAMPIRES_MAX;
		Vampire.vampireOnBoard = 0;
		Dracula.draculaOnBoard = 0;
		Dracula.draculaIsAlive = false;
	}

	public void reset(Long seed, Level level) {
		inicial(seed, level);
	}

	public void doExit() {
		userExit = true;
	}

	// ---------------------------------------------------------
	// ------PRINT GAME-----------------------------------------
	// ---------------------------------------------------------

	public void printGame() {
		System.out.println(this);
	}

	public String toString() {
		return printer.toString();
	}

	public String getGameOverInfo() {
		if (userExit)
			return "Nobody wins...";
		if (vampiresWin())
			return "Vampires win!";
		else
			return "Player wins";
	}

	@Override
	public String getInfo() {
		if (Dracula.draculaIsAlive) {
		return "Number of cycles: " + cycles +"\n" +
				"Coins: " + coins() +"\n" +
				"Remaining vampires: " + (VAMPIRES_MAX - vampiresCreated()) +"\n" +
				"Vampires on the board: " + Vampire.vampireOnBoard+"\n"+
				"Dracula is alive";
		}
		else {
		return "Number of cycles: " + cycles +"\n" +
				"Coins: " + coins() +"\n" +
				"Remaining vampires: " + (VAMPIRES_MAX - vampiresCreated()) +"\n" +
				"Vampires on the board: " + Vampire.vampireOnBoard;
		}
	}

	@Override
	public String getPositionToString(int x, int y) {
		return gameObjectBoard.getPositionToString(x, y);
	}

	// ---------------------------------------------------------
	// ------UPDATE --------------------------------------------
	// ---------------------------------------------------------

	public void updateBoard() {
		gameObjectBoard.move();
		player.giveCoins(rand);
		gameObjectBoard.attack();
		createVampire();
		createDracula();
		createExplosiveVampire();
		remove();
		if (!isFinished())
			cycles++;
	}

	public void remove() {
		gameObjectBoard.remove();
	}

	// ---------------------------------------------------------
	// ------CREATE --------------------------------------------
	// ---------------------------------------------------------

	public boolean addSlayerIfYouCan(int x, int y) {
		if (checkLimits(x, y) && gameObjectBoard.checkPositionEmpty(x, y)) {
			if (player.nCoins() >= slayerCost) {
				gameObjectBoard.createSlayer(new Slayer(x, y, slayerLife, this));
				player.payCoins(slayerCost);
				updateBoard();
				return true;
			} else
				System.out.println("[ERROR]: Not enough coins");
		} else
			System.out.println("[ERROR]: Invalid position");
		return false;
	}

	public boolean createVampire() {
		if (Vampire.vampireRemaining > 0) {
			double x = rand.nextDouble();
			if (x <= VAMPIRES_PROB) {
				int col = TAB_LENGTH - 1;
				int row = rand.nextInt(TAB_WIDTH);
				if (checkPositionEmpty(col, row)) {
					gameObjectBoard.createVampire(new Vampire(col, row, vampireLife, this));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean createDracula() {
		if (Vampire.vampireRemaining > 0 && Dracula.draculaOnBoard ==0) {
			double x = rand.nextDouble();
			if (x <= VAMPIRES_PROB) {
				int col = TAB_LENGTH - 1;
				int row = rand.nextInt(TAB_WIDTH);
				if (checkPositionEmpty(col, row)) {
					gameObjectBoard.createVampire(new Dracula(col, row, vampireLife, this));
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean createExplosiveVampire() {
		if (Vampire.vampireRemaining > 0) {
			double x = rand.nextDouble();
			if (x <= VAMPIRES_PROB) {
				int col = TAB_LENGTH - 1;
				int row = rand.nextInt(TAB_WIDTH);
				if (checkPositionEmpty(col, row)) {
					gameObjectBoard.createVampire(new ExplosiveVampire(col, row, vampireLife, this));
					return true;
				}
			}
		}
		return false;
	}

	// ---------------------------------------------------------
	// ------ATTACK --------------------------------------------
	// ---------------------------------------------------------

	public IAttack getAttackableInPosition(int x, int y) {
		return gameObjectBoard.getAttackableInPosition(x, y);
	}

	public IAttack getAttackableInRow(int x, int y) {
		return gameObjectBoard.getAttackableInRow(x, y);
	}

	// ---------------------------------------------------------
	// ------CHECKS --------------------------------------------
	// ---------------------------------------------------------

	public boolean checkPositionEmpty(int x, int y) {
		return gameObjectBoard.checkPositionEmpty(x, y);
	}

	public boolean checkLimits(int x, int y) {
		return (x < TAB_LENGTH-1 && x >= 0 && y < TAB_WIDTH && y >= 0);
	}

	public boolean isFinished() {
		if (userExit)
			return true;
		else if (vampiresWin())
			return true;
		else if (slayersWin())
			return true;
		else
			return false;
	}

	public boolean vampiresWin() {
		if (Vampire.hasArrived)
			return true;
		return false;
	}

	public boolean slayersWin() {
		if (Vampire.vampireRemaining == 0 && Vampire.vampireOnBoard == 0)
			return true;
		return false;
	}

	//---------------------------------------------------------
	//------GIVE INFO------------------------------------------
	//---------------------------------------------------------

	public Long giveSeed() {
		return gameSeed;
	}

	public Level giveLevel() {
		return gameLevel;
	}

	public int coins() {
		return player.nCoins();
	}

	public int TAB_LENGTH() {
		return TAB_LENGTH;
	}

	public int vampiresCreated() {
		return VAMPIRES_MAX - Vampire.vampireRemaining;
	}

	public int nCycles() {
		return cycles;
	}

	//---------------------------------------------------------
	//------COMMANDS ------------------------------------------
	//---------------------------------------------------------
	
	public void giveCoinsCommand(int i) {
		player.giveCoinsCommand(i);	
	}
	
	public boolean garlicPush() {
		if (player.nCoins() >= garlicCost) {
			player.payCoins(garlicCost);
			gameObjectBoard.garlicPush();
			updateBoard();
			return true;
		}
		else {
			System.out.println("Not enough coins");
			return false;
		}
	}

	public boolean lightFlash() {
		if (player.nCoins() >= lightFlashCost) {
			player.payCoins(lightFlashCost);
			gameObjectBoard.lightFlash();
			updateBoard();
			return true;
		}
		else {
			System.out.println("[ERROR]: Not enough coins");
			return false;
		}
	}
	
	public boolean createVampireCommand(String type, int x, int y) {
		if (Vampire.vampireRemaining > 0) {
			if (checkLimits(x-1, y) && gameObjectBoard.checkPositionEmpty(x, y)) {
				if(type == null) {
					gameObjectBoard.createVampire(new Vampire(x, y, vampireLife, this));
					return true;
				}
				else if(type.equals("e")) {
					gameObjectBoard.createVampire(new ExplosiveVampire(x, y, vampireLife, this));
					return true;
				}
				else if(type.equals("d")) {
					if(Dracula.draculaOnBoard == 0) {
						gameObjectBoard.createVampire(new Dracula(x, y, vampireLife, this));
					return true;
					}
					else {System.out.println("[ERROR]: Dracula is alive");
					return false;	
					}
				}
				else{
					System.out.println("[ERROR]: invalid type");
					return false;
				}
			}
			else{
				System.out.println("[ERROR]: Invalid position");
				return false;
			}
		}
		else{
			System.out.println("[ERROR]: No more remaining vampires left");
			return false;
		}
			
	}

	public boolean addBloodBank(int x, int y, int z) {
		if (checkLimits(x, y) && gameObjectBoard.checkPositionEmpty(x, y)) {
			if (player.nCoins() >= z) {
				gameObjectBoard.createSlayer(new BloodBank(x, y, z, this));
				player.payCoins(z);
				updateBoard();
				return true;
			} else
				System.out.println("[ERROR]: Not enough coins");
		} else
			System.out.println("[ERROR]: Invalid position");
		return false;
	}

}
