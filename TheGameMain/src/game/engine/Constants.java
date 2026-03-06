package game.engine;

public final class Constants {
	//board constants
	int BOARD_SIZE = 100;
	int BOARD_ROWS = 10;
	int BOARD_COLS = 10;
	int WINNING_POSITION = 90;
	int STARTING_POSITION = 0;
	//special cells positions constants
	int[] MONSTER_CELL_INDICES = {2, 18, 34, 54, 82, 88};
	int[] CONVEYOR_CELL_INDICES = {6, 22, 44, 52, 66};
	int[] SOCK_CELL_INDICES = {32, 42, 74, 84, 98};
	int[] CARD_CELL_INDICES = {4, 12, 28, 36, 48, 56, 60, 76, 86, 90};
	// Energy Constants
	int WINNING_ENERGY = 1000;
	int MIN_ENERGY = 0;
	//Monster constants
	int MULTITASKER_BONUS = 200;
	int SCHEMER_STEAL = 10;
	//Cell constants
	int SLIP_PENALTY = 100;
	//Power constants
	int POWERUP_COST = 500;
}
