package game.engine.cells;

import game.engine.monsters.Monster;

public class Cell {
	
	private String name;
	public Monster monster;
	
	public Cell(String name) {
		this.name = name;
		this.monster = null;
	}
}
