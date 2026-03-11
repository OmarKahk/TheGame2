package game.engine;

import java.util.ArrayList;
import game.engine.*;
import game.engine.cards.Card;
import game.engine.cells.Cell;
import game.engine.monsters.*;
public class Board {
	private Cell[][] boardCells;
	ArrayList<Monster> stationedMonsters;
	ArrayList<Card> originalCards;
	ArrayList<Card> cards;
	Board(ArrayList<Card> readCards)
	{
		int x = Constants.BOARD_ROWS;
		
		boardCells = new Cell[BOARD_ROWS][BOARD_COLS] ;
		stationedMonsters = new ArrayList();
		cards = new ArrayList();
		originalCards = 
	}
	
}
