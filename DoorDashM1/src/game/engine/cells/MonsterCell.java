package game.engine.cells;

import game.engine.monsters.*;

public class MonsterCell extends Cell {
	private Monster cellMonster;

	public MonsterCell(String name, Monster cellMonster) {
		super(name);
		this.cellMonster = cellMonster;
	}

	public Monster getCellMonster() {
		return cellMonster;
	}
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {

	    this.setMonster(landingMonster);
	    if (landingMonster.getRole() == cellMonster.getRole()) {
	        landingMonster.executePowerupEffect(opponentMonster);
	        return;
	    }

	    if (landingMonster.getEnergy() > cellMonster.getEnergy()) {
	        int landingEnergy = landingMonster.getEnergy();
	        int cellEnergy = cellMonster.getEnergy();
	        landingMonster.alterEnergy(-(landingEnergy - cellEnergy));
	        cellMonster.alterEnergy(landingEnergy - cellEnergy);
	    }
	}
}
