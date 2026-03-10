package game.engine;

public final class Constants {
	//board constants
	static int BOARD_SIZE = 100;
	static int BOARD_ROWS = 10;
	static int BOARD_COLS = 10;
	static int WINNING_POSITION = 90;
	static int STARTING_POSITION = 0;
	//special cells positions constants
	static int[] MONSTER_CELL_INDICES = {2, 18, 34, 54, 82, 88};
	static int[] CONVEYOR_CELL_INDICES = {6, 22, 44, 52, 66};
	static int[] SOCK_CELL_INDICES = {32, 42, 74, 84, 98};
	static int[] CARD_CELL_INDICES = {4, 12, 28, 36, 48, 56, 60, 76, 86, 90};
	// Energy Constants
	static int WINNING_ENERGY = 1000;
	static int MIN_ENERGY = 0;
	//Monster constants
	static int MULTITASKER_BONUS = 200;
	static int SCHEMER_STEAL = 10;
	//Cell constants
	static int SLIP_PENALTY = 100;
	//Power constants
	static int POWERUP_COST = 500;
}
