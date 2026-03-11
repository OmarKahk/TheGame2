package game.engine;
import java.util.ArrayList;

import game.engine.cards.Card;
import game.engine.cells.Cell;
import game.engine.monsters.Monster;

public class Board {

	private Cell[][] boardCells;
	private ArrayList<Monster> stationedMonsters;
	private ArrayList<Card> originalCards;
	private ArrayList<Card> cards;
	
	public Board(ArrayList<Card> readCards) {
		boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		stationedMonsters = new ArrayList<>();
		cards = new ArrayList<>();
		originalCards = readCards;
		
	}

	public ArrayList<Monster> getStationedMonsters() {
		return stationedMonsters;
	}

	public void setStationedMonsters(ArrayList<Monster> stationedMonsters) {
		this.stationedMonsters = stationedMonsters;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Cell[][] getBoardCells() {
		return boardCells;
	}

	public ArrayList<Card> getOriginalCards() {
		return originalCards;
	}
	
	
}
