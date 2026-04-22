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
		if (row % 2 == 0) 
		{
			col = 9 - (index % 10) ;
        }
		else {
            
            col = index%10 ;
        }
		 
		return new int[] {row-1,col} ;
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
		//initialize door cells
		for(int i = 1; i <= 99; i+=2) {
			setCell(i, specialCells.get(i));
		}
		
		//initialize MONSTERS
		ArrayList<Monster> stationed = getStationedMonsters();
		for(int i = 1; i < Constants.MONSTER_CELL_INDICES.length; i++) { 
			MonsterCell N_M_C = new MonsterCell(stationed.get(i).getName(), stationed.get(i));
			setCell(Constants.MONSTER_CELL_INDICES[i], N_M_C);
		}
		
		//initialize conveyer and contamination
		
		for(int i = 50; i < specialCells.size(); i++) {
			if (i%2 == 0) {
				setCell(Constants.CONVEYOR_CELL_INDICES[(i-50) - (i%2)], specialCells.get(i));
			}else {
				setCell(Constants.SOCK_CELL_INDICES[(i-50) - (i%2)], specialCells.get(i));
			}
		}
		
	}

	
	private  void  setCardsByRarity()
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
	public static  Card  drawCard()
	{
		if(cards.isEmpty())
			reloadCards();
		Card c = cards.remove(0);
		return c;
	}
	public static  void  reloadCards()
	{
		for(int i=0;i<originalCards.size();i++)
		{
			Card c = originalCards.get(i);
			for(int j=0;j<c.getRarity();j++)
			{
				cards.add(c);
			}
		}
	}
	public void  moveMonster(Monster  currentMonster,  int  roll,  Monster  opponentMonster) throws  InvalidMoveException
	{
		
	}
}