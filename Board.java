	package game.engine;

import java.util.ArrayList;

import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.monsters.Monster;

public class Board {
	private Cell[][] boardCells;
	private static ArrayList<Monster> stationedMonsters; 
	private static ArrayList<Card> originalCards;
	public static ArrayList<Card> cards;
	
	public Board(ArrayList<Card> readCards) {
		this.boardCells = new Cell[Constants.BOARD_ROWS][Constants.BOARD_COLS];
		stationedMonsters = new ArrayList<Monster>();
		originalCards = readCards;
		cards = new ArrayList<Card>();
	}
	
	public Cell[][] getBoardCells() {
		return boardCells;
	}
	
	public static ArrayList<Monster> getStationedMonsters() {
		return stationedMonsters;
	}
	
	public static void setStationedMonsters(ArrayList<Monster> stationedMonsters) {
		Board.stationedMonsters = stationedMonsters;
	}

	public static ArrayList<Card> getOriginalCards() {
		return originalCards;
	}
	
	public static ArrayList<Card> getCards() {
		return cards;
	}
	
	public static void setCards(ArrayList<Card> cards) {
		Board.cards = cards;
	}
	private  int[]  indexToRowCol(int  index)
	{
		int row = index/10 +1;
		int col = 0;
		if (row % 2 == 0) {
            
            col = index % 10;
        } else {
            
            col = 10 - (index % 10);
        }
		
		return new int[] {row,col} ;
	}
	private  Cell  getCell(int  index)
	{
		int[] a = indexToRowCol(index);
		return boardCells[a[0]][a[1]];
	}
	private  void  setCell(int  index,  Cell  cell)
	{
		int[] a = indexToRowCol(index);
		boardCells[a[0]][a[1]] = cell;
	}
	public void  initializeBoard(ArrayList<Cell>  specialCells)
	{
		
	}
	private void setCardsByRarity()
	{
		ArrayList<Card> a = new ArrayList<Card>();
		for(int i = 0;i < originalCards.size();i++)
		{
			for(int j=0;j<originalCards.get(i).getRarity();j++)
			{
				a.add(originalCards.get(i));
			}
		}
		originalCards = a;
	}
	public static void reloadCards()
	{
		cards = originalCards;
	}
	public static Card drawCard()
	{
		if(cards.isEmpty())
			reloadCards();
		return cards.remove(0);
	}
}
