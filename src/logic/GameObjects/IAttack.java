package logic.GameObjects;

public interface IAttack {
	// I attack other Object
	public void attack();
	// I receive attack from other
	default boolean receiveSlayerAttack(int damage) {return false;};
	default boolean receiveVampireAttack(int damage) {return false;};
	default boolean receiveLightFlash() {return false;};
	default boolean receiveGarlicPush() {return false;};
	default boolean receiveDraculaAttack(){return false;};
}
