package game.engine;

import java.util.ArrayList;

import game.engine.cards.Card;
import game.engine.cells.*;
import game.engine.exceptions.*;
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
		setCardsByRarity() ;
		reloadCards();	}
	
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
	private int[] indexToRowCol(int index) {
	    int row = index / 10;
	    int col;

	    if (row % 2 == 0) {
	        // even row → left to right
	        col = index % 10;
	    } else {
	        // odd row → right to left
	        col = 9 - (index % 10);
	    }

	    return new int[] {row, col};
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
	
<<<<<<< Updated upstream
	public void initializeBoard(ArrayList<Cell> specialCells) {
=======
	public void  initializeBoard(ArrayList<Cell>  specialCells)
	{
		for(int i = 0; i < 100; i++) {
			setCell(i, specialCells.get(i));
		}
	
		ArrayList<Monster> stationed = getStationedMonsters();
		for(int i = 0; i < Constants.MONSTER_CELL_INDICES.length; i++) { 
			if(stationedMonsters!=null) {
			MonsterCell N_M_C = new MonsterCell(stationed.get(i).getName(), stationed.get(i));
			setCell(Constants.MONSTER_CELL_INDICES[i], N_M_C);
			}
		}
		
		int index = 0;
		
		for(int i = 0; i < Constants.CARD_CELL_INDICES.length; i++) {
			
				setCell(Constants.CARD_CELL_INDICES[i], specialCells.get(index++));
		}
		
		for(int i = 0; i < Constants.CONVEYOR_CELL_INDICES.length;i++)
		{
			setCell(Constants.CONVEYOR_CELL_INDICES[i], specialCells.get(index++));
		}
		
		for(int i = 0; i < Constants.SOCK_CELL_INDICES.length;i++)
		{
			setCell(Constants.SOCK_CELL_INDICES[i], specialCells.get(index++));
		}
		
	}
>>>>>>> Stashed changes

	    // 1. Fill board with normal cells
	    for (int i = 0; i < 100; i++) {
	        setCell(i, new Cell("Normal"));
	    }

	    // 2. Place DoorCells (must alternate SCARER / LAUGHER)
	    int doorPtr = 0;
	    for (int i = 1; i < 100; i += 2) {
	        setCell(i, specialCells.get(doorPtr++));
	    }

	    // 3. Place Transport Cells (DO NOT group by type!)
	    int transportPtr = 50; // doors = first 50 lines in CSV

	    for (int i = 0; i < Constants.CONVEYOR_CELL_INDICES.length; i++) {
	        setCell(Constants.CONVEYOR_CELL_INDICES[i], specialCells.get(transportPtr++));
	    }

	    for (int i = 0; i < Constants.SOCK_CELL_INDICES.length; i++) {
	        setCell(Constants.SOCK_CELL_INDICES[i], specialCells.get(transportPtr++));
	    }

	    // 4. CardCells (NOT from CSV)
	    for (int i = 0; i < Constants.CARD_CELL_INDICES.length; i++) {
	        setCell(Constants.CARD_CELL_INDICES[i], new CardCell("Card"));
	    }

	    // 5. MonsterCells
	    ArrayList<Monster> stationed = getStationedMonsters();

	    for (int i = 0; i < Constants.MONSTER_CELL_INDICES.length; i++) {
	        MonsterCell mc = new MonsterCell("Monster", stationed.get(i));
	        setCell(Constants.MONSTER_CELL_INDICES[i], mc);
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
			cards.add(originalCards.get(i));
		}
	}
	public void  moveMonster(Monster  currentMonster,  int  roll,  Monster  opponentMonster) throws  InvalidMoveException
	{
		int position = currentMonster.getPosition();
		currentMonster.setPosition(currentMonster.getPosition()+roll);
		Cell c = getCell(currentMonster.getPosition());
		c.onLand(currentMonster, opponentMonster);
		
		if(currentMonster.getPosition() == opponentMonster.getPosition()) {
			currentMonster.setPosition(position);
			throw new InvalidMoveException();
		}
		
		if(currentMonster.isConfused())
			currentMonster.setConfusionTurns(opponentMonster.getConfusionTurns()-1);
		
		if(opponentMonster.isConfused())
			opponentMonster.setConfusionTurns(opponentMonster.getConfusionTurns());	
		
		updateMonsterPositions(currentMonster,opponentMonster);
		
	}
	private  void  updateMonsterPositions(Monster  player,  Monster  opponent)
	{
		
	}
}