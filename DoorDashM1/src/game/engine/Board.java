package game.engine;

import java.util.ArrayList;
import java.util.Collections;

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
		originalCards = new ArrayList<Card>(readCards);
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
	public void initializeBoard(ArrayList<Cell> specialCells) {

	    int specialIndex = 0;
	    for (int i = 0; i < Constants.BOARD_SIZE; i++) {
	        if (i % 2 == 0) {
	            setCell(i, new Cell("Normal")); // FIX HERE
	        }
	        else {
	            setCell(i, specialCells.get(specialIndex));
	            specialIndex++;
	        }
	    }
	    
	    for (int i = 0; i < Constants.CONVEYOR_CELL_INDICES.length; i++) {
	    	
	        setCell(Constants.CONVEYOR_CELL_INDICES[i],specialCells.get(specialIndex));
	        specialIndex++;
	        setCell(Constants.SOCK_CELL_INDICES[i],specialCells.get(specialIndex));
	        specialIndex++;
	    }

	    for (int i = 0; i < Constants.CARD_CELL_INDICES.length; i++) {
	        int index = Constants.CARD_CELL_INDICES[i];
	        setCell(index, new CardCell("Card Cell"));
	    }

	    for (int i = 0; i < Constants.MONSTER_CELL_INDICES.length; i++) {
	        int index = Constants.MONSTER_CELL_INDICES[i];

	        if (i < stationedMonsters.size()) {
	            Monster monster = stationedMonsters.get(i);
	            monster.setPosition(index);
	            setCell(index, new MonsterCell(monster.getName(), monster));
	        } else {
	            setCell(index, new MonsterCell("Monster Cell", null));
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
	public static void reloadCards() {
	    cards = new ArrayList<Card>(originalCards);
	    Collections.shuffle(cards);
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
		for (int i = 0; i < boardCells.length; i++) {
	        for (int j = 0; j < boardCells[i].length; j++) {
	            boardCells[i][j].setMonster(null);
	        }
	    }
		
		int playerPos = player.getPosition();
	    int row = playerPos / 10;
	    int col = playerPos % 10;

	    if (row % 2 == 1) {
	        col = 10 - 1 - col;
	    }
	    
	    boardCells[row][col].setMonster(player);
	    
	    int opponentPos = opponent.getPosition();
	    int opponentRow = opponentPos / 10;
	    int opponentCol = opponentPos % 10;

	    if (opponentRow % 2 == 1) {
	        opponentCol = 10 - 1 - opponentCol;
	    }

	    boardCells[opponentRow][opponentCol].setMonster(opponent);
	}
}